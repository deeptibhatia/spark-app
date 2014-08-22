import sbt._

object Version {
  val spark     = "1.0.2"
  val hadoop    = "2.4.0"
  val slf4j     = "1.7.6"
  val logback   = "1.1.1"
  val scalaTest = "2.1.7"
  val mockito   = "1.9.5"
  val akka      = "2.3.3"
}

object Library {

  val sparkStreaming = sparklib("spark-streaming")
  val sparkSql       = sparklib("spark-sql")
  val sparkMllib     = sparklib("spark-mllib")
  val akkaActor      = "com.typesafe.akka" %% "akka-actor"      % Version.akka
  val akkaTestKit    = "com.typesafe.akka" %% "akka-testkit"    % Version.akka
  val hadoopClient   = "org.apache.hadoop" %  "hadoop-client"   % Version.hadoop
  val slf4jApi       = "org.slf4j"         %  "slf4j-api"       % Version.slf4j
  val logbackClassic = "ch.qos.logback"    %  "logback-classic" % Version.logback
  val scalaTest      = "org.scalatest"     %% "scalatest"       % Version.scalaTest
  val mockitoAll     = "org.mockito"       %  "mockito-all"     % Version.mockito
//  val sparkSql       = "org.apache.spark"  %% "spark-sql"       % Version.spark
//  val sparkMllib     = "org.apache.spark"  %% "spark-mllib"     % Version.spark

  def sparklib(name: String) = {
    "org.apache.spark"  %% name % Version.spark
  }

}

object Dependencies {

  import Library._

  val sparkAkkaHadoop = Seq(
    sparkMllib,
    sparkSql,
    sparkStreaming,
//    akkaActor,
//    akkaTestKit,
    hadoopClient,
    logbackClassic % "test",
    scalaTest % "test",
    mockitoAll % "test"
  )
}
