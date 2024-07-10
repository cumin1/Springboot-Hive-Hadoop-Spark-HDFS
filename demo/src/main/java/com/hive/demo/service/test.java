package com.hive.demo.service;


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

public class test {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String HDFS_URI = "hdfs://192.168.96.129:8020";

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
    }
}
