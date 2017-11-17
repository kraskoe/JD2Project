package com.pvt.app.enums;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public enum OrderStatus {
    RECEIVED("Received"),
    CONFIRMED("Confirmed"),
    DELIVERED("Delivered");

    private String status;

    private OrderStatus(final String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString(){
        return this.status;
    }

    public String getName(){
        return this.name();
    }
}
