package com.jb.couponsysbhp2.repos;

import com.jb.couponsysbhp2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company,Integer> {

 /* @Query("select count(*) from spring-coupon-system-2-bhp.companies where name = :name or email = :email")
    public int existsByNameOrEmail(@Param("name") String name,@Param("email") String email);*/
  boolean existsByEmail(String email);
  boolean existsByName(String email);
  boolean existsByEmailAndPassword(String email,String password);
  @Query(value = "select id FROM `spring-coupon-system-2-bhp`.companies where email=:email and password=:password",nativeQuery = true)
  int findIdByEmailAndPassword(@Param("email") String email,@Param("password") String password);

}
