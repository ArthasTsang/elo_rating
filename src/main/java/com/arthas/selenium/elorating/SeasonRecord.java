package com.arthas.selenium.elorating;


import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wytsang
 */
public class SeasonRecord {
    
    private int season;
    private ArrayList<GameRecord> games= new ArrayList<GameRecord>();
    
    public SeasonRecord(int season){
        this.season= season;
    }
    
    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }    
    
    public void addGameRecord(GameRecord gmRecord){
        Schedule week= gmRecord.getWeek();     
        boolean exists= false;
        int size= games.size();
        for(int i=0; !exists && i<size; i++){
            GameRecord g= games.get(i);
            if(week.compareTo(g.getWeek())==0){
                exists= true;
            }else if(week.compareTo(g.getWeek())<0){
                games.add(i, gmRecord);
                break;
            }
        }
        if(!exists){
            games.add(gmRecord);
        }
    }

    public ArrayList<GameRecord> getGames() {
        return games;
    }
    
    public GameRecord getLastGameRecord(){
        return games.get(games.size()-1);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Season: "+season+"\n");
        Iterator<GameRecord> itr= games.iterator();
        while(itr.hasNext()){
            sb.append(itr.next().toString());
        }
        return sb.toString();
    }
    
}
