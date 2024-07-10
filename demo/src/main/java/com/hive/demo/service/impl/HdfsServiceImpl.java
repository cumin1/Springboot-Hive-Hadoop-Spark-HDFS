package com.hive.demo.service.impl;

import com.hive.demo.service.HdfsService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.security.UserGroupInformation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HdfsServiceImpl implements HdfsService {

    private static String HDFS_URI = "hdfs://192.168.96.129:8020";


    @Override
    public String upload_to_hdfs(String hdfsPath, MultipartFile file) throws IOException, URISyntaxException, InterruptedException {
        //1.获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

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
    public List find_csv_hdfs() throws IOException, URISyntaxException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

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
    public List find_image_hdfs() throws IOException, URISyntaxException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

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
    public List find_txt_hdfs() throws IOException, URISyntaxException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

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
    public List find_video_hdfs() throws IOException, URISyntaxException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);
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
    public String upload_image(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException {
        // 传过来一个路劲 和 图片文件
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

        byte[] fileContext = file.getBytes();

        Path path =  new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload image success";
    }

    @Override
    public String upload_csv(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException {
        // 传过来一个路径 和 图片文件
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

        byte[] fileContext = file.getBytes();

        Path path =  new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload csv success";
    }

    @Override
    public String upload_txt(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException {
        // 传过来一个路径 和 图片文件
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), configuration);

        byte[] fileContext = file.getBytes();

        Path path =  new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload txt success";
    }

    @Override
    public String upload_video(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException {
        /*
         * 传过来一个路径 和 视频文件
         */
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration);

        byte[] fileContext = file.getBytes();

        Path path = new Path(hdfsPath);

        FSDataOutputStream fos = fs.create(path);
        fos.write(fileContext);
        fos.close();

        return "upload video success";

    }

    @Override
    public String delete_image(String image_name) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration);

        Path imagePath = new Path("hdfs://192.168.96.129:9000/stiei/image/" + image_name);
        fs.delete(imagePath, true); // 删除指定路径的图片
        fs.close();

        return "delete image success";
    }

    @Override
    public String delete_csv(String csv_name) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration);
        Path csvPath = new Path("hdfs://192.168.96.129:9000/stiei/text/csv/" + csv_name);
        fs.delete(csvPath, true); // 删除指定路径的csv文件
        fs.close();
        return "delete csv success";
    }

    @Override
    public String delete_txt(String txt_name) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI(HDFS_URI),configuration);
        Path txtPath = new Path("hdfs://192.168.96.129:9000/stiei/text/txt/" + txt_name);
        fs.delete(txtPath, true); // 删除指定路径的txt文件
        fs.close();
        return "delete txt success";
    }
}
