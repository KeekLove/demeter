package com.demeter.common.util;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
*@Description 
*@Author 陈龙鑫
*@DateTime 2019/7/20 2019/7/20
 *
*/

public class  FileSave{


    public String saveFile(MultipartFile file, String type) throws IOException {
        String newFileName = null;
        if (null != file)
        {
            // 获取图片原始上传名
            String originalFilename = file.getOriginalFilename();
            // 使用 UUID 生成一个新的文件名（避免名字重复）
            newFileName = UUID.randomUUID()+ type;
            // 图片上传成功后，讲图片写入到本地数据库
            String path = "e:/temp/upload/"+newFileName;
            File toFile = new File(path);
            if (!toFile.exists()) {
                toFile.mkdirs();
            }
            //将file 复制到toFile
            file.transferTo(toFile);
        }
        return newFileName.toString();
    }

    public String saveFile(MultipartFile file) throws IOException {
        String newFileName = null;
        if (null != file) {
            // 获取图片原始上传名
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀名
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 使用 UUID 生成一个新的文件名（避免名字重复）
            newFileName = UUID.randomUUID() + suffix;
            // 图片上传成功后，讲图片写入到本地数据库
            String path = "e:/temp/upload/" + newFileName;
            File toFile = new File(path);
            if (!toFile.exists()) {
                toFile.mkdirs();
            }
            //将file 复制到toFile
            file.transferTo(toFile);
        }
        return newFileName.toString();
    }


}



