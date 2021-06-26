package com.ibm.academy.challenge2;

public class Movie {

    private String title;

    private int year;

    private String imbId;

    public Movie(){

    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getYear(){
        return this.year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String getImbId(){
        return this.imbId;
    }

    public void setImbId(String imbId){
        this.imbId = imbId;
    }

    @Override
    public String toString(){
        return "Title: " + this.title + " Year: " + this.year + " ImbID: " + this.imbId;
    }
    
}
