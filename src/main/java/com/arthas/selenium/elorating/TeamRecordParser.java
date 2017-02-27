package com.arthas.selenium.elorating;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
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
public class TeamRecordParser {
    
    private final static Logger logger= Logger.getLogger(TeamRecordParser.class.getName());
    private final JSONObject content;
    private final SimpleDateFormat originFormat = new SimpleDateFormat("M/d/yyyy");
    private final SimpleDateFormat convertFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public TeamRecordParser(JSONObject content){
        this.content= content;        
    }
    
    public TeamRecord parse(){
        JSONArray arr= (JSONArray) content.get("value");
        
        TeamRecord team= new TeamRecord(((String)content.get("name")).toUpperCase());
        Iterator itr= arr.iterator();
        while(itr.hasNext()){
            JSONObject obj= (JSONObject)itr.next();
            if(isGameRecord(obj)){
                team.addGameRecord(parseGameRecord(obj));
            }
        }
        return team;
    }
    
    private boolean isGameRecord(JSONObject obj){
        return obj.get("d")!=null;
    }
    
    private GameRecord parseGameRecord(JSONObject obj){
//        logger.log(Level.FINE, obj);
        GameRecord gmRecord= new GameRecord();
        try {
            gmRecord.setOpponent(((String)obj.get("t")).toUpperCase());
            gmRecord.setWeek(Schedule.lookup((String)obj.get("w")));
            Date d= originFormat.parse((String)obj.get("d"));
            gmRecord.setGameDate(convertFormat.format(d));
            gmRecord.setSeason(determineSeason(d));
            gmRecord.setElo(obj.get("y").toString());
//            logger.log(Level.FINE, gmRecord.toString());
        } catch (ParseException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return gmRecord;
    }
    
    private int determineSeason(Date gameDate){
        Calendar cld= Calendar.getInstance();
        cld.setTime(gameDate);
        // use Mar-01 as the cutoff for season
        Calendar cutoff= Calendar.getInstance();
        cutoff.setTime(gameDate);
        cutoff.set(cutoff.get(Calendar.YEAR), 2, 1);
        
        return cld.before(cutoff)?cld.get(Calendar.YEAR)-1:cld.get(Calendar.YEAR);
    }
    
}
