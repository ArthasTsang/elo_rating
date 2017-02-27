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
public enum Team {
    
    NE("NE", "ne.json"),
    MIA("MIA", "mia.json"),
    BUF("BUF", "buf.json"),
    NYJ("NYJ", "nyj.json"),
    
    PIT("PIT", "pit.json"),
    BAL("BAL", "bal.json"),
    CIN("CIN", "cin.json"),
    CLE("CLE", "cle.json"),
    
    HOU("HOU", "hou.json"),
    TEN("TEN", "ten.json"),
    IND("IND", "ind.json"),
    JAX("JAX", "jax.json"),
    
    KC("KC", "kc.json"),
    OAK("OAK", "oak.json"),
    DEN("DEN", "den.json"),
    LAC("SD", "sd.json"),
    
    DAL("DAL", "dal.json"),
    NYG("NYG", "nyg.json"),
    WAS("WSH", "wsh.json"),
    PHI("PHI", "phi.json"),
    
    GB("GB", "gb.json"),
    DET("DET", "det.json"),
    MIN("MIN", "min.json"),
    CHI("CHI", "chi.json"),
    
    ATL("ATL", "atl.json"),
    TB("TB", "tb.json"),
    NO("NO", "no.json"),
    CAR("CAR", "car.json"),
    
    SEA("SEA", "sea.json"),
    ARI("ARI", "ari.json"),
    LAR("STL", "stl.json"),
    SF("SF", "sf.json");
    
    private final String label;
    private final String file;
    
    Team(String label, String file){
        this.label= label;
        this.file= file;
    }

    public String getLabel() {
        return label;
    }

    public String getFile() {
        return file;
    }
    
}
