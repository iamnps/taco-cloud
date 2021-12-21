package com.nps.tacocloud.security;

import com.nps.tacocloud.data.UserRepository;
import com.nps.tacocloud.domain.TacoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by peishen.nie on 2020/7/24.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        TacoUser tacoUser = new TacoUser(form.getUsername(), new BCryptPasswordEncoder().encode(form.getPassword()),
                form.getFullname(), form.getStreet(),
                form.getCity(), form.getState(),
                form.getZip(), form.getPhone());
        userRepository.save(tacoUser);
        return "redirect:/login";
    }

}
