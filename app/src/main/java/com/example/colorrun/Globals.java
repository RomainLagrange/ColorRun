package com.example.colorrun;


public class Globals{
    private static Globals instance;

    // Global variable
    private String name;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setName(String d){
        this.name=d;
    }
    public String getName(){
        return this.name;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}