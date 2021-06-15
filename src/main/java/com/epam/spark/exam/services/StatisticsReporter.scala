package com.epam.spark.exam.services

import org.apache.spark.sql.functions.{avg, col, max, min}
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
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
  val betsInDollar = "bet in dollar"
  val winInDollar = "win in dollar"
  val eventCurrencyCode = "eventCurrencyCode"
  var gamesStats: Dataset[Row] = null
  private var profit: String = "profit"

  def gameMaxAndMinForWinAndBet(from:String, to:String,game:String): Dataset[Row] = {
    val gameBets=bets.workingBets.filter(col(gameName)=== game).filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'")
    gameBets.agg(min(col(betsInDollar)),max(col(betsInDollar)),avg(col(betsInDollar)),min(col(winInDollar)),max(col(winInDollar)),avg(col(winInDollar)))
  }


  def gameMaxAndMinForProfit(from:String, to:String, game:String): Dataset[Row] = {
    val gameBets=bets.workingBets.filter(col(gameName)=== game).filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'")
    gameBets.agg(min(profit),max(profit))
  }

  def allGamesMaxAndMinForProfit(from:String, to:String): Dataset[Row] = {
    gamesStats=bets.workingBets.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'")
    .agg(col(gameName),min(col(betsInDollar)),max(col(betsInDollar)),avg(col(betsInDollar)),min(col(winInDollar)),max(col(winInDollar)),avg(col(winInDollar)),min(profit),max(profit)).persist()
    gamesStats
  }

}
