package com.sf.brinkstest.models;

import java.io.Serializable;

/**
 * Created by sf on 2/13/17.
 */

public class App implements Serializable{
    private String name, urlImage, vendor, category, cost, summary;

    //Constructor
     public App(String name, String urlImage, String vendor, String category, String cost, String summary){
         this.name = name;  this.urlImage = urlImage; this.vendor = vendor;
         this.category = category; this.cost = cost; this.summary = summary;
     }


    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getVendor() {
        return vendor;
    }

    public String getCategory() {
        return category;
    }

    public String getCost() {
        return cost;
    }

    public String getSummary() {
        return summary;
    }
}
