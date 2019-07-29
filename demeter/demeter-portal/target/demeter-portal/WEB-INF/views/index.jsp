
<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 2019/7/23
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>上传菜品</h2>

<form action="/food/upload" method="post" enctype="multipart/form-data"><br/>
    icon:<input type="file" name="icon"><br/>
    about:<input type="text" name="about"><br/>
    prize:<input type="text" name="prize"><br/>
    name:<input type="text" name="name"><br/>
    models:<input type="radio" name="models" value="辣">1
          <input type="radio" name="models" value="中辣">2
          <input type="radio" name="models" value="超辣"> 3<br/>
    menuId:<input type="text" name="menuId"><br/>
    restaurantId:<input type="text" name="restaurantId"><br/>
    token:<input type="text" value='${token}' name="token"><br/>
    <input type="submit">
</form>
</body>
</html>
