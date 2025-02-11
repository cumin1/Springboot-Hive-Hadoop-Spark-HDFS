package com.hadoop.demo.controller;

import com.hadoop.demo.entity.ResultBean;
import com.hadoop.demo.service.HdfsService;
import org.springframework.http.MediaType;
//import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/hdfs/file")
public class HdfsController {
    @Value("${hdfsPath.image}")
    String imagePath;

    @Value("${hdfsPath.csv}")
    String csvPath;

    @Value("${hdfsPath.txt}")
    String txtPath;

    @Value("${hdfsPath.video}")
    String videoPath;

    @Value("${hdfsPath.audio}")
    String audioPath;

    @Resource
    HdfsService hdfsService;

    @GetMapping(value = "/findImage",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findImage() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口实现查找hdfs目录：/stiei/image/ 下所有的图片
         */
        List list = hdfsService.find_image_hdfs();

        ResultBean<List<String>> response = new ResultBean<>(200,"查询成功",list);

        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping(value = "/findCsv")
    public ResponseEntity findCsv() throws IOException, URISyntaxException, InterruptedException {
        /*
         此接口用于查找hdfs目录：/stiei/text/csv/ 下所有的csv文件
         */
        List list = hdfsService.find_csv_hdfs();

        ResultBean<List<String>> response = new ResultBean<>(200,"查询成功",list);
        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping(value = "/findTxt")
    public ResponseEntity findTxt() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口用于查找hdfs目录：/stiei/text/txt/ 下所有的txt文件
         */
        List list = hdfsService.find_txt_hdfs();

        ResultBean<List<String>> response = new ResultBean<>(200,"查询成功",list);

        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findVideo")
    public ResponseEntity findVideo() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口用于查找hdfs目录：/stiei/video/ 下所有的视频
         */
        List list = hdfsService.find_video_hdfs();

        ResultBean<List<String>> response = new ResultBean<>(200,"查询成功",list);

        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findAudio")
    public ResponseEntity findAudio() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口用于查找hdfs目录：/stiei/aduio/ 下所有的音频
         */
        List list = hdfsService.find_audio_hdfs();

        ResultBean<List<String>> response = new ResultBean<>(200,"查询成功",list);

        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file)
            throws IOException, URISyntaxException, InterruptedException {
        /*
        该接口实现上传图片至hdfs中的功能，测试好了
         */
        String filename = file.getOriginalFilename();

        String file_tail = filename.substring(filename.length() - 3);
        if (!(file_tail.equals("jpg") || file_tail.equals("png"))) {
            return ResponseEntity.ok(new ResultBean(409,"请上传jpg或png格式的图片",null));
        }

        // 检查imageList中是否已存在filename
        List imageList = hdfsService.find_image_hdfs();
        if (imageList.contains(filename)) {
            return ResponseEntity.ok(new ResultBean(409,"文件已存在",null));
        }

        String hdfsPath = imagePath + filename;
        // 使用Hadoop API将文件写入HDFS
        String message = hdfsService.upload_image(hdfsPath,file);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @PostMapping("/uploadCsv")
    public ResponseEntity uploadCsv(@RequestParam("file") MultipartFile file)
            throws IOException, URISyntaxException, InterruptedException {
        /*
        该接口实现上传csv至hdfs中的功能，测试好了
         */
        String filename = file.getOriginalFilename();

        String file_tail = filename.substring(filename.length() - 3);
        if (!(file_tail.equals("csv"))) {
            return ResponseEntity.ok(new ResultBean(409,"请上传csv格式的文件",null));
        }

        // 检查imageList中是否已存在filename
        List csvList = hdfsService.find_csv_hdfs();
        if (csvList.contains(filename)) {
            return ResponseEntity.ok(new ResultBean(409,"文件已存在",null));
        }

        String filePath = csvPath + filename;
        String message = hdfsService.upload_csv(filePath,file);

        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @PostMapping("/uploadTxt")
    public ResponseEntity uploadTxt(@RequestParam("file") MultipartFile file)
            throws IOException, URISyntaxException, InterruptedException {
        /*
        该接口实现上传txt至hdfs中的功能，测试好了
         */
        String filename = file.getOriginalFilename();

        String file_tail = filename.substring(filename.length() - 3);
        if (!file_tail.equals("txt")) {
            return ResponseEntity.ok(new ResultBean(409,"请上传txt格式的文件",null));
        }

        // 检查imageList中是否已存在filename
        List txtList = hdfsService.find_txt_hdfs();
        if (txtList.contains(filename)) {
            return ResponseEntity.ok(new ResultBean(409,"文件已存在",null));
        }

        String filePath = txtPath + filename;
        String message = hdfsService.upload_txt(filePath,file);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @PostMapping("/uploadVideo")
    public ResponseEntity uploadVideo(@RequestParam("file") MultipartFile file)
        throws IOException, URISyntaxException, InterruptedException {
        /*
        该接口实现上传视频至hdfs中的功能
         */
        String filename = file.getOriginalFilename();
        String file_tail = filename.substring(filename.length() - 3);
        if (!(file_tail.equals("mp4") || file_tail.equals("avi"))) {
            return ResponseEntity.ok(new ResultBean(409,"请上传mp4或avi格式的视频",null));
        }

        // 检查imageList中是否已存在filename
        List videoList = hdfsService.find_video_hdfs();
        if (videoList.contains(filename)) {
            return ResponseEntity.ok(new ResultBean(409,"文件已存在",null));
        }

        String filePath = videoPath + filename;
        String message = hdfsService.upload_video(filePath,file);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @PostMapping("/uploadAudio")
    public ResponseEntity uploadAudio(@RequestParam("file") MultipartFile file)
            throws IOException, URISyntaxException, InterruptedException {
        /*
        该接口实现上传音频至hdfs中的功能
         */
        String filename = file.getOriginalFilename();
        String file_tail = filename.substring(filename.length() - 3);
        if (!(file_tail.equals("mp3")) && !(file_tail.equals("wav"))) {
            return ResponseEntity.ok(new ResultBean(409,"请上传mp3格式的视频",null));
        }

        // 检查imageList中是否已存在filename
        List videoList = hdfsService.find_audio_hdfs();
        if (videoList.contains(filename)) {
            return ResponseEntity.ok(new ResultBean(409,"文件已存在",null));
        }

        String filePath = audioPath + filename;
        String message = hdfsService.upload_audio(filePath,file);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @GetMapping("/deleteImage/{image_name}")
    public ResponseEntity deleteImage(@PathVariable(value = "image_name") String image_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        /*
        此接口用于删除指定图片 测试好了
         */

        // 删除对应图片文件
        String message = hdfsService.delete_image(image_name);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @GetMapping("/deleteCsv/{csv_name}")
    public ResponseEntity deleteCsv(@PathVariable(value = "csv_name") String csv_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        /*
        此接口用于删除指定csv文件 测试好了
         */

        String message = hdfsService.delete_csv(csv_name);

        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @GetMapping("/deleteTxt/{txt_name}")
    public ResponseEntity deleteTxt(@PathVariable(value = "txt_name") String txt_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        /*
        此接口用于删除指定txt文件 测试好了
         */

        String message = hdfsService.delete_txt(txt_name);

        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @GetMapping("/deleteVideo/{video_name}")
    public ResponseEntity deleteVideo(@PathVariable(value = "video_name") String video_name,HttpServletRequest request)
        throws URISyntaxException, IOException, InterruptedException {
        String message = hdfsService.delete_video(video_name);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @GetMapping("/deleteAudio/{audio_name}")
    public ResponseEntity deleteAudio(@PathVariable(value = "audio_name") String audio_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        String message = hdfsService.delete_audio(audio_name);
        return ResponseEntity.ok(new ResultBean(200,message,null));
    }

    @Value("${hdfsPath.uri}")
    String BASE_DIR;
    @GetMapping("/getFile/{filename}")
    public String fileDownload(@PathVariable("filename") String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream; charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

            if (fileName.contains("jpg") || fileName.contains("png")) {
                String path = BASE_DIR + "/stiei/image/" + fileName;
                String message = hdfsService.load_image(path,response.getOutputStream());
                return message;
            }else if (fileName.contains("csv")) {
                String path = BASE_DIR + "/stiei/text/csv/" + fileName;
                String message = hdfsService.load_csv(path,response.getOutputStream());
                return message;
            }else if (fileName.contains("txt")) {
                String path = BASE_DIR + "/stiei/text/txt/" + fileName;
                String message = hdfsService.load_txt(path,response.getOutputStream());
                return message;
            }else if (fileName.contains("mp4") || fileName.contains("avi")) {
                String path = BASE_DIR + "/stiei/video/" + fileName;
                String message = hdfsService.load_video(path,response.getOutputStream());
                return message;
            }else if (fileName.contains("mp3") || fileName.contains("wav")) {
                String path = BASE_DIR + "/stiei/audio/" + fileName;
                String message = hdfsService.load_audio(path,response.getOutputStream());
                return message;
            }else {
                return "file not found";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

}


