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
        //2.获取输入流   从本地文件系统到hdfs
//        FileInputStream fis = new FileInputStream(new File("C:\\Users\\35369\\Desktop\\市赛样题数据\\模拟\\custmer.csv"));
        //3.获取输出流
        FSDataOutputStream fos = fs.create(new Path("/stiei/video/" + file.getOriginalFilename()));

        byte[] bytes = file.getBytes();
        String content = new String(bytes, "GBK"); // 假设文件内容是以GBK编码的

        fos.write(content.getBytes());
        fs.close();
        fos.close();
        //4.流的拷贝
//        IOUtils.copyBytes(fis,fos,configuration);
        //5.关闭资源
//        IOUtils.closeStream(fis);
//        IOUtils.closeStream(fos);
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
    public List find_imgage_hdfs() throws IOException, URISyntaxException {
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
}
