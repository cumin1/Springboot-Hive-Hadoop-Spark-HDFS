package com.hive.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface HdfsService {

    String upload_to_hdfs(String hdfsPath, MultipartFile file) throws IOException, URISyntaxException, InterruptedException;

    List find_csv_hdfs() throws IOException, URISyntaxException, InterruptedException;

    List find_image_hdfs() throws IOException, URISyntaxException, InterruptedException;

    List find_txt_hdfs() throws IOException, URISyntaxException, InterruptedException;

    List find_video_hdfs() throws IOException, URISyntaxException, InterruptedException;

    String upload_image(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException;

    String upload_csv(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException;

    String upload_txt(String hdfsPath, MultipartFile file) throws URISyntaxException, IOException, InterruptedException;

    String upload_video(String hdfsPath,MultipartFile file) throws URISyntaxException, IOException, InterruptedException;

    String delete_image(String image_name) throws URISyntaxException, IOException, InterruptedException;

    String delete_csv(String csv_name) throws URISyntaxException, IOException, InterruptedException;

    String delete_txt(String txt_name) throws URISyntaxException, IOException, InterruptedException;
}
