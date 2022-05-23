package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.Company;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface AdminService{

    void addCompany(Company company) throws CouponSystemExceptions;
    void updateCompany(int companyId,Company company) throws CouponSystemExceptions;
    void deleteCompany(int companyId) throws CouponSystemExceptions;

    List<Company> getAllCompanies();
    Company getSingleCompany(int companyId) throws CouponSystemExceptions;

}
