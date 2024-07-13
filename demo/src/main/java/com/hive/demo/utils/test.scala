package com.hive.demo.utils

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

import java.util.Properties

object test {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME","root")
    Logger.getLogger("org").setLevel(Level.OFF)

    val spark: SparkSession = SparkSession.builder().appName("test")
      .master("local[*]").enableHiveSupport()
      .config("spark.sql.storeAssignmentPolicy", "LEGACY")
//      .config("spark.sql.hive.metastore.version","3.1.2")
      .config("hive.metastore.uris", "thrift://219.228.173.114:9083")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .getOrCreate()


    val headers = spark.read.format("csv")
      .option("header","true")
      .option("inferSchema","true")
      .option("encoding","utf-8") //utf-8
      .load("hdfs://219.228.173.114:9000/stiei/custmer.csv")
      .limit(1)
      .columns

    val data = spark.read.format("csv")
      .option("inferSchema","true")
      .option("encoding","utf-8") //utf-8
      .load("hdfs://219.228.173.114:9000/stiei/custmer.csv")


//    spark.sql("insert overwrite table stiei_rgzn.custmer select * from dataFrame")
    data.write.format("hive").mode("overwrite").saveAsTable("stiei_rgzn.custmer")

//    data.write.mode("append").jdbc("jdbc:hive2://219.228.173.114:10000/stiei_rgzn","custmer",prop)

    spark.stop()
  }
}
