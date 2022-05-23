package com.jb.couponsysbhp2.clr.on;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.repos.CompanyRepository;
import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.repos.CustomerRepository;
import com.jb.couponsysbhp2.utils.PrintUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Order(1)
public class Init implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    @Override
    public void run(String... args) throws Exception {

        Company company1 = Company.builder().
                            name("Comp1").
                            email("comp1@gmail.com").
                            password("1234").build();

        Company company2 = Company.builder().
                name("Comp2").
                email("comp2@gmail.com").
                password("1234").build();

        Company company3 = Company.builder().
                name("Comp3").
                email("comp3@gmail.com").
                password("1234").build();

        Company company4 = Company.builder().
                name("Comp4").
                email("comp4@gmail.com").
                password("1234").build();

        Company company5 = Company.builder().
                name("Comp5").
                email("comp5@gmail.com").
                password("1234").build();

        companyRepository.saveAll(Arrays.asList(company1,company2,company3,company4,company5));
        //companyRepository.findAll().forEach(System.out::println);
        PrintUtils.printCompanies(companyRepository.findAll());


        Customer customer1 = Customer.builder().
                firstName("cust1").
                lastName("cohen").
                email("cust1.cohen@gmail.com").
                password("1234").
                build();

        Customer customer2 = Customer.builder().
                firstName("cust2").
                lastName("lasry").
                email("cust1.lasry@gmail.com").
                password("1234").
                build();

        Customer customer3 = Customer.builder().
                firstName("cust3").
                lastName("levy").
                email("cust3.levy@gmail.com").
                password("1234").
                build();

        Customer customer4 = Customer.builder().
                firstName("cust4").
                lastName("shaked").
                email("cust4.shaked@gmail.com").
                password("1234").
                build();

        Customer customer5 = Customer.builder().
                firstName("cust5").
                lastName("toledano").
                email("cust5.toledano@gmail.com").
                password("1234").
                build();

        customerRepository.saveAll(Arrays.asList(customer1,customer2,customer3,customer4,customer5));
       // customerRepository.findAll().forEach(System.out::println);
        PrintUtils.printCustomers(customerRepository.findAll());

        //Coupon cp1 = new Coupon(1, Category.Vacation,"Holidays in Rhodes","Renovated 5-star hotel located in the northern part of the island of Rhodes and opposite the beach, a 6-minute drive from the Acropolis of Rhodes and a 10-minute drive from Rodini Park.", Date.valueOf("2022-03-01"),Date.valueOf("2022-06-01"),10,1226.55,"Olympic.jpg");
        //Coupon cp2 = new Coupon(6,Category.Electricity,"Aspirator","Version V6-ALLERGY ATL-3080",Date.valueOf("2022-03-01"),Date.valueOf("2022-12-31"),50,400,"aspirator.jpg");
        //Coupon cp3 = new Coupon(2,Category.Home,"Table","Table of wood with 4 chairs",Date.valueOf("2022-02-10"),Date.valueOf("2022-08-30"),86,1050,"table.jpg");
        //Coupon cp4 = new Coupon(6,Category.Electricity,"Electric Boiler","Version V7",Date.valueOf("2022-03-01"),Date.valueOf("2022-12-31"),60,700,"boiler.jpg");
        //Coupon cp5 = new Coupon(2,Category.Home,"Sofa","Sofa four places",Date.valueOf("2022-02-10"),Date.valueOf("2022-08-30"),15,2500,"sofa.jpg");
        //Coupon cp6 = new Coupon(5,Category.Restaurant,"Segev","Restaurant of sushi",Date.valueOf("2022-02-25"),Date.valueOf("2022-12-30"),25,200,"segev.jpg");

        Coupon coupon1 = Coupon.builder()
                .title("coup1")
                .description("first coupon")
                .category(Category.Vacation)
                .price(10.1)
                .amount(100)
                .image("coup1.txt")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(10)))
                .company(company1)
                .build();


        Coupon coupon2 = Coupon.builder()
                .title("coup2")
                .description("second coupon")
                .category(Category.Home)
                .price(11.2)
                .amount(200)
                .image("coup2.txt")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(20)))
                .company(company1)
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("coup3")
                .description("thrid coupon")
                .category(Category.Electricity)
                .price(13.2)
                .amount(250)
                .image("coup3.txt")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .company(company2)
                .build();

        couponRepository.saveAll(Arrays.asList(coupon1,coupon2,coupon3));
        //couponRepository.findAll().forEach(System.out::println);
        PrintUtils.printCoupons(couponRepository.findAll());

        couponRepository.addPurchaseCoupon(1,1);
        couponRepository.addPurchaseCoupon(1,2);
    }
}
