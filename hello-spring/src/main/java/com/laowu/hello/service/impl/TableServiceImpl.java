package com.laowu.hello.service.impl;

import com.laowu.hello.mapper.TableFormMapper;
import com.laowu.hello.model.TableForm;
import com.laowu.hello.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableFormMapper tableFormMapper;

    @Override
    public List<TableForm> show() {
        return tableFormMapper.selectAll();
    }

    @Override
    public int insertOne(TableForm t) {
        int i = tableFormMapper.insertSelective(t);
        return i;
    }

    @Override
    public TableForm getTableFormByByUid(int uid) {
        TableForm t = new TableForm();
        t.setUid(uid);
        TableForm tableForm = tableFormMapper.selectOne(t);
        return tableForm;
    }
}
