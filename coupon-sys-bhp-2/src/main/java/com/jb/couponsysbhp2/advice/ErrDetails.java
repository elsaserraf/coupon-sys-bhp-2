package com.jb.couponsysbhp2.advice;

import lombok.Data;

@Data

public class ErrDetails {

    //private final String key = "Meou";
    private String value;

    public ErrDetails(String value) {
        this.value = value;
    }
}