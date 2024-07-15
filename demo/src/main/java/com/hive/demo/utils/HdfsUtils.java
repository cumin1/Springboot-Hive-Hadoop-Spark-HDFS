package com.hive.demo.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class HdfsUtils {
    private static FileSystem hdfs = null;

    @Value("${hdfsPath.uri}")
    static String hdfsPath;
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        Configuration conf=new Configuration();
        try {
            hdfs = FileSystem.get(URI.create(hdfsPath), conf, "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFileAsStream(String fpath, OutputStream out) throws IOException, InterruptedException {
        org.apache.hadoop.fs.Path path = new org.apache.hadoop.fs.Path(fpath);
        FSDataInputStream fsInput = hdfs.open(path);
        IOUtils.copyBytes(fsInput, out, 4096, false);
        fsInput.close();
        out.flush();
    }
}
