package com.jb.couponsysbhp2.clr.off;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.ClientType;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.security.LoginManager;
import com.jb.couponsysbhp2.services.AdminService;
import com.jb.couponsysbhp2.services.CompanyService;
import com.jb.couponsysbhp2.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(3)
public class CompanyFacadeTest implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private CompanyService companyService;

    private final String email="comp1@gmail.com";
    private final String password="1234";

    @Override
    public void run(String... args) throws Exception {


        try {
            companyService = (CompanyService) loginManager.login(email, password, ClientType.COMPANY);
            System.out.println("****************************LOGIN SUCCESSFULLY*******************************\n");
            int companyId=companyService.getCompanyIdByEmailAndPassword(email,password);
            System.out.printf("Customer Id Logged %d\n",companyId);


            try {
                Coupon coupon6 = Coupon.builder()
                        .title("coup6")
                        .description("coupon 1+1")
                        .category(Category.Vacation)
                        .price(13)
                        .amount(100)
                        .image("coup1.txt")
                        .startDate(Date.valueOf(LocalDate.now()))
                        .endDate(Date.valueOf(LocalDate.now().plusDays(10)))
                        .company(companyService.getCompanyDetails(1))
                        .build();
                Coupon coupon7 = Coupon.builder().id(4)
                        .title("coup6")
                        .description("coupon 2+1")
                        .category(Category.Vacation)
                        .price(13)
                        .amount(100)
                        .image("coup1.txt")
                        .startDate(Date.valueOf(LocalDate.now()))
                        .endDate(Date.valueOf(LocalDate.now().plusDays(10)))
                        .company(companyService.getCompanyDetails(1))
                        .build();
                companyService.addCoupon(companyId,coupon6);
                companyService.updateCoupon(companyId,coupon7);
                System.out.println("***********************ADDED COUPON SUCCESSFULLY***************************\n");
            } catch (CouponSystemExceptions e) {
                //e.printStackTrace();
                System.out.println(e);
            }
            System.out.println("***********************GET COMPANY COUPONS***************************\n");
            List<Coupon> coupons1 = companyService.getCompanyCoupons(companyId);
            PrintUtils.printCoupons(coupons1);
            System.out.println("***********************GET COMPANY COUPONS BY ID AND CATEGORY***************************\n");
            List<Coupon> coupons2 = companyService.getCompanyCoupons(companyId,Category.Vacation);
            PrintUtils.printCoupons(coupons2);
            System.out.println("***********************GET COMPANY COUPONS BY ID AND CATEGORY***************************\n");
            List<Coupon> coupons3 = companyService.getCompanyCoupons(companyId,Category.Vacation);
            PrintUtils.printCoupons(coupons3);
            System.out.println("***********************GET COMPANY COUPONS BY ID AND CATEGORY***************************\n");
            List<Coupon> coupons4 = companyService.getCompanyCoupons(companyId,14);
            PrintUtils.printCoupons(coupons4);
            System.out.println("***********************GET COMPANY DETAILS***************************\n");
            Company company1 = companyService.getCompanyDetails(companyId);
            PrintUtils.printCompany(company1);


        } catch (CouponSystemExceptions e) {
            System.out.println(e);
        }

    }
}
