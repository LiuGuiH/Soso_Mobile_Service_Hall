package com.ecjtu.lgh.controller;

import com.ecjtu.lgh.po.*;
import com.ecjtu.lgh.service.CardUtil;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        CardUtil cardUtil=new CardUtil();
        MobileCard card=new MobileCard();
        Scanner in=new Scanner(System.in);
        int cardId;
        int packageId;

        do{
            System.out.println("****************************欢迎使用嗖嗖移动业务大厅********************************");
            System.out.println("1.用户登录\t2.用户注册\t3.使用嗖嗖\t4.话费充值\t5.资费说明\t6.退出系统");
            System.out.print("请选择：");
            int i=in.nextInt();
            //System.out.println();
            switch (i){
                case 1:
                    System.out.print("请输入卡号：");
                    String cardId1 = in.next();
                    System.out.print("请输入密码：");
                    String password1 = in.next();
                    if (cardUtil.isExistCard(cardId1,password1)){
                        for (String k:cardUtil.cards.keySet()){
                            if (k.equals(cardId1)&&cardUtil.cards.get(k).password.equals(password1)){
                                System.out.println("登录成功！");
                                System.out.println("********嗖嗖移动用户菜单********");
                                System.out.println("1.本月套餐查询");
                                System.out.println("2.套餐余量查询");
                                System.out.println("3.打印消费详单");
                                System.out.println("4.套餐更变");
                                System.out.println("5.办理退网");
                                System.out.print("请选择（输入1-5选择功能，其他键返回上一级）：");
                                int a=in.nextInt();
                                switch (a){
                                    case 1:
                                        cardUtil.showRemainDetail(card.cardNumber);
                                        break;
                                    case 2:
                                        cardUtil.showAmountDetail(card.cardNumber);
                                        break;
                                    case 3:
                                        cardUtil.printConsumInfo(card.cardNumber);
                                        break;
                                    case 4:
                                        System.out.print("1.话痨套餐\t2.超人套餐\t3.网虫套餐\t请选择套餐（输入序号）：");
                                        String aa=in.next();
                                        cardUtil.changingPack(card.cardNumber,aa);
                                        break;
                                    case 5:
                                        cardUtil.delCard(card.cardNumber);
                                        System.out.println("退网成功");
                                        System.exit(0);
                                        break;
                                    default:
                                }
                            }else {
                                System.out.println("密码错误！");
                            }
                        }
                    }else {
                        System.out.println("该号码不存在！");
                    }
                    break;
                case 2:
                    String[] cardNumbers=cardUtil.getNewNumbers(9);
                    System.out.print("请选择卡号（输入1-9的序号）：");
                    cardId=in.nextInt();
                    if (!cardUtil.isExistCard(cardNumbers[cardId-1])){
                        System.out.println("该号码已存在！");
                    }else {
                        System.out.print("1.话痨套餐\t2.超人套餐\t3.网虫套餐\t请选择套餐（输入序号）：");
                        packageId = in.nextInt();

                        switch (packageId) {
                            case 1:
                                card.serPackage=new TalkPackage();
                                if (card.serPackage instanceof TalkPackage) {
                                    card.realTalkTime = ((TalkPackage) card.serPackage).getTalkTime();
                                    card.realSMSCount = ((TalkPackage) card.serPackage).getSmsCount();
                                    break;
                                }

                            case 2:
                                card.serPackage=new SuperPackage();
                                if (card.serPackage instanceof SuperPackage) {
                                    card.realTalkTime = ((SuperPackage) card.serPackage).getTalkTime();
                                    card.realSMSCount = ((SuperPackage) card.serPackage).getSmsCount();
                                    card.realFlow = ((SuperPackage) card.serPackage).getFlow();
                                    break;
                                }

                            case 3:
                                card.serPackage=new NetPackage();
                                if (card.serPackage instanceof NetPackage) {
                                    card.realFlow = ((NetPackage) card.serPackage).getFlow();
                                    break;
                                }

                        }
                        System.out.print("请输入姓名：");
                        card.userName = in.next();
                        System.out.print("请输入密码：");
                        card.password = in.next();
                        System.out.print("请输入预存话费余额：");
                        card.money = in.nextDouble();

                        if (card.serPackage.price > card.money) {
                            do {
                                System.out.print("你预存的话费金额不足以支付本月的固定套餐资费，请重新充值：");
                                double a = in.nextDouble();
                                card.money = card.money + a;
                            } while (card.money < card.serPackage.price);
                            card.money = card.money - card.serPackage.price;
                        } else {
                            card.money = card.money - card.serPackage.price;
                        }
                        card.cardNumber = cardNumbers[cardId - 1];
                        if (cardUtil.isExistCard(card.cardNumber)) {
                            cardUtil.addCard(card);
                        }
                        System.out.println("注册成功！卡号：" + card.cardNumber + "\t用户名：" + card.userName + "\t当前余额：" + card.money);
                        card.serPackage.showInfo();
                    }
                    break;
                case 3:
                    cardUtil.useSoso(card.cardNumber);
                    break;
                case 4:
                    System.out.print("请输入需要充值的号码：");
                    String cardNumber=in.next();
                    System.out.print("请输入充值金额：");
                    double m=in.nextDouble();
                    cardUtil.chargeMoney(cardNumber,m);
                    break;
                case 5:
                    cardUtil.showDescription();
                    break;
                case 6:
                    System.out.println("退出成功!");
                    System.exit(0); break;
                default:
                    System.out.println("输入不对，请重新输入！");
            }
        }while (true);
    }


}
