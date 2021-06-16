package com.epam.spark.exam.model

import com.epam.spark.exam.model.dislay.{BetDisplay, GameStats, UserDisplay}
import com.google.gson.Gson
import org.apache.spark.sql
import org.apache.spark.sql.{Dataset, Encoders, Row}

object DataRawExtend {
  val betsEncoder: sql.Encoder[BetDisplay] = Encoders.product[BetDisplay]
  val userEncoder: sql.Encoder[UserDisplay] = Encoders.product[UserDisplay]
  val gameStatEncoder:sql.Encoder[GameStats] = Encoders.product[GameStats]
  val gson = new Gson

  implicit class DataRawImprovements(val s: Dataset[Row]) {
    def filterByDates(from:String,to:String): Dataset[Row]=s.filter("eventTime > date'" + from + "' and eventTime < date'" + to + "'")
    def asBets():Array[BetDisplay]=s.as[BetDisplay](betsEncoder).collect()
    def asGameStat():Array[GameStats]=s.as[GameStats](gameStatEncoder).collect()
    def getUsers():Array[UserDisplay]=s.select("userId").as[UserDisplay](userEncoder).collect()
  }

  implicit class BetArrayImprove(val bets: Array[BetDisplay]){
    def toJsonString():Array[String]=bets.map(x=>gson.toJson(x).toString)
  }
  implicit class UsersArrayImprove(val user: Array[UserDisplay]){
    def toJsonStrings():Array[String]=user.map(x=>gson.toJson(x).toString)
  }
  implicit class gamesStatArrayImprove(val gameStat: Array[GameStats]){
    def toJsonStrings():Array[String]=gameStat.map(x=>gson.toJson(x).toString)
  }
}


