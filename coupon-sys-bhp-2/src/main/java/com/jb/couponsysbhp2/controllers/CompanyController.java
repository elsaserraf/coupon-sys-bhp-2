package com.jb.couponsysbhp2.controllers;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.services.CompanyService;
import com.jb.couponsysbhp2.services.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("couponsys/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/login")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public boolean login(@RequestParam("email") String email, @RequestParam("password") String password) throws CouponSystemExceptions {
        return ((CompanyServiceImpl)companyService).login(email,password);
    }

    @PostMapping("/add/coupon/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int id,@RequestBody Coupon coupon) throws CouponSystemExceptions {
        companyService.addCoupon(id,coupon);
    }

    @PutMapping("/update/coupon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int id,@RequestBody Coupon coupon) throws CouponSystemExceptions {
        companyService.updateCoupon(id,coupon);
    }

    @DeleteMapping("/delete/coupon/{companyId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int companyId,@PathVariable int id) throws CouponSystemExceptions {
        companyService.deleteCoupon(companyId,id);
    }


    @GetMapping("/{id}")
    public Company getCompanyDetails(@PathVariable int id){
        return  companyService.getCompanyDetails(id);
    }

    @GetMapping("/coupons/{id}")
    public List<Coupon> getAllCoupons(@PathVariable int id){
        return  companyService.getCompanyCoupons(id);
    }

    @GetMapping("/coupons/{id}/{categoryName}")
    public List<Coupon> getAllCouponsByCategory(@PathVariable int id,@PathVariable String categoryName){
        return  companyService.getCompanyCoupons(id, Category.valueOf(categoryName));
    }

    @GetMapping("/couponsbyprice/{id}/{price}")
    public List<Coupon> getAllCouponsByPrice(@PathVariable int id,@PathVariable double price){
        return  companyService.getCompanyCoupons(id, price);
    }
}
