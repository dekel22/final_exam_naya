package com.epam.spark.exam.model.dislay

import org.apache.spark.sql.types.Decimal

case class GameStats(gameName: String, min_bet: Decimal, max_bet: Decimal, avg_bet: Decimal, min_win: Decimal, max_win: Decimal, avg_win: Decimal, min_profit: Decimal, max_profit: Decimal)
