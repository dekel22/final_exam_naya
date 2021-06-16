package com.epam.spark.exam.services
import org.apache.spark.sql.functions.col
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import com.epam.spark.exam.model.DataRawExtend._
import java.util.Locale

@Component
class FraudReporter {

  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
  @Autowired
  val bets :BetsEnrichmentator=null


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





  def towCountriesPlayers(from:String, to:String): Array[String] = {
    bets.workingBets.filterByDates(from,to).
      dropDuplicates(userId, "eventCountry").groupBy(userId).count().filter("`count` > 1").getUsers().toJsonStrings()
  }



  def winRatioToHigh(from:String, to:String): Array[String]= {
    val sums=bets.workingBets.filterByDates(from,to).
    groupBy(userId).sum(winInDollar,betsInDollar).orderBy(col(userId))
    sums.withColumn("ratio", col("sum(winInDollar)") / col("sum(betInDollar)")).
    filter(col("ratio")>10).getUsers().toJsonStrings()
  }



  def longConnection(from:String, to:String): Array[String]= {
    bets.workingBets.filterByDates(from,to).
        filter(col(onlineTimeSecs)>18000).asBets().toJsonString()
  }


}
