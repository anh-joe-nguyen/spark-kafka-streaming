# spark-kafka-streaming
Listen kafka -> parse message -> push to elasticsearch

## Build
`sbt clean assembly`

## Run
`$SPARK_HOME/bin/spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.1,org.elasticsearch:elasticsearch-spark-20_2.11:6.7.1 --master yarn --class com.example.Application /path/to/file.jar`
