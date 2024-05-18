package com.hive.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface HdfsService {

    String upload_to_hdfs(String hdfsPath, MultipartFile file) throws IOException, URISyntaxException, InterruptedException;

}
