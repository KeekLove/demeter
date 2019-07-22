package com.demeter.common.util;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

/**
*@Description Properties文件操作工具类
*@Author 刘海亮
*@DateTime 2019/7/21 19:46
*/
public class PropertiesUtils {
    /**
     * 以Properties 格式读取资源文件
     * @param resource 资源名称（以class为根目录的相对路径的文件名，例如：log4j.properties）
     * */
    public static Properties getResourceAsProperties(String resource) throws IOException {
        Properties props = new Properties();

        InputStream in = getResourceAsStream(resource);
        props.load(in);
        in.close();

        return props;
    }

    private static InputStream getResourceAsStream(final String name) {
        return (InputStream) AccessController.doPrivileged(
                new PrivilegedAction<Object>() {
                    public Object run() {
                        ClassLoader threadCL = getContextClassLoader();
                        if (threadCL != null) {
                            return threadCL.getResourceAsStream(name);
                        } else {
                            return ClassLoader.getSystemResourceAsStream(name);
                        }
                    }
                });
    }

    private static ClassLoader getContextClassLoader() {
        ClassLoader classLoader = null;

        if (classLoader == null) {
            try {
                // Are we running on a JDK 1.2 or later system?
                Method method = Thread.class.getMethod("getContextClassLoader", (Class[]) null);

                // Get the thread context class loader (if there is one)
                try {
                    classLoader = (ClassLoader) method.invoke(Thread.currentThread());
                } catch (IllegalAccessException e) {
                    ; // ignore
                } catch (InvocationTargetException e) {
                    /**
                     * InvocationTargetException is thrown by 'invoke' when
                     * the method being invoked (getContextClassLoader) throws
                     * an exception.
                     * getContextClassLoader() throws SecurityException when
                     * the context class loader isn't an ancestor of the
                     * calling class's class loader, or if security
                     * permissions are restricted.
                     * In the first case (not related), we want to ignore and
                     * keep going. We cannot help but also ignore the second
                     * with the logic below, but other calls elsewhere (to
                     * obtain a class loader) will trigger this exception where
                     * we can make a distinction.
                     */
                    if (e.getTargetException() instanceof SecurityException) {
                        ; // ignore
                    } else {
                        // Capture 'e.getTargetException()' exception for details
                        // alternate: log 'e.getTargetException()', and pass back 'e'.
                        throw new Exception("Unexpected InvocationTargetException", e.getTargetException());
                    }
                }
            } catch (Exception e) {
                // Assume we are running on JDK 1.1
                ; // ignore
            }
        }

        if (classLoader == null) {
            classLoader = PropertiesUtils.class.getClassLoader();
        }

        // Return the selected class loader
        return classLoader;
    }
}
