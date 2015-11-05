package com.tuniu.mob.category.company.web.controller;

import com.tuniu.mob.category.company.Beautiful;
import com.tuniu.mob.category.company.ClientFormLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
            String strMeals = combineAllStringResult();
            LOG.info("End to fetchAndGroupMeals, clickCount={}, cost={}ms", ac.incrementAndGet(), (System.currentTimeMillis() - begin));
            return strMeals;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String combineAllStringResult() throws Exception {
        StringBuilder sb = new StringBuilder(Beautiful.HTML_HEADER + Beautiful.BR);
        sb.append(fetchAndGroupMeals_Diceng());
        sb.append(fetchAndGroupMeals_Yingyong());
        sb.append(fetchAndGroupMeals_Chuyoufuwu());
        sb.append(Beautiful.HTML_TAIL);
        return sb.toString();
    }

    private String fetchAndGroupMeals_Diceng() throws Exception {
        List<String> listDeptId = new ArrayList<>();
        List<String> listDeptName = new ArrayList<>();

        listDeptId.add("1471");
        listDeptName.add("无线中心" + ClientFormLogin.DELIMITER +
                "底层研发部");

        return cfl.fetchAndGroupMeals(listDeptId, listDeptName, Collections.emptyList());
    }

    private String fetchAndGroupMeals_Yingyong() throws Exception {
        List<String> listDeptId = new ArrayList<>();
        List<String> listDeptName = new ArrayList<>();

        listDeptId.add("2171");
        listDeptName.add("无线中心" + ClientFormLogin.DELIMITER +
                "应用研发部");

        return cfl.fetchAndGroupMeals(listDeptId, listDeptName, Collections.emptyList());
    }

    private String fetchAndGroupMeals_Chuyoufuwu() throws Exception {
        List<String> listDeptId = new ArrayList<>();
        List<String> listDeptName = new ArrayList<>();

        listDeptId.add("4018");
        listDeptName.add("无线中心" + ClientFormLogin.DELIMITER +
                "出游服务产品部");

        List<String> plusThem = new ArrayList<>();
        plusThem.add("吴昊天");

        return cfl.fetchAndGroupMeals(listDeptId, listDeptName, plusThem);
    }

    @RequestMapping(value = "/total", method = RequestMethod.GET)
    public String totalMeals() {
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

//        listDeptId.add("1936");
//        listDeptName.add("预订中心/线路预订A部/线路预订A4组");
//
//        listDeptId.add("778");
//        listDeptName.add("预订中心/线路预订A部/线路预订A1组");
//
//        listDeptId.add("776");
//        listDeptName.add("预订中心/线路预订A部/线路预订A2组");
//
//        listDeptId.add("779");
//        listDeptName.add("预订中心/线路预订A部/线路预订A3组");

        prepare_Diceng_Yingyong_Chuyoufuwu(listDeptId, listDeptName);

        List<String> plusThem = new ArrayList<>();
        plusThem.add("吴昊天");

        return cfl.fetchAndGroupMeals(listDeptId, listDeptName, plusThem);
    }

    private void prepare_Diceng_Yingyong_Chuyoufuwu(List<String> listDeptId, List<String> listDeptName) {
        listDeptId.add("1471");
        listDeptName.add("无线中心" + ClientFormLogin.DELIMITER +
                "底层研发部");

        listDeptId.add("2171");
        listDeptName.add("无线中心" + ClientFormLogin.DELIMITER +
                "应用研发部");

        listDeptId.add("4018");
        listDeptName.add("无线中心" + ClientFormLogin.DELIMITER +
                "出游服务产品部");
    }
}
