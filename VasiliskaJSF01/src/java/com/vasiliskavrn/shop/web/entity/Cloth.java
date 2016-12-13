package com.vasiliskavrn.shop.web.entity;
// Generated Dec 11, 2016 2:21:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cloth generated by hbm2java
 */
public class Cloth  implements java.io.Serializable {


     private Long idCloth;
     private String clothName;
     private String clothNameOne;
     private String clothStatus;
     private Set goodses = new HashSet(0);

    public Cloth() {
    }

	
    public Cloth(Long idCloth) {
        this.idCloth = idCloth;
    }
    public Cloth(Long idCloth, String clothName, String clothNameOne, String clothStatus, Set goodses) {
       this.idCloth = idCloth;
       this.clothName = clothName;
       this.clothNameOne = clothNameOne;
       this.clothStatus = clothStatus;
       this.goodses = goodses;
    }
   
    public Long getIdCloth() {
        return this.idCloth;
    }
    
    public void setIdCloth(Long idCloth) {
        this.idCloth = idCloth;
    }
    public String getClothName() {
        return this.clothName;
    }
    
    public void setClothName(String clothName) {
        this.clothName = clothName;
    }
    public String getClothNameOne() {
        return this.clothNameOne;
    }
    
    public void setClothNameOne(String clothNameOne) {
        this.clothNameOne = clothNameOne;
    }
    public String getClothStatus() {
        return this.clothStatus;
    }
    
    public void setClothStatus(String clothStatus) {
        this.clothStatus = clothStatus;
    }
    public Set getGoodses() {
        return this.goodses;
    }
    
    public void setGoodses(Set goodses) {
        this.goodses = goodses;
    }




}

