package com.epam.spark.exam.model;

import java.io.Serializable;

public class Bet implements Serializable {
    public int eventId;
    public String eventTime;
    public String eventCountry;
    public String eventCurrencyCode;
    public int userId;
    public double bet;
    public String gameName;
    public double win;
    public int onlineTimeSecs;
}