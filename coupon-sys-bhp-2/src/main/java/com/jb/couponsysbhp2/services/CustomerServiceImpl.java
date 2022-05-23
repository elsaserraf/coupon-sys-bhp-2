package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;


@Service
public class CustomerServiceImpl extends ClientService implements CustomerService{
    @Override
    public boolean login(String email, String password) throws CouponSystemExceptions {
        if(!customerRepository.existsByEmailAndPassword(email,password))
            throw new CouponSystemExceptions("EMAIL_OR_PASSWORD_WRONG");
        return true;
    }

    @Override
    public void purchaseCoupon(int customerId,int couponId) throws CouponSystemExceptions {
        if (!couponRepository.existsById(couponId))
            throw new CouponSystemExceptions("COUPON_DOESN'T_EXIS");
        Coupon cp1 = couponRepository.getById(couponId);
        if (cp1.getAmount()==0)
            throw new CouponSystemExceptions("NUMBER OF COUPONS INSUFFICIENT ");
        if (cp1.getEndDate().before(Date.from(Instant.now())))
            throw new CouponSystemExceptions("COUPON ALREADY EXPIRED");
        if (couponRepository.existsByCustomerIdAndCouponId(customerId,couponId)!=0)
            throw new CouponSystemExceptions("COUPON ALREADY PURCHASED");

    couponRepository.addPurchaseCoupon(customerId,couponId);
        cp1.setAmount(cp1.getAmount()-1);
        couponRepository.saveAndFlush(cp1);
  }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        List<Integer> listCp = couponRepository.getListCouponsIdByCustomerId(customerId);
        List<Coupon> coupons = new ArrayList<>();
        for (Integer i:listCp) {
            coupons.add(couponRepository.getById(i));
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId,String categoryName) {
        List<Integer> listCp = couponRepository.getListCouponsIdByCustomerIdAndCategory(customerId,categoryName);
        List<Coupon> coupons = new ArrayList<>();
        for (Integer i:listCp) {
            coupons.add(couponRepository.getById(i));
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId,double maxPrice) {
        List<Integer> listCp = couponRepository.getListCouponsIdByCustomerIdAndPrice(customerId,maxPrice);
        List<Coupon> coupons = new ArrayList<>();
        for (Integer i:listCp) {
            coupons.add(couponRepository.getById(i));
        }
        return coupons;
    }

    @Override
    public Customer getCustomerDetails(int customerId) {
        Customer curr = customerRepository.findById(customerId).get();
        curr.setCoupons(getCustomerCoupons(customerId));
        return curr;
    }

    @Override
    public int getCustomerIdByEmailAndPassword(String email, String password) {
        return customerRepository.findIdByEmailAndPassword(email,password);
    }
}
