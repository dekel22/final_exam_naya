package com.epam.spark.exam.controllers;

import com.epam.spark.exam.model.BetDisplay;
import com.epam.spark.exam.services.FraudReporter;
import com.epam.spark.exam.services.StatisticsReporter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dekel Levitan
 */
@RestController
@RequestMapping("/bets/")
public class BetsController {

    @Autowired
    private FraudReporter fraudReporter;

    @Autowired
    private StatisticsReporter statReporter;
    String from= "2015-03-01T10:50:39 -03:00";
    String to= "2015-03-22T10:50:39 -03:00";

    @GetMapping("2countries/{fromDate}/{toDate}")
    public Dataset<Row> towCountryPlayers(@PathVariable String from, @PathVariable String to) {
        return (fraudReporter.towCountriesPlayers(from,to));
    }

    @GetMapping("high_win/{fromDate}/{toDate}")
    public void  highRate(@PathVariable String from, @PathVariable String to) {
        fraudReporter.winRatioToHigh(from,to);
    }


    @GetMapping("long_connection")//{fromDate}/{toDate}")
    public ResponseEntity<List<BetDisplay>> longConnection(){//@PathVariable String from, @PathVariable String to) {
        return(ResponseEntity.ok(fraudReporter.longConnection(from, to)));
    }




    private String RawtoJavaObj(Row x) {
        return(x.getAs("gameNAme"));
    }

    @GetMapping("gameMaxAndMinForWinAndBet/{fromDate}/{toDate}/{game}")
    public void  gameMaxAndMinForWinAndBet(@PathVariable String from, @PathVariable String to, @PathVariable String game) {
        statReporter.gameMaxAndMinForWinAndBet(from,to,"game");
    }

    @GetMapping("allGamesMaxAndMinForWinAndBet{fromDate}/{toDate}")
    public void  allGameMaxAndMinForWinAndBet(@PathVariable String from, @PathVariable String to) {
        statReporter.allGamesMaxAndMinForProfit(from,to).collectAsList();

    }


}
