import org.apache.spark.{ SparkConf, SparkContext }

/**
 * Created by dbhatia on 8/11/14.
 */
object WordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("Simple Application")
    val sc = new SparkContext(conf)

    val readmeFile = "/Users/dbhatia/training/spark/code_provided/spark/README.md"
    val changesFile = "/Users/dbhatia/training/spark/code_provided/spark/CHANGES.txt"

    val readmeText = sc.textFile(readmeFile)
    val changesText = sc.textFile(changesFile)

    val numSparksInReadme = readmeText.filter(line => line.contains("Spark"))
    val numSparksInChanges = changesText.filter(line => line.contains("Spark"))

    val result = numSparksInChanges ++ numSparksInReadme

    println("Count = " + result.count)

    sc.stop()
  }

}
