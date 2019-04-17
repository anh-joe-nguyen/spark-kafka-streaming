package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

object Application {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Kafka Streaming")
      .getOrCreate()

    val schema = new StructType(
      Array(StructField("title", StringType),
        StructField("description", StringType),
        StructField("url", StringType),
        StructField("subscribers", LongType)
      )
    )

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "data")
      .load()
      .withColumn("value", col("value").cast("string"))

    df.select(from_json(col("value"), schema).alias("value"))
      .select("value.*")
      .writeStream
      .format("es")
      .option("es.nodes", "localhost:9200")
      .option("es.resource", "subreddit/docs")
      .option("checkpointLocation", "/tmp/checkpoint")
      .start()
      .awaitTermination()
  }
}
