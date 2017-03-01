/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arthas.selenium.elorating;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author wytsang
 */
public class Console {
    
    private static Logger logger= LogManager.getLogger(Console.class.getName());
        
    public static void main(String args[]){
        logger.log(Level.INFO, "console starts");
        EloRating elo= new EloRating();
        elo.download();
    }
    
}
