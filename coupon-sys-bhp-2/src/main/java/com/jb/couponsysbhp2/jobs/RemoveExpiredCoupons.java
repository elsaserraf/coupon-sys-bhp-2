package com.jb.couponsysbhp2.jobs;

import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.utils.Art;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveExpiredCoupons {
    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000*60)
    public void RemoveCoupons(){
        System.out.println(Art.START_DELETE_EXPIRED_COUPONS);
        couponRepository.deleteExpiredCoupons();
    }

}
