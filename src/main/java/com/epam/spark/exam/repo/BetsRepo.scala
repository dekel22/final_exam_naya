package com.epam.spark.exam.repo

import com.epam.spark.exam.model.Bet
import org.apache.spark.sql.{Dataset, Encoder, Encoders, Row, SparkSession}
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * @author Dekel Levitan
 */
@Component
class BetsRepo(sparkSession: SparkSession) {

  @Value("${bets_path}")
  private val betsPath:String = null

  def readBets(): Dataset[Bet] = {
    val enc = Encoders.bean(classOf[Bet])
    sparkSession.read.option("multiline", true).json(betsPath).as(enc)

  }
}
