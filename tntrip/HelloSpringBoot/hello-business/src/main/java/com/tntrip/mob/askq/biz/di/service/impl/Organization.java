package com.tntrip.mob.askq.biz.di.service.impl;

import com.tntrip.mob.askq.biz.di.service.Party;

/**
 * Created by libing2 on 2016/10/16.
 */
public class Organization implements Party {
    @Override
    public String partyName() {
        return "Organization";
    }
}
