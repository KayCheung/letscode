package com.tuniu.mob.category.company.web.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(value = "/testdeploy", method = RequestMethod.GET)
    public String testDeploy() {
        LOG.info("CompanyOrderController.testDeploy-->/testdeploy");
        try {
            ClientFormLogin.fdfdfdfdfdfdfdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Congratulation!!!";
    }


}
