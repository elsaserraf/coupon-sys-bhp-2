package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.Exceptions.ErrMsg;
import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    @Override
    public boolean login(String email, String password) throws CouponSystemExceptions {
        if (!companyRepository.existsByEmailAndPassword(email, password))
            throw new CouponSystemExceptions("EMAIL_OR_PASSWORD_WRONG");
        return true;
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws CouponSystemExceptions {
        if (companyId != coupon.getCompany().getId())
            throw new CouponSystemExceptions("COMPANY_ID_NOT_CORRECT");
        Company curr = companyRepository.getById(companyId);
        for (Coupon coup : curr.getCoupons()) {
            if (coup.getTitle().equals(coupon.getTitle()))
                throw new CouponSystemExceptions("TITLE_ALREADY_EXIST");

        }

        this.couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int companyId, Coupon coupon) throws CouponSystemExceptions {
        if (companyId != coupon.getCompany().getId())
            throw new CouponSystemExceptions("COMPANY_ID_NOT_CORRECT");

        Coupon coupon1 = couponRepository.findTop1ByCompanyIdAndId(companyId, coupon.getId());
        if (coupon1 != null)
            couponRepository.saveAndFlush(coupon);
        else
            throw new CouponSystemExceptions("COUPON_NOT_EXIST");

    }

    @Override
    public void deleteCoupon(int companyId, int couponId) {
        couponRepository.deleteCustomerCoupons(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) {
        return couponRepository.findAllByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) {
        return couponRepository.findAllByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) {
        Company curr = companyRepository.getById(companyId);
        curr.setCoupons(getCompanyCoupons(companyId));
        return curr;
    }

    @Override
    public int getCompanyIdByEmailAndPassword(String email, String password) {
        return companyRepository.findIdByEmailAndPassword(email, password);
    }
}
