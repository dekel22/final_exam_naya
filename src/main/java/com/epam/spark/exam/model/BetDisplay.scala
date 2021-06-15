package com.epam.spark.exam.model

import org.apache.spark.sql
import org.apache.spark.sql.Encoders

case class BetDisplay(bet: Double, eventCurrencyCode:String, eventTime:String, gameName:String, onlineTimeSecs:BigInt,
                      userId:BigInt, win:Double, firstName:String, lastName:String, countryOfOrigin:String,
                      email:String) {


  //

}
