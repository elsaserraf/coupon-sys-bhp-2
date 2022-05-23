package com.jb.couponsysbhp2.Exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {

    ID_NOT_EXIST("....."),
    ID_ALREADY_EXIST("......"),
    EMAIL_ALREADY_EXIST("....."),
    NAME_ALREADY_EXIST("......"),
    EMAIL_OR_PASSWORD_WRONG("......"),
    COMPANY_ID_NOT_CORRECT("......."),
    TITLE_ALREADY_EXIST("........"),
    COUPON_NOT_EXIST(".......");

    private String msg;

    ErrMsg (String msg){
        this.msg=msg;
    }


}
