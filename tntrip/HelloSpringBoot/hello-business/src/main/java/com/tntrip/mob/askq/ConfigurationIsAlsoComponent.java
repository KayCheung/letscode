/**
 * Copyright (C) 2006-2015 tntrip All rights reserved
 */
package com.tntrip.mob.askq;


import com.alibaba.druid.pool.DruidDataSource;
import com.tntrip.mob.askq.biz.di.service.Party;
import com.tntrip.mob.askq.biz.di.service.impl.Person;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Configuration()
@Import(value = AskqBeanConfig.class)
public class DatabaseConfig {
    @Bean("ppp1")
    public Party parrty1() {
        return new Person();
    }

    @Bean("ppp2")
    public Party parrty2() {
        return new Person();
    }

    @Bean("ppp3")
    public Party parrty3() {
        return new Person();
    }

    @Bean("ppp4")
    public Party parrty4() {

        return new Person();
    }

    @Bean(name = "druidDataSource1")
    @Primary
    public DataSource createDS1() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean(name = "druidDataSource2")
    public DataSource createDS2() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

}
