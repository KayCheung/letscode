package com.tntrip.mob.askq.web.di;

import com.tntrip.mob.askq.biz.di.service.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @Autowired and @Inject
 * 1.Matches by Type
 * 2.Restricts by Qualifiers
 * 3.Matches by Name
 * </pre>
 * <p>
 * <pre>
 * @Resource
 * 1.Matches by Name
 * 2.Matches by Type
 * 3.Restricts by Qualifiers (ignored if match is found by name)
 * </pre>
 */
@RestController
public class DIController {
    @Autowired
    private Party psn;
//    @Resource
//    private Party person;

    @RequestMapping("/api/hi")
    public String status() {
        return psn.partyName();
    }
}
