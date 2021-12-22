package com.nps.tacocloud.security;

import com.nps.tacocloud.data.TacoUserRepository;
import com.nps.tacocloud.domain.TacoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by peishen.nie on 2020/7/24.
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private TacoUserRepository tacoUserRepository;

    public RegistrationController(TacoUserRepository tacoUserRepository){
        this.tacoUserRepository = tacoUserRepository;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        TacoUser tacoUser = new TacoUser();
        tacoUser.setUsername(form.getUsername());
        tacoUser.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        tacoUser.setFullname(form.getFullname());
        tacoUser.setStreet(form.getStreet());
        tacoUser.setCity(form.getCity());
        tacoUser.setState(form.getState());
        tacoUser.setZip(form.getZip());
        tacoUser.setPhoneNumber(form.getPhone());
        tacoUserRepository.save(tacoUser);
        return "redirect:/login";
    }

}
