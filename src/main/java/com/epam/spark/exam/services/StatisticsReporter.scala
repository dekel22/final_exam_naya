package com.epam.spark.exam.services

import org.apache.spark.sql.functions.{avg, col, max, min}
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.epam.spark.exam.model.DataRawExtend._
@Component
class StatisticsReporter {
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
  var gamesStats: Dataset[Row] = null
  private var profit: String = "profit"



  def gameMaxAndMinForWinAndBet(from:String, to:String,game:String):Array[String]= {
     bets.workingBets.filter(col(gameName)=== game).filterByDates(from,to).
      agg(min(col(betsInDollar)),max(col(betsInDollar)),avg(col(betsInDollar)),
      min(col(winInDollar)),max(col(winInDollar)),avg(col(winInDollar))).asGameStat().toJsonStrings()
  }

  def gameMaxAndMinForProfit(from:String, to:String, game:String): Array[String]= {
    bets.workingBets.filter(col(gameName)=== game).filterByDates(from,to).agg(min(profit).alias("min_profit"),
      max(profit).alias("max_profit"),avg(profit).alias("avg_profit")).asGameStat().toJsonStrings()
  }

  def allGamesMaxAndMinForProfit(from:String, to:String): Array[String] = {
      gamesStats=bets.workingBets.filterByDates(from,to).groupBy(gameName).agg(min(col(betsInDollar)).
      alias("min_bet"),max(col(betsInDollar)).alias("max_bet"),avg(col(betsInDollar)).alias("avg_bet"),
      min(col(winInDollar)).alias("min_win"),max(col(winInDollar)).alias("max_win"),
      avg(col(winInDollar)).alias("avg_win"),min(profit).alias("min_profit"),
      max(profit).alias("max_profit")).persist()
     gamesStats.asGameStat().toJsonStrings()


  }

}
