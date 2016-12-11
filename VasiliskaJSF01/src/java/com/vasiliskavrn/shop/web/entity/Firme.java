package com.vasiliskavrn.shop.web.entity;
// Generated Dec 11, 2016 2:21:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Firme generated by hbm2java
 */
public class Firme  implements java.io.Serializable {


     private int idFirme;
     private String firmeName;
     private String firmeStatus;
     private Set goodses = new HashSet(0);

    public Firme() {
    }

	
    public Firme(int idFirme) {
        this.idFirme = idFirme;
    }
    public Firme(int idFirme, String firmeName, String firmeStatus, Set goodses) {
       this.idFirme = idFirme;
       this.firmeName = firmeName;
       this.firmeStatus = firmeStatus;
       this.goodses = goodses;
    }
   
    public int getIdFirme() {
        return this.idFirme;
    }
    
    public void setIdFirme(int idFirme) {
        this.idFirme = idFirme;
    }
    public String getFirmeName() {
        return this.firmeName;
    }
    
    public void setFirmeName(String firmeName) {
        this.firmeName = firmeName;
    }
    public String getFirmeStatus() {
        return this.firmeStatus;
    }
    
    public void setFirmeStatus(String firmeStatus) {
        this.firmeStatus = firmeStatus;
    }
    public Set getGoodses() {
        return this.goodses;
    }
    
    public void setGoodses(Set goodses) {
        this.goodses = goodses;
    }




}


