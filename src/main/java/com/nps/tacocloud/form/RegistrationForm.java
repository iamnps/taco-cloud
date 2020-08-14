package com.nps.tacocloud.form;

import com.nps.tacocloud.data.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by peishen.nie on 2020/7/24.
 */

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
