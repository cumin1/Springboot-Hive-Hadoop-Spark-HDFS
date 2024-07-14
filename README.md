Hadoop平台中存储的文件有图像、文本、视频

图像、视频 将会存到hdfs中
csv存两份：
* hdfs目录下
* hive中 hive库名：rgzn

存储的地方：

图像：hdfs://bigdata1:9000/stiei/image/

文本：hdfs://bigdata1:9000/stiei/text/csv/

hdfs://bigdata1:9000/stiei/text/txt/

视频：hdfs://bigdata1:9000/stiei/video/

需要实现的接口：
(统一以json格式返回)

查看hdfs下所有的文件(GET) 结果会返回一个列表，展示所有文件：
* /hdfs/file/findImage(已实现)
* /hdfs/file/findCsv(已实现)
* /hdfs/file/findTxt(已实现)
* /hdfs/file/findVideo(已实现)

上传文件至hdfs(POST)； 
* /hdfs/file/uploadImage(已实现)
* /hdfs/file/uploadCsv(已实现)
* /hdfs/file/uploadTxt(已实现)
* /hdfs/file/uploadVideo(已实现)

删除文件(POST)： 
* /hdfs/file/deleteImage(已实现)
* /hdfs/file/deleteCsv(已实现)
* /hdfs/file/deleteTxt(已实现)
* /hdfs/file/deleteVideo(已实现)

下载文件(Get)： /hdfs/file/getFile/{filename}