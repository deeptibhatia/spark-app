/* SimpleApp.scala */

import org.apache.spark.{ SparkConf, SparkContext }

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = if (args.length > 0) {
      println("Using file: " + args(0)); args(0)
    } else // Default to some file on the filesystem for testing
      "/Users/dbhatia/bin/spark-1.0.0-bin-hadoop2/README.md" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
