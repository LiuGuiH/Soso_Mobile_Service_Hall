package com.ecjtu.lgh.service;

import com.ecjtu.lgh.po.*;

import java.io.*;
import java.util.*;


public class CardUtil {

    public Map<String, MobileCard> cards=new HashMap<>();
    public Map<String, List<ConsumInfo>> comsumInfos=new HashMap<>();
    ConsumInfo info=new ConsumInfo();
    Scanner in=new Scanner(System.in);
    Random random=new Random();
    Scene scene=new Scene();
    public void initScene() throws Exception {

        int typeNum=random.nextInt(3)+1;
        switch (typeNum){
            case 1:
                scene.description="问候客户，谁知其如此难缠  通话90分钟";
                scene.type="通话";
                System.out.println("场景为："+scene.description);
                break;
            case 2:
                scene.description="通知朋友手机换号，发送短信50条";
                System.out.println("场景为："+scene.description);
                scene.type="短信";
                break;
            case 3:
                scene.description="参与环境保护实施方案问卷调查   使用流量1MB";
                scene.type="流量";
                System.out.println("场景为："+scene.description);
                break;
            default:
                throw new Exception("没有该场景");
        }
    }

    public boolean isExistCard(String number,String passWord){
        if (cards.keySet().contains(number)){
            return true;
        }else {
            return false;
        }

    }

    public boolean isExistCard(String number){
        if (!cards.containsKey(number)){
            return true;
        }else {
            return false;
        }
    }

    public String createNumber(){
        Random random=new Random();
        String id="139";
        for (int i=0;i<8;i++){
            int a=random.nextInt(10);
            id=id+a;
        }

        return id;
    }

    public String[] getNewNumbers(int count){
        String[] numbers=new String[count];

        for (int j=1;j<=count;j++){
            numbers[j-1]=createNumber();
            System.out.print(j+"."+numbers[j-1]+"\t");
            if (j%3==0){
                System.out.println();
            }
        }
        return numbers;
    }

    public void addCard(MobileCard card){
        cards.put(card.cardNumber,card);
    }

    public void delCard(String number){
        if (cards.keySet().contains(number)){
            cards.remove(number);
        }
    }

    public void showRemainDetail(String number){
        System.out.println("你的卡号："+number);
        System.out.println("当月账单为： "+cards.get(number).serPackage.price+"元");
        System.out.println("合计："+cards.get(number).serPackage.price+"元");
        System.out.println("账户余额："+cards.get(number).money+"元");
    }

    public void showAmountDetail(String number){
        cards.get(number).showMeg();
    }

    public void addConsumInfo(String number,ConsumInfo info) throws Exception {
        List<ConsumInfo> list=new LinkedList<>();
        list.add(info);
        comsumInfos.put(number,list);
    }

    public void useSoso(String number) throws Exception {

        initScene();
        System.out.println("请输入场景消耗数据！");
        scene.data=in.nextInt();
        switch (scene.type){
            case "通话":
                if (cards.get(number).realTalkTime>=scene.data){
                    info.talkTime=scene.data;
                    cards.get(number).realTalkTime-=scene.data;
                }else {
                    cards.get(number).realTalkTime=0;
                    cards.get(number).money-=0.2*scene.data;
                }
                break;
            case "短信":
                if (cards.get(number).realSMSCount>=scene.data){
                    info.smsCount=scene.data;
                    cards.get(number).realSMSCount-=scene.data;
                }else {
                    cards.get(number).realSMSCount=0;
                    cards.get(number).money-=0.1*scene.data;
                }
                break;
            case "流量":
                if (cards.get(number).realFlow>=scene.data){
                    info.flow=scene.data;
                    cards.get(number).realFlow-=scene.data;
                }else {
                    cards.get(number).realFlow=0;
                    cards.get(number).money-=0.1*scene.data;
                }
                break;
            default:
                throw new Exception("没有该场景！");
        }
        addConsumInfo(number,info);
    }

    public void showDescription() throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(new File("资费说明.txt")));
        String str=new String();
        while ((str=br.readLine())!=null){
            System.out.println(str);
        }
        br.close();
    }

    public void changingPack(String number,String packNum){
        switch (packNum){
            case "1":
                if (cards.get(number).serPackage.getPrice()==58){
                    System.out.println("对不起，你已是该套餐用户，无需换套餐！");
                }else {

                    if (cards.get(number).money<new TalkPackage().price){
                        System.out.println("对不起，你的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
                    }else {
                        cards.get(number).serPackage=new TalkPackage();
                        if (cards.get(number).serPackage instanceof TalkPackage){
                            cards.get(number).money-=cards.get(number).serPackage.price;
                            cards.get(number).realTalkTime+=((TalkPackage) cards.get(number).serPackage).getTalkTime();
                            cards.get(number).realSMSCount+=((TalkPackage) cards.get(number).serPackage).getSmsCount();
                            System.out.println("更换成功！");
                        }
                    }
                }


                break;
            case "2":
                if (cards.get(number).serPackage.getPrice()==78){
                    System.out.println("对不起，你已是该套餐用户，无需换套餐！");
                }else {

                    if (cards.get(number).money<new SuperPackage().price){
                        System.out.println("对不起，你的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
                    }else {
                        cards.get(number).serPackage=new SuperPackage();
                        if (cards.get(number).serPackage instanceof SuperPackage){
                            cards.get(number).money-=cards.get(number).serPackage.price;
                            cards.get(number).realTalkTime+=((SuperPackage) cards.get(number).serPackage).getTalkTime();
                            cards.get(number).realSMSCount+=((SuperPackage) cards.get(number).serPackage).getSmsCount();
                            System.out.println("更换成功！");
                        }
                    }
                }
            break;
            case "3":
                if (cards.get(number).serPackage.getPrice()==68){
                    System.out.println("对不起，你已是该套餐用户，无需换套餐！");
                }else {

                    if (cards.get(number).money<new NetPackage().price){
                        System.out.println("对不起，你的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
                    }else {
                        cards.get(number).serPackage=new NetPackage();
                        if (cards.get(number).serPackage instanceof NetPackage){
                            cards.get(number).money-=cards.get(number).serPackage.price;
                            cards.get(number).realTalkTime+=((NetPackage) cards.get(number).serPackage).getFlow();
                            System.out.println("更换成功！");
                        }
                    }
                }
            break;
        }


    }

    public void printConsumInfo(String number) throws Exception {
        BufferedWriter bw=new BufferedWriter(new FileWriter(new File("消费记录.txt")));
        bw.write(number+"\t");
        bw.write(scene.type+"\t");
        bw.write(scene.data+"\t");
        bw.close();
        System.out.println(number+"\t"+scene.type+"\t"+scene.data);

    }

    public void chargeMoney(String number,double money){
            if (!isExistCard(number)){
                cards.get(number).money+=money;
            }
            System.out.println("号码: "+number+"\t余额为："+cards.get(number).money);
        }

}
