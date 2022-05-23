package com.jb.couponsysbhp2.beans;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Singular
    @JsonManagedReference
    private List<Coupon> coupons = new ArrayList<>();
}
