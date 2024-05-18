# 学习用

#### 介绍
springboot+hive+Hadoop+Spark+HDFS 集合

目前实现了四个接口
* localhost:8080/hive/find : 可以获取到指定表的所有数据
* localhost:8080/hive/list : 可以查询hive中stiei_rgzn库中的所有表
* localhost:8080/hive/describe ：可以查询指定表的所有字段
* localhost:8080/hive/upload ： 可以通过表单的形式上传csv文件 并将csv文件保存至HDFS 然后同步到hive表中

