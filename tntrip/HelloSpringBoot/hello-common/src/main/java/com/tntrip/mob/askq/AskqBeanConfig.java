package com.tntrip.mob.askq;

import com.tntrip.mob.askq.common.util.LovelyPet;
import com.tntrip.mob.askq.common.util.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.tntrip")
public class AskqBeanConfig {
    @Bean("pet1")
    public Pet createPet() {
        return new LovelyPet();
    }
}
