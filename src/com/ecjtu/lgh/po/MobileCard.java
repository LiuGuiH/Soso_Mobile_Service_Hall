package com.ecjtu.lgh.po;

public class MobileCard {
    public String cardNumber;
    public String userName;
    public String password;
    public ServicePackage serPackage;
    public double consumAmount;
    public double money;
    public int realTalkTime;
    public int realSMSCount;
    public int realFlow;

    public void showMeg(){
        System.out.println("cardNumber="+cardNumber+",userName="+userName+",password="+password);
    }

}
