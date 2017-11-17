package com.pvt.app.enums;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public enum UserType {
    ADMIN("ADMIN"),
    GRAND_USER("GRAND_USER"),
    USER("USER");

    String type;

    UserType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }


    @Override
    public String toString(){
        return this.type;
    }

    public String getName(){
        return this.name();
    }
}
