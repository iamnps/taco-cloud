package com.nps.tacocloud.form;

import com.nps.tacocloud.data.TacoUser;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by peishen.nie on 2020/7/24.
 */

public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public TacoUser toUser(PasswordEncoder passwordEncoder){
        return new TacoUser(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
