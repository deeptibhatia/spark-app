package conf

import org.apache.spark.streaming.{ Seconds, StreamingContext }
import org.apache.spark.{ SparkConf, SparkContext }

object Configuration {
  private val ContextMaster = "local"
  private val AppName = "My Application"
  val sparkConf = new SparkConf().setMaster(ContextMaster).setAppName(AppName)

}

/**
 * Created by dbhatia on 8/11/14.
 */
object SCFactory {

  val sc = new SparkContext(Configuration.sparkConf)
  val ssc = new StreamingContext(Configuration.sparkConf, Seconds(1))

}
