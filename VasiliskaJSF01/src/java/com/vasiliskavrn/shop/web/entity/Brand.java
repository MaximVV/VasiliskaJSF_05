package com.vasiliskavrn.shop.web.entity;
// Generated Dec 11, 2016 2:21:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Brand generated by hbm2java
 */
public class Brand  implements java.io.Serializable {


     private int idBrand;
     private String brandCountry;
     private String brandStatus;
     private Set goodses = new HashSet(0);

    public Brand() {
    }

	
    public Brand(int idBrand) {
        this.idBrand = idBrand;
    }
    public Brand(int idBrand, String brandCountry, String brandStatus, Set goodses) {
       this.idBrand = idBrand;
       this.brandCountry = brandCountry;
       this.brandStatus = brandStatus;
       this.goodses = goodses;
    }
   
    public int getIdBrand() {
        return this.idBrand;
    }
    
    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }
    public String getBrandCountry() {
        return this.brandCountry;
    }
    
    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }
    public String getBrandStatus() {
        return this.brandStatus;
    }
    
    public void setBrandStatus(String brandStatus) {
        this.brandStatus = brandStatus;
    }
    public Set getGoodses() {
        return this.goodses;
    }
    
    public void setGoodses(Set goodses) {
        this.goodses = goodses;
    }




}


