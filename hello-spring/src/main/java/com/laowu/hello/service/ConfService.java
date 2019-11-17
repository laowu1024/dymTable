package com.laowu.hello.service;

import com.laowu.hello.model.UserConf;

public interface ConfService {

    int insert(UserConf conf);

    void updateConf(UserConf conf);

    UserConf selectUserConfByUid(Integer uid);
}
