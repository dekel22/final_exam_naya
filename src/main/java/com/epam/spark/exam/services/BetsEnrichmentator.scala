package com.epam.spark.exam.services
import com.epam.spark.exam.repo.BetsRepo
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.springframework.context.event.{ContextRefreshedEvent, EventListener}
import org.springframework.stereotype.Component
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.Decimal


import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * @author Dekel Levitan
 */
@Component
class BetsEnrichmentator(@transient userService: UserService, @transient betsRepo: BetsRepo, @transient sparkSession: SparkSession) extends Serializable {

  var workingBets: Dataset[Row] = null
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

  val countryOfOrigin: String = "countryOfOrigin"
  val gameName: String = "gameName"
  val USD: String = "USD"
  val EUR: String = "EUR"
  val EVENT_CURRENCY_CODE: String = "EVENT_CURRENCY_CODE"
  val CURRENCY_CODE: String = "CURRENCY_CODE"
  val userId: String = "UserId"
  val win: String ="win"
  val bet: String = "bet"
  val CONVERSION_RATE: Double = 1.1
  val betsInDollar = "bet in dollar"
  val winInDollar = "win in dollar"
  val eventCurrencyCode = "eventCurrencyCode"


  @EventListener(Array(classOf[ContextRefreshedEvent]))
  def calcBetsExtract(): Unit = {

    //אני בוחר להגדיל את הטבלה ולהאט ביצועים בשביל קוד קריא יתר
    import com.epam.spark.exam.model.UserAdapter._
    val betsDS = betsRepo.readBets()
    val winsInDollarsDS=betsDS.withColumn(winInDollar, when(col(eventCurrencyCode).equalTo(EUR), (col(win).multiply(CONVERSION_RATE))).otherwise(col(bet)).cast("decimal(38,0)"))
    val betsInDollarDS=winsInDollarsDS.withColumn(betsInDollar, when(col(eventCurrencyCode).equalTo(EUR), (col(bet).multiply(CONVERSION_RATE))).otherwise(col(bet)).cast("decimal(38,0)"))
    val users = userService.getAllUsers
    val usersDf = sparkSession.createDataFrame(users)
    val allBets = betsInDollarDS.join(usersDf, betsInDollarDS("userId") === usersDf("id")).drop("id")
    val allBetsWithProfit= allBets.withColumn("profit", col(winInDollar) - col(betsInDollar))
    workingBets = allBetsWithProfit.filter(col(countryOfOrigin).notEqual("USA") || not(col(gameName).contains("demo"))).persist()

  }



  def towCountriesPlayers(from:String, to:String): Dataset[Row] = {
    workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'").dropDuplicates(userId, "eventCountry").groupBy(userId).count().filter("`count` > 1");

  }



  def winRatioToHigh(from:String, to:String): Dataset[Row] = {
    val sums=workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'").
      groupBy(userId).sum(winInDollar,betsInDollar).orderBy(col(userId));
    return(sums.withColumn("ratio", col("sum(win in dollar)") / col("sum(bet in dollar)")).
      filter(col("ratio")>10));
  }



  def longConnection(from:String, to:String): Dataset[Row] = {
    val onlineTimeSecs = "onlineTimeSecs"
    return(workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'").filter(col(onlineTimeSecs)>18000));

  }

}