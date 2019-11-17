package com.laowu.hello.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laowu.hello.model.TableForm;
import com.laowu.hello.model.UserConf;
import com.laowu.hello.service.ConfService;
import com.laowu.hello.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
public class HelloController {

    @Autowired
    private TableService tableService;

    @Autowired
    private ConfService confService;

    @Transactional
    @RequestMapping("/add")
    public String addInfo(){
        TableForm t = new TableForm();
        t.setCardDate(fromLocalDate(LocalDate.of(2019,10,2)));
        t.setCardName("你妈的消费卡");
        t.setCardInfo("超行1234");
        t.setCardOther1("我的贝贝");
        t.setCardOther2("我的宝宝");
        t.setUid(33);
        tableService.insertOne(t);
        System.out.println("插入一条记录,插入默认的配置");
        UserConf c = new UserConf();
        c.setDefaultConf(genDefList());
        c.setUid(33L);
        c.setCreateTime(fromLocalDate(LocalDate.of(2019,11,11)));
        c.setUpdateTime(fromLocalDate(LocalDate.of(2019,11,12)));
        confService.insert(c);
        return "入库结束";
    }

    @RequestMapping("change")
    public String changeInfo(){
        UserConf conf = new UserConf();
        conf.setUid(33L);
        conf.setConf(genList());
        confService.updateConf(conf);
        return "结束";
    }


    @RequestMapping("/show/{uid}")
    public String show(@PathVariable int uid){
        //首先获取用户自定的conf 如果为空或者不能转换的 则使用默认的配置
        UserConf conf = confService.selectUserConfByUid(uid);
        String confStr= conf.getConf();
        if(null == confStr){
            confStr = conf.getDefaultConf();
            System.out.println(confStr);
        }
        JSONArray showHead = JSON.parseArray(confStr);
        //获得需要展示的列表 转为map等待获取
        TableForm t = tableService.getTableFormByByUid(uid);
        System.out.println(t);
        Map<String,String> infoMap = JSON.parseObject(JSON.toJSONString(t), Map.class);
        List<Map<String,String>> res = new ArrayList<>();
        //找到对应的关系map
        Map<String,String> confGenMap = getConfMap();
        showHead.forEach(head -> {
            Map<String,String> map = new HashMap<>();
            map.put((String) head,infoMap.get(confGenMap.get(head)));
            res.add(map);
        });
        System.out.println(JSON.toJSONString(res));
        return "";
    }

    public static Date fromLocalDate(LocalDate date) {
        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }
    public static String genList(){
        List<String> list = new ArrayList<>();
        list.add("卡备注1");
        list.add("卡时间");
        list.add("卡备注2");
        list.add("卡名");
        list.add("卡信息");
        return JSON.toJSONString(list);
    }

    public static String genDefList(){
        List<String> list = new ArrayList<>();
        list.add("卡信息");
        list.add("卡名");
        list.add("卡时间");
        list.add("卡备注1");
        list.add("卡备注2");
        return JSON.toJSONString(list);
    }

    public static Map<String,String> getConfMap(){
        Map<String,String> map = new HashMap<>();
        map.put("卡信息","cardInfo");
        map.put("卡名","cardName");
        map.put("卡时间","cardDate");
        map.put("卡备注1","cardOther1");
        map.put("卡备注2","cardOther2");
        return map;
    }
}
