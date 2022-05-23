package com.jb.couponsysbhp2.clr.off;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.ClientType;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.security.LoginManager;
import com.jb.couponsysbhp2.services.AdminService;
import com.jb.couponsysbhp2.utils.Art;
import com.jb.couponsysbhp2.utils.PrintUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Thread.sleep;

@Component
@Order(2)
public class AdminFacadeTest implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private AdminService adminService;
    @Override
    public void run(String... args) throws Exception {
        try {
            adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
            System.out.println("****************************LOGIN SUCCESSFULLY*******************************\n");
        } catch (CouponSystemExceptions e) {
            e.printStackTrace();
        }
        //AdminFacade adminFacade = (AdminFacade) loginManager.login("dmin@admin.com", "admin", ClientType.Administrator); //with Error
        System.out.println(Art.ADMIN_FACADE);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ Add Companies @@@@@@@@@@@@@@@@@@@@@@@@");

        Company company6 = Company.builder().
                name("Comp6").
                email("comp6@gmail.com").
                password("123456").build();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ Get all companies @@@@@@@@@@@@@@@@@@@@@@@@");
        List<Company> companies = adminService.getAllCompanies();
        PrintUtils.printCompanies(companies);
        sleep(3000);

        try {
            adminService.addCompany(company6);
        } catch (CouponSystemExceptions e) {
            e.printStackTrace();
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ Update Companies @@@@@@@@@@@@@@@@@@@@@@@@");

        try {
            adminService.updateCompany(3,company6);
        } catch (CouponSystemExceptions e) {
            e.printStackTrace();
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ Get all companies @@@@@@@@@@@@@@@@@@@@@@@@");
        List<Company> companies2 = adminService.getAllCompanies();
        PrintUtils.printCompanies(companies2);
        sleep(3000);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ Get Single Company @@@@@@@@@@@@@@@@@@@@@@@@");
        try {
            Company comp = adminService.getSingleCompany(5);
            PrintUtils.printCompany(comp);
        } catch (CouponSystemExceptions e) {
            e.printStackTrace();
        }




    }





}
