package com.arthas.selenium.elorating;


import java.util.Date;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wytsang
 */
public class GameRecord {
    
    private final static Logger logger= Logger.getLogger(GameRecord.class.getName());
    
    private int season;
    private String opponent;
    private Schedule week;
    private String gameDate;
    private String elo;
    
    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public Schedule getWeek() {
        return week;
    }

    public void setWeek(Schedule week) {
        this.week = week;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Season: "+season+", ");
        sb.append("Week: "+week.getWeek()+", ");
        sb.append("Date: "+gameDate+", ");
        sb.append("Opponent: "+opponent+", ");
        sb.append("Elo: "+elo+"\n");
        return sb.toString();
    }
    
}