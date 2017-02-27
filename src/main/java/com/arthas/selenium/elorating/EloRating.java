package com.arthas.selenium.elorating;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wytsang
 */
public class EloRating {
    
    private final static Logger logger= Logger.getLogger(EloRating.class.getName());
    
    public void download(){
        Properties config= new Properties();
        InputStream in= getClass().getClassLoader().getResourceAsStream("config/config.properties");
        if(in!=null){
            logger.log(Level.FINE, "Loading config file");
            try {
                config.load(in);
            } catch (IOException ex) {
                Logger.getLogger(EloRating.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            logger.log(Level.FINE, "File not found!");
        }
        
        System.setProperty("webdriver.chrome.driver", config.getProperty("webdriver.chrome.driver"));
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        String baseAddress = config.getProperty("website");
        
        Map<String, TeamRecord> teams= new HashMap<String, TeamRecord>();
        int startYear= Calendar.getInstance().get(Calendar.YEAR);
        int currentYear= Calendar.getInstance().get(Calendar.YEAR);
        for(Team t: Team.values()){
            String webAddress= baseAddress+t.getFile();
            driver.get(webAddress);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    //        logger.log(Level.FINE, driver.getPageSource());
    //        logger.log(Level.FINE, "--------------------------------------------------");

            WebElement elm= driver.findElement(By.tagName("pre"));
            String jString= elm.getAttribute("innerHTML");

            JSONParser parser = new JSONParser();
            try {
                Object obj= parser.parse(jString);
                JSONObject jObj= (JSONObject) obj;
                TeamRecordParser histParser= new TeamRecordParser(jObj);
                TeamRecord team= histParser.parse();
                teams.put(team.getTeam(), team);
                if(team.getStartYear()<startYear){
                    startYear= team.getStartYear();
                }
                logger.log(Level.FINE, team.toString());
            } catch (ParseException ex) {
                Logger.getLogger(EloRating.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        driver.quit();
        
        CSVWriter writer=null; 
        try {
            writer = new CSVWriter(new FileWriter(config.getProperty("output.file")), ',');
            
            List<String> headerList= new ArrayList<String>();
            headerList.add("Year");
            for(int y=startYear; y<currentYear; y++){
                headerList.add(y+"");
            }
            writer.writeNext(headerList.toArray(new String[0]));
        
            for(Team t: Team.values()){
                TeamRecord tmRecord= teams.get(t.getLabel());
                List<String> eloList= new ArrayList<String>();
                eloList.add(tmRecord.getTeam());
                for(int y=startYear; y<currentYear; y++){
                    SeasonRecord snRecord= tmRecord.getSeason(y);
                    if(snRecord==null){
                        eloList.add("-");
                    }else{
                        eloList.add(snRecord.getLastGameRecord().getElo());
                    }
                }
                writer.writeNext(eloList.toArray(new String[0]));
            }
        }catch (IOException ex) {
            Logger.getLogger(EloRating.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(EloRating.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
