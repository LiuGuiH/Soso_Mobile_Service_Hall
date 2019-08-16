package com.ecjtu.lgh.po;

public class ServicePackage {
    public double price;

    public ServicePackage() {
    }


    public double getPrice() {
        return price;
    }

    public void showInfo(){
        System.out.println("price="+price);
    }
}
