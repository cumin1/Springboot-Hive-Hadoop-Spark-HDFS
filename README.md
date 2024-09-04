# 快速开始
将项目导入到服务器中，配置相应环境，运行项目即可。

运行前端界面:
```
vue-cli-service serve
```

# 数据存储的地方
图像：hdfs://bigdata1:9870/stiei/image/

文本：hdfs://bigdata1:9870/stiei/text/csv/

hdfs://bigdata1:9000/9870/text/txt/

视频：hdfs://bigdata1:9870/stiei/video/

# 查看所有图片
方法：GET

uri:  http://localhost:8080/hdfs/file/findImage

请求参数：无

返回结果结构(成功)：
```
{
    "statusCode": 200,
    "message": "查询成功",
    "data": [
        "dimo.jpg",
        "dimo.png",
        "dog1.jpg"
    ]
}
```

# 查看所有CSV
方法：GET

uri: http://localhost:8080/hdfs/file/findCsv

请求参数：无

返回结果结构(成功)：
```
{
    "statusCode": 200,
    "message": "查询成功",
    "data": [
        "online_order.csv",
        "userlostprob.csv"
    ]
}
```

# 查看所有Txt
方法：GET

uri: http://localhost:8080/hdfs/file/findTxt

请求参数：无

返回结果结构(成功):
```
{
    "statusCode": 200,
    "message": "查询成功",
    "data": [
        "sample.txt",
        "userlostprob.txt"
    ]
}
```

# 查看所有视频
方法：GET

uri: http://localhost:8080/hdfs/file/findVideo

请求参数：无

返回结果结构(成功):
```
{
    "statusCode": 200,
    "message": "查询成功",
    "data": [
        "珊瑚.mp4",
        "荷花.mp4"
    ]
}
```

# 查看所有音频
方法：GET

uri: http://localhost:8080/hdfs/file/findAudio

请求参数：无

返回结果结构(成功):
```
{
    "statusCode": 200,
    "message": "查询成功",
    "data": [
        "测试1.mp3",
        "测试2.mp3"
    ]
}
```

# 上传图片到平台
方法：POST

uri: http://localhost:8080/hdfs/file/uploadImage

请求头设置：
```
Content-Type=multipart/form-data
```

请求体设置：
```
参数名： file
参数值：xxx.jpg / xxx.png
参数类型：file
```

返回成功信息：
```
upload image success
```

# 上传csv到平台
方法：POST

uri: http://localhost:8080/hdfs/file/uploadCsv

请求头设置：
```
Content-Type=multipart/form-data
```

请求体设置：
```
参数名： file
参数值：xxx.csv
参数类型：file
```

返回成功信息：
```
upload csv success
```

# 上传txt到平台
方法：POST

uri: http://localhost:8080/hdfs/file/uploadTxt

请求头设置：
```
Content-Type=multipart/form-data
```

请求体设置：
```
参数名： file
参数值：xxx.txt
参数类型：file
```

返回成功信息：
```
upload txt success
```

# 上传视频到平台
方法： POST

uri: http://localhost:8080/hdfs/file/uploadVideo

请求头设置：
```
Content-Type=multipart/form-data
```

请求体设置：
```
参数名： file
参数值：xxx.mp4
参数类型：file
```

返回成功信息:
```
upload video success
```

# 上传音频到平台
方法： POST

uri: http://localhost:8080/hdfs/file/uploadAudio

请求头设置：
```
Content-Type=multipart/form-data
```

请求体设置：
```
参数名： file
参数值：xxx.mp3
参数类型：file
```

返回成功信息:
```
upload audio success
```

# 删除平台中的图片
方法：GET

uri: http://localhost:8080/hdfs/file/deleteImage/{image_name}

请求示例：
```
http://localhost:8080/hdfs/file/deleteImage/dimo.jpg
```

返回成功信息：
```
文件已成功删除
```

# 删除平台的csv
方法：GET

uri： http://localhost:8080/hdfs/file/deleteCsv/{csv_name}

请求示例：
```
http://localhost:8080/hdfs/file/deleteCsv/train.csv
```

返回成功信息：
```
文件已成功删除
```

# 删除平台的Txt
方法： GET

uri: http://localhost:8080/hdfs/file/deleteTxt/{txt_name}

请求示例：
```
http://localhost:8080/hdfs/file/deleteTxt/sample.txt
```

返回成功信息：
```
文件已成功删除
```

# 删除平台的视频
方法： GET

uri: http://localhost:8080/hdfs/file/deleteVideo{video_name}

请求示例：
```
http://localhost:8080/hdfs/file/deleteVideo/xxx.mp4
```

# 删除平台的音频
方法： GET

uri: http://localhost:8080/hdfs/file/deleteAudio{audio_name}

请求示例：
```
http://localhost:8080/hdfs/file/deleteAudio/xxx.mp3
```


# 下载平台文件
方法：GET

uri: http://localhost:8080/hdfs/file/getFile/{filename}





