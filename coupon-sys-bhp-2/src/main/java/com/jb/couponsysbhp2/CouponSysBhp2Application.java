package com.jb.couponsysbhp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "com.jb.couponsysbhp2" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.jb.couponsysbhp2.clr.off.*"))
public class CouponSysBhp2Application {


	public static void main(String[] args) {
		SpringApplication.run(CouponSysBhp2Application.class, args);


	}

}
