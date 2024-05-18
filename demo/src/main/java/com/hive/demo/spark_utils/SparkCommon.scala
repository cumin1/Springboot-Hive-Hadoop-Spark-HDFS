package com.hive.demo.spark_utils

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

import java.util.Properties

class SparkCommon {
  def load_data_hive(tableName:String): Unit = {
    System.setProperty("HADOOP_USER_NAME","root")
    Logger.getLogger("org").setLevel(Level.OFF)

    val spark: SparkSession = SparkSession.builder().appName("data_load_to_hive")
      .master("local[*]").enableHiveSupport()
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.sql.storeAssignmentPolicy", "LEGACY")
      .config("spark.sql.legacy.avro.datetimeRebaseModeInWrite", "CORRECTED")
      .config("spark.sql.parquet.datetimeRebaseModeInRead", "CORRECTED")
//      .config("spark.sql.hive.metastore.version","3.1.2")
//      .config("hive.metastore.uris", "thrift://219.228.173.114:9083")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .getOrCreate()


    val dataFrame = spark.read.format("csv")
      .option("header","true")
      .option("inferSchema","true")
      .option("encoding","utf-8") //utf-8
      .load("hdfs://219.228.173.114:9000/stiei/" + tableName)

    dataFrame.show()


//    dataFrame.write.jdbc("jdbc:hive2://219.228.173.114:10000/stiei_rgzn",tableName.dropRight(4),prop)

    dataFrame.write.format("hive").saveAsTable("stiei_rgzn."  + tableName.dropRight(4))


    spark.stop()
  }
}
