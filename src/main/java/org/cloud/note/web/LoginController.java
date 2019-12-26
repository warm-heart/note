package org.cloud.note.web;

import ch.qos.logback.core.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.cloud.note.VO.ApiResponse;
import org.cloud.note.VO.ServiceResult;
import org.cloud.note.entity.User;
import org.cloud.note.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.io.IOException;


/**
 * @author wangqianlong
 * @create 2019-12-25 10:45
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public ApiResponse<String> login(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "password") String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password))
            return ApiResponse.error("用户名或密码为空");
        ServiceResult serviceResult = loginService.login(userName, password);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success((String) serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());

    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(String token) {
        //解决前端传送的数据中带有特殊字符（+ 会转化为空格）
        String Token = HtmlUtils.htmlEscape(token);
        if (StringUtils.isEmpty(Token)) {
            return ApiResponse.error("你还未登录");
        }
        ServiceResult serviceResult = loginService.logout(Token);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success((String) serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());

    }


    @PostMapping("/file")
    public ApiResponse<String> file(@RequestParam(name = "file",required = false) MultipartFile file, User user) {


        //1.判断文件是否为空(是否上传文件 / 文件内容是否为空)
        if (file==null||file.isEmpty()) {
            return ApiResponse.error("上传文件不可以为空");
        }
        //获取上传文件名
        String fileName = file.getOriginalFilename();

        /*防止不同浏览器上传出现FileIOException 目标卷不正确*/
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        System.out.println("文件名是" + fileName);
        //2.判断文件后缀名是否符合要求
        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String Suffix = "csv/txt/zip/pdf/doc/jpg";

        if (Suffix.indexOf(fileNameSuffix) < 0) {
            return ApiResponse.error("文件类型不正确");
        }

        //3.判断文件大小是否符合要求
        int size = (int) file.getSize();//获取上传文件大小,返回字节长度1M=1024k=1048576字节 - 文件过大进入controller之前抛出异常 - 前端判断文件大小

        System.out.println("size:" + size);
        if (size > 1024 * 1024) {
            return ApiResponse.error("上传文件过大，请上传小于1MB大小的文件");
        }
        //4.将文件重命名，避免文件名相同覆盖文件
        String fileNamePrefix = fileName.substring(0, fileName.lastIndexOf("."));
        fileName = fileNamePrefix + "-" + System.currentTimeMillis() + "." + fileName;//获取上传文件名

        // TODO:文件名存放数据库
        //5.判断文件夹是否存在
        String path = "E:/uploadFile";
        File targetFile = new File(path + "/" + fileName);
        if (!targetFile.getParentFile().exists()) {
            //不存在创建文件夹
            targetFile.getParentFile().mkdirs();
        }
        try {
            //6.将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponse.success("ok");

    }


    @PostMapping("/postUploadMore")
    @ResponseBody
    public ApiResponse<String> uploadMore(@RequestParam("file") MultipartFile[] files) {
        //文件上传位置
        String path = "E:/uploadFile";
        System.out.println(files.length);
        for (int i = 0; i < files.length; i++) {
            //1.判断文件是否为空
            if (files[i].isEmpty()) {
                return ApiResponse.error("第" + (i + 1) + "个文件为空");
            }
            //2.判断文件后缀名是否符合要求
            //获取上传文件名
            String fileName = files[i].getOriginalFilename();
            //防止出现不同浏览器上传出现目标卷错误
            fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
            String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String Suffix = "csv/txt/zip/pdf/doc/jpg";
            //        if (!Suffix.contains(fileNameSuffix)){
            if (Suffix.indexOf(fileNameSuffix) < 0) {
                return ApiResponse.error("第" + (i + 1) +"个 "+ "文件类型不正确");
            }
            //3.判断文件大小是否符合要求
            int size = (int) files[i].getSize();//获取上传文件大小,返回字节长度1M=1024k=1048576字节 - 文件过大进入controller之前抛出异常 - 前端判断文件大小

            System.out.println("size:" + size);
            if (size > 1024 * 1024) {
                return ApiResponse.error("第" + (i + 1) + "上传文件过大，请上传小于1MB大小的文件");
            }
            //4.将文件重命名，避免文件名相同覆盖文件
            String fileNamePrefix = fileName.substring(0, fileName.lastIndexOf("."));
            fileName = fileNamePrefix + "-" + System.currentTimeMillis() + "." + fileName;//获取上传文件名
            System.out.println("文件名称是"+fileName);
            // TODO:文件名存放数据库
            //5.判断文件夹是否存在
            File targetFile = new File(path + "/" + fileName);
            if (!targetFile.getParentFile().exists()) {
                //不存在创建文件夹
                targetFile.getParentFile().mkdirs();
            }
            try {
                //6.将上传文件写到服务器上指定的文件
                files[i].transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ApiResponse.success("success");
    }

}
