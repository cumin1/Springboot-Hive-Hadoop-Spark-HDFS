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

查看hdfs下所有的文件(GET)：
* /hdfs/file/findImage
* /hdfs/file/findCsv
* /hdfs/file/findTxt
* /hdfs/file/findVideo

上传文件至hdfs(POST)； 
* /hdfs/file/uploadImage
* /hdfs/file/uploadCsv
* /hdfs/file/uploadTxt
* /hdfs/file/uploadVideo

删除文件(POST)： 
* /hdfs/file/deleteImage
* /hdfs/file/deleteCsv
* /hdfs/file/deleteTxt
* /hdfs/file/deleteVideo

* 将文件导出(POST)： /hdfs/file/loadCsv