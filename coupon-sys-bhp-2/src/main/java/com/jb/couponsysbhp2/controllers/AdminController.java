package com.jb.couponsysbhp2.controllers;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.services.AdminService;
import com.jb.couponsysbhp2.services.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("couponsys/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /*@Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }*/

    @PostMapping("/login")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public boolean login(@RequestParam("email") String email,@RequestParam("password") String password) throws CouponSystemExceptions {
        return ((AdminServiceImpl)adminService).login(email,password);
    }



    @PostMapping("/add/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemExceptions {
        adminService.addCompany(company);
    }

    @PutMapping("update/company/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int id,@RequestBody Company company) throws CouponSystemExceptions {
        adminService.updateCompany(id,company);
    }

    @DeleteMapping("delete/company/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) throws CouponSystemExceptions {
        adminService.deleteCompany(id);
    }

    /*List<Company> getAllCompanies();
    Company getSingleCompany(int companyId) throws CouponSystemExceptions;*/

    @GetMapping("/companies")
    public List<Company> getAllCompanies(){
        return  adminService.getAllCompanies();
    }

    @GetMapping("/company/{id}")
    public Company getSingleCompany(@PathVariable int id) throws CouponSystemExceptions {
        return adminService.getSingleCompany(id);
    }




}

