package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


public interface CustomerService {
    void purchaseCoupon(int customerId,int couponId) throws CouponSystemExceptions;
   List<Coupon> getCustomerCoupons(int customerId);
    List<Coupon> getCustomerCoupons(int customerId,String categoryName);
    List<Coupon> getCustomerCoupons(int customerId,double maxPrice);
    Customer getCustomerDetails(int customerId);


    int getCustomerIdByEmailAndPassword(String email, String password);
}
