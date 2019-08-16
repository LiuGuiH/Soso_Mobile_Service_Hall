package com.ecjtu.lgh.po;

import com.ecjtu.lgh.dao.NetService;

public class NetPackage extends ServicePackage implements NetService {
    public int flow=3092;


    public NetPackage() {
        this.price=68;

    }

    public int getFlow() {
        return flow/1024;
    }

    public void showInfo(){
        //super.showInfo();
        System.out.println("网虫套餐：上网流量为"+getFlow()+"GB/月");
    }

    @Override
    public int netPlay(int flow, MobileCard card) {
        if (this.flow>=flow){
            return (int)(price);
        }else {
            return (int)(price=(flow-this.flow)*0.1+price);
        }
    }
}
