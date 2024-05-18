package com.hive.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HiveService {

    Object select(String hql);

    List<String> listAllTables();

    List<String> describeTable(String tableName);

    List<String> selectFromTable(String tableName);

    String loadInfoTable();

    String createTable();


}

