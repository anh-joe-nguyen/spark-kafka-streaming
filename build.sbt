
name := "spark-kafka-streaming"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.1" % "provided"
libraryDependencies += "org.elasticsearch" %% "elasticsearch-spark-20" % "6.7.1" % "provided"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}