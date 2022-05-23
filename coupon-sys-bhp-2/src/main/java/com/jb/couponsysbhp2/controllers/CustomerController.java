package com.jb.couponsysbhp2.controllers;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.services.CompanyServiceImpl;
import com.jb.couponsysbhp2.services.CustomerService;
import com.jb.couponsysbhp2.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("couponsys/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean login(@RequestParam("email") String email, @RequestParam("password") String password) throws CouponSystemExceptions {
        return ((CustomerServiceImpl)customerService).login(email,password);
    }

    @PostMapping("/purchasecoupon/{customerId}/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId,@PathVariable int couponId) throws CouponSystemExceptions {
        customerService.purchaseCoupon(customerId,couponId);
    }

    @GetMapping("/coupons/id/{id}")
    public List<Coupon> getAllCouponsById(@PathVariable int id){
        return customerService.getCustomerCoupons(id);
    }

    @GetMapping("/coupons/category/{id}/{categoryName}")
    public List<Coupon> getAllCouponsByIdAndCategory(@PathVariable int id,@PathVariable String categoryName){
        return customerService.getCustomerCoupons(id,categoryName);
    }

    @GetMapping("/coupons/price/{id}/{price}")
    public List<Coupon> getAllCouponsByIdAndPrice(@PathVariable int id,@PathVariable double price){
        return customerService.getCustomerCoupons(id,price);
    }

}
