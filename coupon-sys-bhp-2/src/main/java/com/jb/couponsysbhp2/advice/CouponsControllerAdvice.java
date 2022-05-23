package com.jb.couponsysbhp2.advice;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    public class CouponsControllerAdvice {

        @ExceptionHandler(value = {CouponSystemExceptions.class})
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        ErrDetails errDetails(Exception e){
            return new ErrDetails(e.getMessage());
        }
}
