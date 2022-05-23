package com.jb.couponsysbhp2.security;

import com.jb.couponsysbhp2.Exceptions.CouponSystemExceptions;
import com.jb.couponsysbhp2.beans.ClientType;
import com.jb.couponsysbhp2.services.*;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    private final AdminServiceImpl adminServiceImpl;
    private final CompanyServiceImpl companyServiceImpl;
    private final CustomerServiceImpl customerServiceImpl;

    public LoginManager(AdminServiceImpl adminServiceImpl, CompanyServiceImpl companyServiceImpl, CustomerServiceImpl customerServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
        this.companyServiceImpl = companyServiceImpl;
        this.customerServiceImpl = customerServiceImpl;
    }


    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemExceptions {

        ClientService clientService = null;
        switch (clientType) {
            case ADMINISTRATOR:
                if(adminServiceImpl.login(email, password)) {
                    clientService = (ClientService) adminServiceImpl;
                }
                break;
            case COMPANY:
                if(companyServiceImpl.login(email, password)) {
                    clientService = (ClientService) companyServiceImpl;
                }
                break;
           case CUSTOMER:
                if(customerServiceImpl.login(email, password)) {
                    clientService = (ClientService) customerServiceImpl;
                }
                break;
        }
        if (clientService==null) {
            throw new CouponSystemExceptions("EMAIL_OR_PASSWORD_WRONG");
        }
        return clientService;
    }


}
