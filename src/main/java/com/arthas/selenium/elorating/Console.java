/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arthas.selenium.elorating;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author wytsang
 */
public class Console {
    
//    private final static InputStream inputStream = Console.class.getResourceAsStream("config/log4j.properties");
    
    public static void main(String args[]){
        InputStream inputStream = Console.class.getClassLoader().getResourceAsStream("config/log4j.properties");
        try{
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (final IOException e){
            Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
            Logger.getAnonymousLogger().severe(e.getMessage());
        }
        Logger logger= Logger.getLogger(Console.class.getName());
        logger.log(Level.FINE, "console starts");
        EloRating elo= new EloRating();
        elo.download();
    }
    
}
