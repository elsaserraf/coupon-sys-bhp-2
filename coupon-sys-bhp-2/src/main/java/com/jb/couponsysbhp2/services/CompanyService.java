package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface CompanyService {
    void addCoupon(int companyId,Coupon coupon) throws CouponSystemExceptions;
    void updateCoupon(int companyId,Coupon coupon) throws CouponSystemExceptions;
    void deleteCoupon(int companyId,int couponId);

    List<Coupon> getCompanyCoupons(int companyId);
    List<Coupon> getCompanyCoupons(int companyId,Category category);
    List<Coupon> getCompanyCoupons(int companyId,double maxPrice);
    Company getCompanyDetails(int companyId);
    int getCompanyIdByEmailAndPassword(String email,String password);





}
