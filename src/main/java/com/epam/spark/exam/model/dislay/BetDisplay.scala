package com.epam.spark.exam.model.dislay

import lombok.Data

@Data
case class BetDisplay(bet: Double, eventCurrencyCode: String, eventTime: String, gameName: String, onlineTimeSecs: BigInt,
                      userId: BigInt, win: Double, firstName: String, lastName: String, countryOfOrigin: String,
                      email: String) {


  //

}
