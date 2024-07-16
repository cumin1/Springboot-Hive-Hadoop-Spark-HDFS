package com.hive.demo.controller;

import com.hive.demo.service.HdfsService;

//import org.apache.tools.ant.taskdefs.condition.Http;
import com.hive.demo.utils.HdfsUtils;
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

    @Resource
    HdfsService hdfsService;

    @GetMapping(value = "/findImage")
    public ResponseEntity findImage() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口实现查找hdfs目录：/stiei/image/ 下所有的图片
         */
        List list = hdfsService.find_image_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findCsv")
    public ResponseEntity findCsv() throws IOException, URISyntaxException, InterruptedException {
        /*
         此接口用于查找hdfs目录：/stiei/text/csv/ 下所有的csv文件
         */
        List list = hdfsService.find_csv_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findTxt")
    public ResponseEntity findTxt() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口用于查找hdfs目录：/stiei/text/txt/ 下所有的txt文件
         */
        List list = hdfsService.find_txt_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/findVideo")
    public ResponseEntity findVideo() throws IOException, URISyntaxException, InterruptedException {
        /*
        此接口用于查找hdfs目录：/stiei/video/ 下所有的视频
         */
        List list = hdfsService.find_video_hdfs();

        ResponseEntity responseEntity = new ResponseEntity(list,HttpStatus.OK);
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
            return ResponseEntity.badRequest().body("请上传jpg或png格式的图片");
        }

        String hdfsPath = imagePath + filename;

        // 使用Hadoop API将文件写入HDFS
        String message = hdfsService.upload_image(hdfsPath,file);

        return ResponseEntity.ok(message);
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
            return ResponseEntity.badRequest().body("请上传csv格式的文件");
        }

        String filePath = csvPath + filename;

        String message = hdfsService.upload_csv(filePath,file);

        return ResponseEntity.ok(message);
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
            return ResponseEntity.badRequest().body("请上传txt格式的文件");
        }
        String filePath = txtPath + filename;
        String message = hdfsService.upload_txt(filePath,file);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/uploadVideo")
    public ResponseEntity uploadVideo(@RequestParam("file") MultipartFile file)
        throws IOException, URISyntaxException, InterruptedException {
        /*
        todo 该接口实现上传视频至hdfs中的功能，还没测过
         */
        String filename = file.getOriginalFilename();
        String file_tail = filename.substring(filename.length() - 3);
        if (!(file_tail.equals("mp4") || file_tail.equals("avi"))) {
            return ResponseEntity.badRequest().body("请上传mp4或avi格式的视频");
        }

        String filePath = videoPath + filename;
        String message = hdfsService.upload_video(filePath,file);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/deleteImage/{image_name}")
    public ResponseEntity deleteImage(@PathVariable(value = "image_name") String image_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        /*
        此接口用于删除指定图片 测试好了
         */

        // 删除对应图片文件
        String message = hdfsService.delete_image(image_name);

        return ResponseEntity.ok(message);
    }


    @GetMapping("/deleteCsv/{csv_name}")
    public ResponseEntity deleteCsv(@PathVariable(value = "csv_name") String csv_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        /*
        此接口用于删除指定csv文件 测试好了
         */

        String message = hdfsService.delete_csv(csv_name);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/deleteTxt/{txt_name}")
    public ResponseEntity deleteTxt(@PathVariable(value = "txt_name") String txt_name,HttpServletRequest request)
            throws URISyntaxException, IOException, InterruptedException {
        /*
        此接口用于删除指定txt文件 测试好了
         */

        String message = hdfsService.delete_txt(txt_name);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/deleteVideo/{video_name}")
    public ResponseEntity deleteVideo(@PathVariable(value = "video_name") String video_name,HttpServletRequest request)
        throws URISyntaxException, IOException, InterruptedException {
        String message = hdfsService.delete_video(video_name);
        return ResponseEntity.ok(message);
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
            }else {
                return "file not found";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }




}


