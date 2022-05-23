package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.Exceptions.ErrMsg;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.repos.CompanyRepository;
import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.repos.CustomerRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {


    @Override
    public void addCompany(Company company) throws CouponSystemExceptions {
        if(companyRepository.existsByEmail(company.getEmail())){
            throw new CouponSystemExceptions("EMAIL_ALREADY_EXIST");
        }
        if (companyRepository.existsByName(company.getName())){
            throw new CouponSystemExceptions("NAME_ALREADY_EXIST");
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemExceptions {
        if (!companyRepository.existsById(companyId)){
            throw new CouponSystemExceptions("ID_NOT_EXIST");
        }
        if(companyId != company.getId()){
            throw new CouponSystemExceptions("Company Id is wrong");
        }

        companyRepository.saveAndFlush(company);

    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemExceptions {
        if (!companyRepository.existsById(companyId)){
            throw new CouponSystemExceptions("ID_NOT_EXIST");
        }
        companyRepository.deleteById(companyId);

    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemExceptions {
        return companyRepository.findById(companyId).orElseThrow(()->new CouponSystemExceptions("ID_NOT_EXIST"));
    }

    @Override
    public boolean login(String email, String password) throws CouponSystemExceptions {
        if (!(email.equals("admin@admin.com") && password.equals("admin")))
            throw new CouponSystemExceptions("EMAIL_OR_PASSWORD_WRONG");

        return true;
    }
}
