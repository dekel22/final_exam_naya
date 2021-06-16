package com.epam.spark.exam.model.dislay

import org.apache.spark.sql.types.Decimal
case class GameProfit(gameName: String, min_profit: Decimal, max_profit: Decimal, avg_profit: Decimal)
