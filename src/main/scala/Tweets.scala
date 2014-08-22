import conf.SCFactory

/**
 * Created by dbhatia on 8/12/14.
 */
object Tweets {
  def main(args: Array[String]) {
    val sc = SCFactory.sc

    // load some tweets
    val tweets = sc.textFile("/Users/dbhatia/Downloads/minitweets")
    tweets.take(5)

    // pretty-print the data that's in JSON format
    import com.fasterxml.jackson.databind.ObjectMapper

    def prettyPrint(str: String) = {
      val mapper = new ObjectMapper()
      val prettyPrinter = mapper.writerWithDefaultPrettyPrinter()
      val obj = mapper.readValue(str, classOf[java.util.Map[String, Object]])
      println(prettyPrinter.writeValueAsString(obj))
    }

    tweets.take(5).foreach(prettyPrint(_))

    // sc is an existing SparkContext
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext._

    // create a SchemaRDD from the JSON file
    val tweetTable = sqlContext.jsonFile("/Users/dbhatia/Downloads/minitweets")

    // let's take a look at the schema
    tweetTable.printSchema
    //
    //    // use SQL queries to explore the data
    tweetTable.registerAsTable("tweetTable")

    sql("SELECT text FROM tweetTable LIMIT 10").collect.foreach(println)
    //
    //    sql("SELECT user.name, text, lang FROM tweetTable LIMIT 10").collect.foreach(println)
    //
    //    // which are the top ten languages represented?
    //    sql("SELECT lang, COUNT(*) AS cnt FROM tweetTable GROUP BY lang ORDER BY cnt DESC LIMIT 10").collect.foreach(println)
    //
    //    // feature engineering, based on an ngram approach
    //    val texts = sql("SELECT text FROM tweetTable").map(_.head.toString)
    //
    //    import org.apache.spark.mllib.clustering.KMeans
    //    import org.apache.spark.mllib.linalg.{ Vector, Vectors }
    //
    //    def featurize(s: String): Vector = {
    //      val n = 1000
    //      val result = new Array[Double](n)
    //      val bigrams = s.sliding(2).toArray
    //
    //      for (h <- bigrams.map(_.hashCode % n)) {
    //        result(h) += 1.0 / bigrams.length
    //      }
    //
    //      Vectors.sparse(n, result.zipWithIndex.filter(_._1 != 0).map(_.swap))
    //    }
    //
    //    // test this function
    //    featurize("Hello World!")
    //
    //    // train a model
    //    val vectors = texts.map(featurize).cache()
    //    vectors.count()
    //
    //    val model = KMeans.train(vectors, 10, 20)
    //
    //    // monitor the stages of this unit of work
    //    // http://localhost:4040
    //
    //    // take a look into those clusters
    //    val some_tweets = texts.take(100)
    //
    //    for (i <- 0 until 10) {
    //      println(s"\nCLUSTER $i:")
    //
    //      some_tweets.foreach { t =>
    //        if (model.predict(featurize(t)) == i) {
    //          println(t)
    //        }
    //      }
    //    }
    //
    //    // persist the model to disk, so we can use it for streaming
    //    sc.makeRDD(model.clusterCenters, 10).saveAsObjectFile("tweets-model")

    sc.stop()

  }
}
