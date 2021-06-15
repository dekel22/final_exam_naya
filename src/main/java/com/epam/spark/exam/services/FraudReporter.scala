package com.epam.spark.exam.services

import com.epam.spark.exam.model.{BetDisplay}
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.spark.sql
import org.apache.spark.sql.{Dataset, Encoders, Row}
import org.apache.spark.sql.functions.col
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.format.DateTimeFormatter
import java.util.Locale

@Component
class FraudReporter {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
  @Autowired
  val bets :BetsEnrichmentator=null;

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
  val betsInDollar = "betInDollar"
  val winInDollar = "winInDollar"
  val eventCurrencyCode = "eventCurrencyCode"
  val onlineTimeSecs = "onlineTimeSecs"





  def towCountriesPlayers(from:String, to:String): Dataset[Row] = {
    bets.workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'").dropDuplicates(userId, "eventCountry").groupBy(userId).count().filter("`count` > 1");

  }



  def winRatioToHigh(from:String, to:String): Dataset[Row] = {
    val sums=bets.workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'").
    groupBy(userId).sum(winInDollar,betsInDollar).orderBy(col(userId));
    sums.withColumn("ratio", col("sum(winInDollar)") / col("sum(betInDollar)")).
    filter(col("ratio")>10)
  }



  def longConnection(from:String, to:String): java.util.List[BetDisplay]= {
    val suspiciousActivityEncoder: sql.Encoder[BetDisplay] = Encoders.product[BetDisplay]

    bets.workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'").
        filter(col(onlineTimeSecs)>18000).as[BetDisplay](suspiciousActivityEncoder).collectAsList()

  }


}
