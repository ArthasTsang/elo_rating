package com.arthas.selenium.elorating;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wytsang
 */
public enum Schedule {
    
    WEEK_1("Week 1"),
    WEEK_2("Week 2"),
    WEEK_3("Week 3"),
    WEEK_4("Week 4"),
    WEEK_5("Week 5"),
    WEEK_6("Week 6"),
    WEEK_7("Week 7"),
    WEEK_8("Week 8"),
    WEEK_9("Week 9"),
    WEEK_10("Week 10"),
    WEEK_11("Week 11"),
    WEEK_12("Week 12"),
    WEEK_13("Week 13"),
    WEEK_14("Week 14"),
    WEEK_15("Week 15"),
    WEEK_16("Week 16"),
    WEEK_17("Week 17"),
    WEEK_18("Week 18"),
    WILD_CARD("Wild card"),
    DIVISONAL("Divisional"),
    CONFERENCE("Conference"),
    SUPER_BOWL("Super Bowl");
    
    
    private final String week;

    Schedule(String week){
        this.week= week;
    }
    
    public static Schedule lookup(String week){
        for (Schedule s: Schedule.values()) {
            if (s.week.equals(week)) {
                return s;
            }
        }
        return null;
    }
    
    public String getWeek() {
        return week;
    }
    
}
