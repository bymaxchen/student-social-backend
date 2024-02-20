package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.util.FileNameUtil;
import com.group1.studentsocialbackend.util.FileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    @RequestMapping(value="/upload",method= {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //定义要上传文件 的存放路径
        //replace the localPath with your own accessible computer path!
        String localPath="/Users/zhangxiaoran/Downloads/";
        //获得文件名字
        String fileName=file.getOriginalFilename();
        fileName= FileNameUtil.getFileName(fileName);
        File dest = new File(localPath + fileName);
        if(FileUploadUtil.upload(file, localPath, fileName)){
            // 将上传的文件写入到服务器端文件夹
            // 获取当前项目运气的完整url
            String requestURL = request.getRequestURL().toString();
            // 获取当前项目的请求路径url
            String requestURI = request.getRequestURI();
            // 得到去掉了uri的路径
            String url = requestURL.substring(0, requestURL.length()-requestURI.length() + 1);
            url="images/"+ fileName;


            return  new ResponseEntity<>(url, HttpStatus.OK);

        }
        // 返回
        return  new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }
}
