package com.jb.couponsysbhp2.utils;


import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;


/**
 * Created by kobis on 19 Jul, 2020
 */


public class PrintUtils {
    private static final int LEN = 30;
    private static final String SPACE = "\t\t\t";
    private static final int LINE_LENGHT = 70;
    private static int COUNTER = 0;
    private static final Object object = new Object();

    public static void printError(String key, String value) {
        printTableInfo(Arrays.asList("key", "description"));
        printErrorRow(key, value);
    }

    public static void printBoolean(String key, boolean value) {
        printTableInfo(Arrays.asList("key", "value"));
        printBooleanRow(key, value);
    }

    public static void printCompanies(List<Company> companies) {
        printTableInfo(Arrays.asList("id", "name", "email", "password", "coupons"));
        for (Company company : companies) {
            printCompany(company);
        }
        System.out.println();
    }
    private int id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @ToString.Exclude
    //@JoinColumn(name="company_id")
    private Company company;
    private String title;
    private String description ;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    public static void printCoupons(List<Coupon> coupons) {
        printTableInfo(Arrays.asList("id","category","companyID" ,"title", "description", "start_date", "end_date", "amount", "price", "image"));
        for (Coupon coupon : coupons) {
            printCoupon(coupon);
        }
    }

    public static void printCustomers(List<Customer> customers) {
        printTableInfo(Arrays.asList("id", "first_name", "last_name", "email", "password", "coupons"));
        for (Customer customer : customers) {
            printCustomer(customer);
        }
    }

//    public static void printCutomersVsCoupons(List<CustomersVsCoupons> customersVsCoupons){
//        printTableInfo(Arrays.asList("customer_id","coupons_id"));
//        for (CustomersVsCoupons customerVsCoupon :customersVsCoupons) {
//            printCutomerVsCoupon(customerVsCoupon);
//        }
//    }


    public static void printCompany(Company company) {
        synchronized (object) {
            printWithTab(String.valueOf(company.getId()));
            printWithTab(company.getName());
            printWithTab(company.getEmail());
            printWithTab(company.getPassword());
            printWithTab(company.getCoupons().toString());
            System.out.println();
        }
    }

    public static void printBooleanRow(String key, boolean value) {
        synchronized (object) {
            printWithTab(key);
            printWithTab(String.valueOf(value));
            System.out.println();
        }
    }

    public static void printErrorRow(String key, String value) {
        synchronized (object) {
            printWithTab(key);
            printWithTab(value);
            System.out.println();
        }
    }

    public static void printCoupon(Coupon coupon) {
        printWithTab(String.valueOf(coupon.getId()));
        printWithTab(String.valueOf(coupon.getCategory().toString()));
        printWithTab(String.valueOf(coupon.getCompany().getId()));
        printWithTab(coupon.getTitle());
        printWithTab(coupon.getDescription());
        printWithTab(coupon.getStartDate().toString());
        printWithTab(coupon.getEndDate().toString());
        printWithTab(String.valueOf(coupon.getAmount()));
        printWithTab(String.valueOf(coupon.getPrice()));
        printWithTab(coupon.getImage());
        System.out.println();
    }

    public static void printCustomer(Customer customer) {
        printWithTab(String.valueOf(customer.getId()));
        printWithTab(customer.getFirstName());
        printWithTab(customer.getLastName());
        printWithTab(customer.getEmail());
        printWithTab(customer.getPassword());
        printWithTab(customer.getCoupons().toString());
        System.out.println();

    }


//    public static void printCutomerVsCoupon(CustomersVsCoupons customersVsCoupons){
//        printWithTab(String.valueOf(customersVsCoupons.getCustomer_id()));
//        printWithTab(String.valueOf(customersVsCoupons.getCoupons_id()));
//        System.out.println();
//    }


    public static void printWithTab(String content) {
        String tldrContent = null;
        if (content.length() > 10) {
            tldrContent = String.format("%4s", content.substring(0, Math.min(LEN - 2, content.length())));
        } else {
            tldrContent = String.format("%4s", content);
        }
        int fillInSpaces = LEN - tldrContent.length();
        int half = fillInSpaces / 2;

        System.out.print("|");
        for (int i = 0; i < half - 1; i++) {
            System.out.print(" ");
        }
        System.out.print(tldrContent);
        if (fillInSpaces % 2 != 0) {
            half++;
        }
        for (int i = 0; i < half; i++) {
            System.out.print(" ");
        }

    }

    public static void printSeparatorLine(Character c, int argumentNumber) {
        int separaters = argumentNumber * LEN;
        for (int i = 0; i < separaters; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void printTableInfo(List<String> columns) {
        printSeparatorLine('-', columns.size());
        for (String string : columns) {
            printWithTab(string);
        }
        System.out.println();
        printSeparatorLine('-', columns.size());
    }


    public static void printTestInfo(String testName) {
        System.out.println(String.format("Unit Test : %d | Test: %s", ++COUNTER, testName));
    }


    public static void printSeparateLine() {
        for (int i = 0; i < LINE_LENGHT; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
