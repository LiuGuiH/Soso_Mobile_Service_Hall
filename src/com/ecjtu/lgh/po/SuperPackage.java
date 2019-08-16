package com.ecjtu.lgh.po;

import com.ecjtu.lgh.dao.CallService;
import com.ecjtu.lgh.dao.NetService;
import com.ecjtu.lgh.dao.SendService;

public class SuperPackage extends ServicePackage implements CallService, SendService, NetService {
    public  int talkTime=200;
    public  int smsCount=50;
    public  int flow=1024;

    public SuperPackage() {
        this.price=78;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public int getFlow() {
        return flow/1024;
    }


    public void showInfo(){
        //super.showInfo();

        System.out.println("超人套餐：通话时长为"+talkTime+"分钟/月，短信条数为"+smsCount+"条/月,上网流量为"+getFlow()+"GB/月");
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
    public int netPlay(int flow, MobileCard card) {
        if (this.flow>=flow){
            return (int)(price);
        }else {
            return (int)(price=(flow-this.flow)*0.1+price);
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
