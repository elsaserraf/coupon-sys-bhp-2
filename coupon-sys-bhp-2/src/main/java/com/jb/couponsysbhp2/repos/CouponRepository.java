package com.jb.couponsysbhp2.repos;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    List<Coupon> findAllByCompanyId(int companyId);
    List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findAllByCompanyIdAndPriceLessThan(int companyId,double maxPrice);
    Coupon findTop1ByCompanyIdAndId(int companyId,int Id);


    @Modifying
    @Query(value="DELETE FROM `coupons` WHERE `coupons`.`end_date` < now()",nativeQuery = true)
    void deleteExpiredCoupons();

    @Modifying
    @Query(value="DELETE FROM `customers_coupons` WHERE coupons_id = :coupons_id",nativeQuery = true)
    void deleteCustomerCoupons(@Param("coupons_id") int coupons_id);


    @Query(value="select exists(select * from `customers_coupons` WHERE customer_id = :customer_id and coupons_id = :coupons_id)",nativeQuery = true)
    int existsByCustomerIdAndCouponId(@Param("customer_id") int customer_id,@Param("coupons_id") int coupons_id);


    @Modifying
    @Query(value="insert into `customers_coupons` (customer_id,coupons_id) values(:customer_id,:coupons_id)",nativeQuery = true)
    void addPurchaseCoupon(@Param("customer_id") int customer_id,@Param("coupons_id") int coupons_id);

    @Query(value="select coupons_id from `customers_coupons` where customer_id=:customer_id",nativeQuery = true)
    List<Integer> getListCouponsIdByCustomerId(@Param("customer_id") int customer_id);

    @Query(value="select A.coupons_id from customers_coupons A,coupons B where A.customer_id=:customer_id and A.coupons_id=B.id and B.category=:category",nativeQuery = true)
    List<Integer> getListCouponsIdByCustomerIdAndCategory(@Param("customer_id") int customer_id,@Param("category") String categoryName);

    @Query(value="select A.coupons_id from customers_coupons A,coupons B where A.customer_id=:customer_id and A.coupons_id=B.id and B.price<:price",nativeQuery = true)
    List<Integer> getListCouponsIdByCustomerIdAndPrice(@Param("customer_id") int customer_id,@Param("price") double price);
}

