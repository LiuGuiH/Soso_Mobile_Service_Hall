package com.ecjtu.lgh.dao;

import com.ecjtu.lgh.po.MobileCard;

public interface CallService {
    int call(int minCount, MobileCard card);
}
