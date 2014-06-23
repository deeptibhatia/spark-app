// http://spark.apache.org/docs/latest/quick-start.html#a-standalone-app-in-scala
name := """spark-app"""

version := "1.0" 

scalaVersion := "2.10.4"

libraryDependencies ++= Dependencies.sparkAkkaHadoop

releaseSettings

scalariformSettings

