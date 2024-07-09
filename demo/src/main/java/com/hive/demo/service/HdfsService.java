package com.hive.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface HdfsService {

    String upload_to_hdfs(String hdfsPath, MultipartFile file) throws IOException, URISyntaxException, InterruptedException;

    List find_csv_hdfs() throws IOException, URISyntaxException;

    List find_imgage_hdfs() throws IOException, URISyntaxException;

    List find_txt_hdfs() throws IOException, URISyntaxException;

    List find_video_hdfs() throws IOException, URISyntaxException;
}
