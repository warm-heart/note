package org.cloud.note.web;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dto.ApiResponse;


import org.cloud.note.dto.ServiceResult;
import org.cloud.note.exception.UserException;
import org.cloud.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author wangqianlong
 * @create 2019-12-24 17:41
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/updateUserAvatar")
    public ApiResponse<String> file(@RequestParam(name = "file", required = false)
                                            MultipartFile file, HttpServletRequest request) {

        //1.判断文件是否为空(是否上传文件 / 文件内容是否为空)
        if (file == null || file.isEmpty()) {
            return ApiResponse.error("上传头像不可以为空");
        }
        String token = request.getHeader("token");
        ServiceResult serviceResult = userService.upUserAvatar(file, token);
        if (serviceResult.isSuccess())
            return ApiResponse.success((String) serviceResult.getResult());
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping("/uploadIcon")
    public ApiResponse<String> uploadIcon(@RequestParam(name = "file", required = false)
                                                  MultipartFile file, HttpServletRequest request) {

        //1.判断文件是否为空(是否上传文件 / 文件内容是否为空)
        if (file == null || file.isEmpty()) {
            return ApiResponse.error("上传图片不可以为空");
        }
        //获取上传文件名
        String fileName = file.getOriginalFilename();

        /*防止不同浏览器上传出现FileIOException 目标卷不正确*/
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

        //2.判断文件后缀名是否符合要求
        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String Suffix = "png/jpg";

        if (Suffix.indexOf(fileNameSuffix) < 0) {
            return ApiResponse.error("文件类型不正确");
        }

        //3.判断文件大小是否符合要求
        int size = (int) file.getSize();//获取上传文件大小,返回字节长度1M=1024k=1048576字节 - 文件过大进入controller之前抛出异常 - 前端判断文件大小

        if (size > 1024 * 1024) {
            return ApiResponse.error("上传文件过大，请上传小于1MB大小的文件");
        }
        //4.将文件重命名，避免文件名相同覆盖文件
        String fileNamePrefix = fileName.substring(0, fileName.lastIndexOf("."));
        fileName = fileNamePrefix + "-" + System.currentTimeMillis() + "." + fileName;//修改上传文件名
        String url = "http://localhost:8080/Image/" + fileName;


        //5.判断文件夹是否存在
        String path = "E:/userIcon";
        File targetFile = new File(path + "/" + fileName);
        if (!targetFile.getParentFile().exists()) {
            //不存在创建文件夹
            targetFile.getParentFile().mkdirs();
        }
        try {
            //7.将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new UserException("图片上传失败，请重新上传");
        }

        return ApiResponse.success(url);
    }
}
