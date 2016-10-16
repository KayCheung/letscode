package com.tntrip.mob.askq.web.di;

import com.tntrip.mob.askq.biz.di.service.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nuc on 2016/10/16.
 */
@RestController
public class DIController {
    @Autowired
    private Party party;

    @RequestMapping("/api/hi")
    public String status() {
        return party.partyName();
    }
}
