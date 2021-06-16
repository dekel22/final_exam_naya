package com.epam.spark.exam.controllers;

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



    @GetMapping("towCountryPlayers{fromDate}/{toDate}")
    public ResponseEntity<String[]> towCountryPlayers(@PathVariable String from, @PathVariable String to) {
        return(ResponseEntity.ok(fraudReporter.towCountriesPlayers(from,to)));
    }

    @GetMapping("high_win/{fromDate}/{toDate}")
    public ResponseEntity<String[]> highRate(@PathVariable String from, @PathVariable String to) {
        return(ResponseEntity.ok(fraudReporter.winRatioToHigh(from,to)));
    }


    @GetMapping("long_connection{fromDate}/{toDate}")
    public ResponseEntity<String[]> longConnection(@PathVariable String from, @PathVariable String to){
        return(ResponseEntity.ok(fraudReporter.longConnection(from, to)));
    }



    @GetMapping("gameMaxAndMinForWinAndBet/{fromDate}/{toDate}/{game}")
    public ResponseEntity<String[]>  gameMaxAndMinForWinAndBet(@PathVariable String from, @PathVariable String to, @PathVariable String game) {
        return(ResponseEntity.ok(statReporter.gameMaxAndMinForWinAndBet(from,to,"game")));
    }

    @GetMapping("allGameMaxAndMinForWinAndBe//{fromDate}/{toDate}")
    public ResponseEntity<String[]>  allGameMaxAndMinForWinAndBet(@PathVariable String from, @PathVariable String to) {
        return(ResponseEntity.ok(statReporter.allGamesMaxAndMinForProfit(from,to)));
    }


}
