package com.ecjtu.lgh.dao;

import com.ecjtu.lgh.po.MobileCard;

public interface SendService {
    int send(int count, MobileCard card);
}
