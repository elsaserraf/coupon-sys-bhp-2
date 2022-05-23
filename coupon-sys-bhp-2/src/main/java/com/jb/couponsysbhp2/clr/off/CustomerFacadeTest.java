package com.jb.couponsysbhp2.clr.off;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.ClientType;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.security.LoginManager;
import com.jb.couponsysbhp2.services.CompanyService;
import com.jb.couponsysbhp2.services.CustomerService;
import com.jb.couponsysbhp2.utils.Art;
import com.jb.couponsysbhp2.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Order(4)
public class CustomerFacadeTest implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private CustomerService customerService;

    private final String email="cust4.shaked@gmail.com";
    private final String password="1234";


    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println(Art.CUSTOMER_FACADE);
            customerService = (CustomerService) loginManager.login(email, password, ClientType.CUSTOMER);
            System.out.println("****************************LOGIN SUCCESSFULLY*******************************\n");
            int customerId=customerService.getCustomerIdByEmailAndPassword(email,password);
            System.out.printf("Customer Id Logged %d\n",customerId);

            try {

                customerService.purchaseCoupon(customerId,3);
                System.out.println("***********************PURCHASED COUPON 3 SUCCESSFULLY***************************\n");
            } catch (CouponSystemExceptions e) {
                //e.printStackTrace();
                System.out.println(e);
            }
            try {
                customerService.purchaseCoupon(customerId,2);
                System.out.println("***********************PURCHASED COUPON 2 SUCCESSFULLY***************************\n");
            } catch (CouponSystemExceptions e) {
                System.out.println(e);
            }
            System.out.printf("***********************LIST COUPONS FOR CUSTOMER %s COUPONS***************************\n",customerId);
            List<Coupon> coupons = customerService.getCustomerCoupons(customerId);
            PrintUtils.printCoupons(coupons);

            System.out.printf("***********************LIST COUPONS FOR CUSTOMER %s BY CATEGORY***************************\n",customerId);
            List<Coupon> coupons1 = customerService.getCustomerCoupons(customerId,"Home");
            PrintUtils.printCoupons(coupons1);
            System.out.printf("***********************LIST COUPONS FOR CUSTOMER %s BY PRICE***************************\n",customerId);
            List<Coupon> coupons2 = customerService.getCustomerCoupons(customerId,15);
            PrintUtils.printCoupons(coupons2);
            System.out.printf("***********************DETAILS CUSTOMER %s ***************************\n",customerId);
            Customer customer = customerService.getCustomerDetails(customerId);
            PrintUtils.printCustomer(customer);

        } catch (CouponSystemExceptions e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }
}
