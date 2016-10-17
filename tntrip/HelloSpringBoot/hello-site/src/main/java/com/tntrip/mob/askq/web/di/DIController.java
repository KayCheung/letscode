package com.tntrip.mob.askq.web.di;

import com.tntrip.mob.askq.biz.di.service.Party;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @Resource(name = "ppp1")
    private Party psn1;

    @Resource(name = "ppp2")
    private Party psn2;

    @Resource(name = "ppp3")
    private Party psn3;

    @RequestMapping("/api/hi")
    public String status() {
        return (psn1 == psn2) + "----" + (psn1 == psn3);
    }
}
