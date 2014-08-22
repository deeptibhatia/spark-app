import conf.SCFactory
import org.apache.spark.SparkContext._

case class Register(d: java.util.Date, uuid: String, cust_id: String, lat: Float, lng: Float)

case class Click(d: java.util.Date, uuid: String, landing_page: Int)

object ClickApp {
  def main(args: Array[String]) {

    val sc = SCFactory.sc

    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")

    // Key value rdd
    val reg = sc.textFile("/Users/dbhatia/training/spark/code_provided/data/join/reg.tsv").map(_.split("\t")).map(
      r => (r(1), Register(format.parse(r(0)), r(1), r(2), r(3).toFloat, r(4).toFloat))
    )

    val clk = sc.textFile("/Users/dbhatia/training/spark/code_provided/data/join/clk.tsv").map(_.split("\t")).map(
      r => (r(1), Click(format.parse(r(0)), r(1), r(2).toInt))
    )

    val results = reg.join(clk).take(2)

    results.foreach(println _)
  }
}
