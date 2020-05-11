package com.woniuxy.controller;

import cn.hutool.core.io.FileUtil;
import com.woniuxy.util.DateUtil;
import com.woniuxy.util.FileAppUtils;
import com.woniuxy.util.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;

@RestController

public class UploadController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private  String uploadDir;
    @PostMapping("/uploadImage.do")
    public ResultDto uploadImage(@RequestParam("headImage") MultipartFile headImage, HttpServletRequest request){
        //找到到服务器上的目录
        String uploadDir=request.getServletContext().getRealPath("/static/upload/");

        //获取文件的后缀
        String fileSuffix=headImage.getOriginalFilename().substring(headImage.getOriginalFilename().lastIndexOf(".")+1);
        logger.info("后缀名:"+fileSuffix);
        //构建时间戳
        String fileName= "img_"+ DateUtil.timeStamp(new Date())+"."+fileSuffix;
        String imageFile=uploadDir+ File.separator+fileName;
        logger.info("图片的路径:"+imageFile);
        logger.info("文件的路径:"+fileName);
        try {
            headImage.transferTo(new File(fileName));
            ResultDto dto=new ResultDto();
            dto.setCode(1003);
            dto.setMsg("上传成功");
            dto.setData(fileName);
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultDto.UPLOAD_FILE_FAILURE;
        }
    }
    @PostMapping("/uploadImage2.do")
    public ResultDto uploadImage2(@RequestParam("headImage") MultipartFile headImage, HttpServletRequest request){
        //读取设置的图片的文件夹
        String dirPath= FileAppUtils.UPLOAD_PATH;
        File dirFile=new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        //获取文件的后缀
        String fileSuffix=headImage.getOriginalFilename().substring(headImage.getOriginalFilename().lastIndexOf(".")+1);
        logger.info("后缀名:"+fileSuffix);
        //构建时间戳
        String fileName= "img_"+ DateUtil.timeStamp(new Date())+"."+fileSuffix;

        logger.info("文件的路径:"+fileName);
        try {
            headImage.transferTo(new File(dirFile,fileName));
            ResultDto dto=new ResultDto();
            dto.setCode(1003);
            dto.setMsg("上传成功");
            dto.setData(fileName);
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultDto.UPLOAD_FILE_FAILURE;
        }
    }
    //显示图片的方法
    @RequestMapping(value = "showImage")
    public ResponseEntity<Object> showImage(String imageFileName){
        //创建文件对象
        File file=new File(FileAppUtils.UPLOAD_PATH,imageFileName);
        if(file.exists()){
            byte[] bytes=null;
            bytes= FileUtil.readBytes(file);
            //创建封装响应头信息对象
            HttpHeaders headers=new HttpHeaders();
            //封装响应头类型（APPLICATION_OCTET_STREAM 响应的内容不限定）
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            ResponseEntity<Object> entity=new ResponseEntity<Object>(bytes,headers, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }







}
