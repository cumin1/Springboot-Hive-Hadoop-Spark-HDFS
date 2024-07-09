package com.hive.demo.controller;

import com.hive.demo.service.HdfsService;
import com.hive.demo.service.HiveService;
import com.hive.demo.spark_utils.SparkCommon;

//import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/hdfs/file")
public class HdfsController {

    @Resource
    HdfsService hdfsService;

    @GetMapping(value = "/findImage")
    public ResponseEntity findImage() throws IOException, URISyntaxException {
        /*
        此接口实现查找hdfs目录：/stiei/image/ 下所有的图片
         */
        List list = hdfsService.find_imgage_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findCsv")
    public ResponseEntity findCsv() throws IOException, URISyntaxException {
        /*
         此接口用于查找hdfs目录：/stiei/text/csv/ 下所有的csv文件
         */
        List list = hdfsService.find_csv_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findTxt")
    public ResponseEntity findTxt() throws IOException, URISyntaxException {
        /*
        todo 此接口用于查找hdfs目录：/stiei/text/txt/ 下所有的txt文件
         */
        List list = hdfsService.find_txt_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findVideo")
    public ResponseEntity findVideo() throws IOException, URISyntaxException {
        /*
        todo 此接口用于查找hdfs目录：/stiei/video/ 下所有的视频
         */
        List list = hdfsService.find_video_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException, InterruptedException {
        String filename = file.getOriginalFilename();

        // 假设HDFS的URI和配置已经设置好了
        String hdfsPath = "hdfs://192.168.96.129:9000/stiei/video";

        // 使用Hadoop API将文件写入HDFS
        // ... 写入HDFS的代码 ...
        System.out.println(file.getOriginalFilename());

        hdfsService.upload_to_hdfs(hdfsPath,file);


        return ResponseEntity.ok("video uploaded successfully");

    }

}


