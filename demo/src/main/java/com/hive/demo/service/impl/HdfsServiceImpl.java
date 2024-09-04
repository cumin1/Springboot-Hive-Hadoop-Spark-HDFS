package com.hive.demo.service.impl;

import com.hive.demo.service.HdfsService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.security.UserGroupInformation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.net.URI;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HdfsServiceImpl implements HdfsService {

    @Value("${hdfsPath.uri}")
    String HDFS_URI;

    @Override
    public String upload_to_hdfs(String hdfsPath, MultipartFile file) throws IOException, URISyntaxException, InterruptedException {
        //1.获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        FSDataOutputStream fos = fs.create(new Path("/stiei/video/" + file.getOriginalFilename()));

        byte[] bytes = file.getBytes();
        String content = new String(bytes, "GBK"); // 假设文件内容是以GBK编码的

        fos.write(content.getBytes());
        fs.close();
        fos.close();
        System.out.println("结束！");


        return "success";
    }

    @Override
    public List find_csv_hdfs() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        Path path = new Path("/stiei/text/csv");
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(path, true);

        List<String> list = new ArrayList<>();
        while (files.hasNext()) {
            FileStatus fileStatus = files.next();
            String fileName = fileStatus.getPath().getName();
            list.add(fileName);
        }

        return list;
    }

    @Override
    public List find_image_hdfs() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        Path path = new Path("/stiei/image");
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(path, true);

        List<String> list = new ArrayList<>();
        while (files.hasNext()) {
            FileStatus fileStatus = files.next();
            String fileName = fileStatus.getPath().getName();
            list.add(fileName);
        }

        return list;

    }

    @Override
    public List find_txt_hdfs() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        Path path = new Path("/stiei/text/txt");
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(path, true);

        List<String> list = new ArrayList<>();
        while (files.hasNext()) {
            FileStatus fileStatus = files.next();
            String fileName = fileStatus.getPath().getName();
            list.add(fileName);
        }

        return list;
    }

    @Override
    public List find_video_hdfs() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");
        Path path = new Path("/stiei/video");
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(path, true);

        List<String> list = new ArrayList<>();
        while (files.hasNext()) {
            FileStatus fileStatus = files.next();
            String fileName = fileStatus.getPath().getName();
            list.add(fileName);
        }

        return list;
    }

    @Override
    public List find_audio_hdfs() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");
        Path path = new Path("/stiei/audio");
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(path, true);

        List<String> list = new ArrayList<>();
        while (files.hasNext()) {
            FileStatus fileStatus = files.next();
            String fileName = fileStatus.getPath().getName();
            list.add(fileName);
        }

        return list;
    }

    @Override
    public String upload_image(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException {
        // 传过来一个路劲 和 图片文件
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        byte[] fileContext = file.getBytes();

        Path path =  new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload image success";
    }

    @Override
    public String upload_csv(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException {
        // 传过来一个路径 和 图片文件
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        byte[] fileContext = file.getBytes();

        Path path =  new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload csv success";
    }

    @Override
    public String upload_txt(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException {
        // 传过来一个路径 和 图片文件
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration,"root");

        byte[] fileContext = file.getBytes();

        Path path =  new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload txt success";
    }

    @Override
    public String upload_video(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException {
        /*
         * 传过来一个路径 和 视频文件
         */
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
        byte[] fileContext = file.getBytes();
        Path path = new Path(hdfsPath);
        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload video success";
    }

    @Override
    public String upload_audio(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException {
        /*
         * 传过来一个路径 和 音频文件
         */
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
        byte[] fileContext = file.getBytes();
        Path path = new Path(hdfsPath);
        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload audio success";
    }

    @Override
    public String delete_image(String image_name) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");

        Path imagePath = new Path(HDFS_URI + "/stiei/image/" + image_name);
        boolean isDelete = fs.delete(imagePath, true); // 删除指定路径的图片
        fs.close();

        if (!isDelete) {
            // 如果isDeleted为false，则文件或目录不存在
            return "没有该文件，请重新输入";
        } else {
            // 文件已成功删除
            return "文件已成功删除";
        }
    }

    @Override
    public String delete_csv(String csv_name) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
        Path csvPath = new Path(HDFS_URI + "/stiei/text/csv/" + csv_name);
        boolean isDelete = fs.delete(csvPath, true); // 删除指定路径的csv文件
        fs.close();
        if (!isDelete) {
            // 如果isDeleted为false，则文件或目录不存在
            return "没有该文件，请重新输入";
        } else {
            // 文件已成功删除
            return "文件已成功删除";
        }
    }

    @Override
    public String delete_txt(String txt_name) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
        Path txtPath = new Path(HDFS_URI + "/stiei/text/txt/" + txt_name);
        boolean isDelete = fs.delete(txtPath, true); // 删除指定路径的txt文件
        fs.close();
        if (!isDelete) {
            // 如果isDeleted为false，则文件或目录不存在
            return "没有该文件，请重新输入";
        } else {
            // 文件已成功删除
            return "文件已成功删除";
        }
    }

    @Override
    public String delete_video(String video_name) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
        Path videoPath = new Path(HDFS_URI + "/stiei/video/" + video_name);
        boolean isDelete = fs.delete(videoPath, true);
        fs.close();
        if (!isDelete) {
            // 如果isDeleted为false，则文件或目录不存在
            return "没有该文件，请重新输入";
        }  else {
            // 文件已成功删除
            return "文件已成功删除";
        }
    }

    @Override
    public String delete_audio(String audio_name) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
        Path videoPath = new Path(HDFS_URI + "/stiei/audio/" + audio_name);
        boolean isDelete = fs.delete(videoPath, true);
        fs.close();
        if (!isDelete) {
            // 如果isDeleted为false，则文件或目录不存在
            return "没有该文件，请重新输入";
        }  else {
            // 文件已成功删除
            return "文件已成功删除";
        }
    }

    @Override
    public String load_image(String path, ServletOutputStream outputStream) throws URISyntaxException, IOException, InterruptedException
    {
        try {
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
            Path imagePath = new Path(path);
            FSDataInputStream fsInput = fs.open(imagePath);
            IOUtils.copyBytes(fsInput, outputStream, 4096, false);
            fsInput.close();
            outputStream.flush();
            return "文件下载成功";
        }catch (Exception e){
            return "文件下载错误";
        }

    }

    @Override
    public String load_csv(String path, ServletOutputStream outputStream) throws URISyntaxException, IOException, InterruptedException {
        try {
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
            Path csvPath = new Path(path);
            FSDataInputStream fsInput = fs.open(csvPath);
            IOUtils.copyBytes(fsInput, outputStream, 4096, false);
            fsInput.close();
            outputStream.flush();
            return "文件下载成功";
        }catch (Exception e){
            return "文件下载错误";
        }
    }

    @Override
    public String load_txt(String path, ServletOutputStream outputStream) throws URISyntaxException, IOException, InterruptedException {
        try {
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
            Path txtPath = new Path(path);
            FSDataInputStream fsInput = fs.open(txtPath);
            IOUtils.copyBytes(fsInput, outputStream, 4096, false);
            fsInput.close();
            outputStream.flush();
            return "文件下载成功";
        }catch (Exception e){
            return "文件下载错误";
        }
    }

    @Override
    public String load_video(String path, ServletOutputStream outputStream) throws URISyntaxException, IOException, InterruptedException {
        try {
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
            Path videoPath = new Path(path);
            FSDataInputStream fsInput = fs.open(videoPath);
            IOUtils.copyBytes(fsInput, outputStream, 4096, false);
            fsInput.close();
            outputStream.flush();
            return "文件下载成功";
        }catch (Exception e){
            return "文件下载错误";
        }
    }

    @Override
    public String load_audio(String path, ServletOutputStream outputStream) throws URISyntaxException, IOException, InterruptedException {
        try {
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration,"root");
            Path audioPath = new Path(path);
            FSDataInputStream fsInput = fs.open(audioPath);
            IOUtils.copyBytes(fsInput, outputStream, 4096, false);
            fsInput.close();
            outputStream.flush();
            return "文件下载成功";
        }catch (Exception e){
            return "文件下载错误";
        }
    }
}
