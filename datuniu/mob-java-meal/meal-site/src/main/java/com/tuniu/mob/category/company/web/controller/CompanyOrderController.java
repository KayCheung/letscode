package com.tuniu.mob.category.company.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tuniu.mob.category.company.ClientFormLogin;

/**
 * Created by libing2 on 2015/6/10.
 */
@RestController
public class CompanyOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyOrderController.class);
    // only for curiousness purpose--how many times it is accessed
    private static final AtomicLong ac = new AtomicLong(0);

    @Autowired
    private ClientFormLogin cfl;

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public String testDeploy() {
        LOG.info("Begin to fetchAndGroupMeals");
        try {
            long begin = System.currentTimeMillis();
            String strMeals = fetchAndGroupMeals();
            LOG.info("End to fetchAndGroupMeals, clickCount={}, cost={}ms", ac.incrementAndGet(), (System.currentTimeMillis() - begin));
            return strMeals;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String fetchAndGroupMeals() throws Exception {
        List<String> listDeptId = new ArrayList<>();
        List<String> listDeptName = new ArrayList<>();

        listDeptId.add("1936");
        listDeptName.add("预订中心/线路预订A部/线路预订A4组");

        listDeptId.add("778");
        listDeptName.add("预订中心/线路预订A部/线路预订A1组");

        listDeptId.add("776");
        listDeptName.add("预订中心/线路预订A部/线路预订A2组");

        listDeptId.add("779");
        listDeptName.add("预订中心/线路预订A部/线路预订A3组");

        prepare_Diceng_Yingyong(listDeptId, listDeptName);

        return cfl.fetchAndGroupMeals(listDeptId, listDeptName);
    }

    private void prepare_Diceng_Yingyong(List<String> listDeptId, List<String> listDeptName) {
        listDeptId.add("1471");
        listDeptName.add("网站无线中心/底层研发部");

        listDeptId.add("2171");
        listDeptName.add("网站无线中心/应用研发部");
    }
}