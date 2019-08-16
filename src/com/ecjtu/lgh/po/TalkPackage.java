package com.ecjtu.lgh.po;

import com.ecjtu.lgh.dao.CallService;
import com.ecjtu.lgh.dao.SendService;

public class TalkPackage extends ServicePackage implements CallService, SendService {
    public int talkTime=500;
    public int smsCount=30;

    public TalkPackage() {
        this.price=58;
    }

    /*public TalkPackage(double price, int talkTime, int smsCount) {
        super(price);
        this.talkTime = talkTime;
        this.smsCount = smsCount;
    }*/

    public int getTalkTime() {
        return talkTime;
    }

    public int getSmsCount() {
        return smsCount;
    }


    public void showInfo(){
        //super.showInfo();
        System.out.println("话痨套餐：通话时长为"+talkTime+"分钟/月，短信条数为"+smsCount+"条/月");
    }

    @Override
    public int call(int minCount, MobileCard card) {
        if (talkTime>=minCount){
            return (int)(price);
        }else {
            return (int)(price =(minCount-talkTime)*0.2+price);
        }

    }

    @Override
    public int send(int count, MobileCard card) {
        if (count<=smsCount){
            return (int)(price);
        }else {
            return (int) (price=price+(count-smsCount)*0.1);
        }

    }
}
