package com.tntrip.mob.category.company.rtx;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by libing2 on 2015/10/21.
 */
@ConfigurationProperties(prefix = "organization0")
public class Organization0 {
    private List<String> orgName;
    private List<String> groupA_0;
    private List<String> groupB_1;
    private List<String> groupC_2;
    private List<String> groupD_3;

    public List[] getGroups() {
        return new List[]{groupA_0, groupB_1, groupC_2, groupD_3};
    }

    public List<String> getOrgName() {
        return orgName;
    }

    public void setOrgName(List<String> orgName) {
        this.orgName = orgName;
    }

    public List<String> getGroupA_0() {
        return groupA_0;
    }

    public void setGroupA_0(List<String> groupA_0) {
        this.groupA_0 = groupA_0;
    }

    public List<String> getGroupB_1() {
        return groupB_1;
    }

    public void setGroupB_1(List<String> groupB_1) {
        this.groupB_1 = groupB_1;
    }

    public List<String> getGroupC_2() {
        return groupC_2;
    }

    public void setGroupC_2(List<String> groupC_2) {
        this.groupC_2 = groupC_2;
    }

    public List<String> getGroupD_3() {
        return groupD_3;
    }

    public void setGroupD_3(List<String> groupD_3) {
        this.groupD_3 = groupD_3;
    }
}
