/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arthas.selenium.elorating;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 *
 * @author wytsang
 */
public class TestA {

    private String baseDir="src/test/resources/";
    
    public TestA() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void startOfSeason(){
        String path= baseDir+"startOfSeason.json";
        File file = new File(path);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            
            JSONParser parser = new JSONParser();
            JSONObject jObj= (JSONObject) parser.parse(new String(data));
            TeamRecordParser histParser= new TeamRecordParser(jObj);
            TeamRecord team= histParser.parse();
            assertEquals("Incorrect team name", team.getTeam(), "ATL");
            SeasonRecord srRecord= team.getSeason(2016);
            assertNotNull("No record for season 2016", srRecord);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void endOfSeason(){
        String path= baseDir+"endOfSeason.json";
        File file = new File(path);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            
            JSONParser parser = new JSONParser();
            JSONObject jObj= (JSONObject) parser.parse(new String(data));
            TeamRecordParser histParser= new TeamRecordParser(jObj);
            TeamRecord team= histParser.parse();
            assertEquals("Incorrect team name", team.getTeam(), "ATL");
            SeasonRecord srRecord= team.getSeason(2015);
            assertNotNull("No record for season 2015", srRecord);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void scheduleSorting(){
        String path= baseDir+"schedule.json";
        File file = new File(path);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            
            JSONParser parser = new JSONParser();
            JSONObject jObj= (JSONObject) parser.parse(new String(data));
            TeamRecordParser histParser= new TeamRecordParser(jObj);
            TeamRecord team= histParser.parse();
            SeasonRecord srRecord= team.getSeason(2016);
            List<GameRecord> games= srRecord.getGames();
            assertEquals("2016 season should have 6 records", games.size(), 6);
            assertEquals("1st record is week 17", games.get(0).getWeek(), Schedule.WEEK_17);
            assertEquals("2nd record is week 18", games.get(1).getWeek(), Schedule.WEEK_18);
            assertEquals("3rd record is wild card", games.get(2).getWeek(), Schedule.WILD_CARD);
            assertEquals("4th record is divisional", games.get(3).getWeek(), Schedule.DIVISONAL);
            assertEquals("5th record is conference", games.get(4).getWeek(), Schedule.CONFERENCE);
            assertEquals("6th record is super bowl", games.get(5).getWeek(), Schedule.SUPER_BOWL);
            assertEquals("last record is super bowl", srRecord.getLastGameRecord().getWeek(), Schedule.SUPER_BOWL);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TestA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
