package com.laowu.hello.service.impl;

import com.laowu.hello.mapper.UserConfMapper;
import com.laowu.hello.model.UserConf;
import com.laowu.hello.service.ConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfServiceImpl implements ConfService {

    @Autowired
    private UserConfMapper userConfMapper;

    @Override
    public int insert(UserConf conf) {
        int insert = userConfMapper.insert(conf);
        return insert;
    }

    @Override
    public void updateConf(UserConf conf){
        userConfMapper.updateConf(conf);
    }

    @Override
    public UserConf selectUserConfByUid(Integer uid) {
        UserConf conf = new UserConf();
        conf.setUid(Long.valueOf(uid));
        return userConfMapper.selectConf(conf);
    }
}
