spark-app
=========

Setup to run a spark application locally
1. Install spark from http://spark.apache.org/downloads.html
2. Add <PATH-TO-SPARK>/bin to the default path e.g. I unpacked spark in the ~/bin directory, so my PATH-TO-SPARK is ~/bin/spark-1.0.0-bin-hadoop2
3. In src/main/scala/SimpleApp.scala: update the file name to any file on the local file system. You could also pass the filename as an argument (in step 5 below)
4. sbt update, sbt package to generate the jar: target/scala-2.10/spark-app_2.10-1.0.jar
5.  To run 
spark-submit --class "SimpleApp" --master local[4] target/scala-2.10/spark-app_2.10-1.0.jar

To supply the filename externally just add it as a command line argument at the end of the command above. e.g. 
spark-submit --class "SimpleApp" --master local[4] target/scala-2.10/spark-app_2.10-1.0.jar "/Users/dbhatia/opensource/github/spark-app/LICENSE"


Generated output 
...
Lines with a: 8, Lines with b: 6



