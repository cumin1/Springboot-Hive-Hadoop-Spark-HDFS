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
@RequestMapping(value = "/hive")
public class HiveController {

    @Resource
    HiveService hiveService;

    @Resource
    HdfsService hdfsService;

    @GetMapping(value = "/stiei")
    public ResponseEntity getTs(){
        ResponseEntity responseEntity = new ResponseEntity("{stiei_rgzn}",HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/find")
    public ResponseEntity getFind(HttpServletRequest request){
        String tableName = request.getParameter("tableName");
        List<String> strings = hiveService.selectFromTable(tableName);
        ResponseEntity responseEntity = new ResponseEntity(strings,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/create")
    public ResponseEntity createData(HttpServletRequest request){
        String table = hiveService.createTable();
        ResponseEntity responseEntity = new ResponseEntity(table,HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException, InterruptedException {
        // 假设HDFS的URI和配置已经设置好了
        String hdfsPath = "hdfs://219.228.173.114:9000/stiei/";
        // 使用Hadoop API将文件写入HDFS
        // ... 写入HDFS的代码 ...
        System.out.println(file.getOriginalFilename());

        hdfsService.upload_to_hdfs(hdfsPath,file);

        // 调用Spark程序将CSV导入Hive
        // ... 调用Spark程序的代码（可能是一个外部脚本或Java API）...
        SparkCommon sparkCommon = new SparkCommon();
        sparkCommon.load_data_hive(file.getOriginalFilename());


        return ResponseEntity.ok("File uploaded successfully and imported to Hive");
    }


    @GetMapping(value = "/list") // 该接口可以查看stiei_rgzn库下的所有表名
    public ResponseEntity listAllTables(){
        List<String> s = hiveService.listAllTables();
        ResponseEntity responseEntity = new ResponseEntity(s,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/describe") // 该接口可以查看stiei_rgzn库下的所有表名
    public ResponseEntity describeTables(HttpServletRequest request){
        String tableName = request.getParameter("tableName");
        List<String> s = hiveService.describeTable(tableName);
        ResponseEntity responseEntity = new ResponseEntity(s,HttpStatus.OK);
        return responseEntity;
    }


}
