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
public class TeamRecord {
    
    private String team;
    private ArrayList<SeasonRecord> seasons= new ArrayList<SeasonRecord>();
    
    public TeamRecord(String team){
        this.team= team;
    }
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    public void addGameRecord(GameRecord gmRecord){
        int season= gmRecord.getSeason();      
        SeasonRecord srRecord= null;
        int size= seasons.size();
        for(int i=0; i<size; i++){
            SeasonRecord s= seasons.get(i);
            if(season==s.getSeason()){
                srRecord= s;
                break;
            }else if(season<s.getSeason()){
                srRecord= new SeasonRecord(season);
                seasons.add(i, srRecord);
                break;
            }
        }
        if(srRecord==null){
            srRecord= new SeasonRecord(season);
            seasons.add(srRecord);
        }
        srRecord.addGameRecord(gmRecord);
    }
    
    public int getStartYear(){
        return seasons.size()>0?seasons.get(0).getSeason():-1;
    }
    
    public SeasonRecord getSeason(int season){
        Iterator<SeasonRecord> itr= seasons.iterator();
        while(itr.hasNext()){
            SeasonRecord snRecord= itr.next();
            if(season==snRecord.getSeason()){
                return snRecord;
            }else if(season<snRecord.getSeason()){
                break;
            }
        }
        return null;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Team: "+team+"\n");
        Iterator<SeasonRecord> itr= seasons.iterator();
        while(itr.hasNext()){
            sb.append(itr.next().toString());
        }
        return sb.toString();
    }
    
}
