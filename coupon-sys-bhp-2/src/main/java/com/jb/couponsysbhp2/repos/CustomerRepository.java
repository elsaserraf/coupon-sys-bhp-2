package com.jb.couponsysbhp2.repos;

import com.jb.couponsysbhp2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmailAndPassword(String email, String password);

    @Query(value = "select id FROM `spring-coupon-system-2-bhp`.customers where email=:email and password=:password",nativeQuery = true)
    int findIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
