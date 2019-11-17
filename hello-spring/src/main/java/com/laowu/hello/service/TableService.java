package com.laowu.hello.service;

import com.laowu.hello.model.TableForm;

import java.util.List;

public interface TableService {
    List<TableForm> show();

    int insertOne(TableForm t);

    TableForm getTableFormByByUid(int uid);
}
