package com.nps.tacocloud.service.impl;

import com.nps.tacocloud.dao.UserRepository;
import com.nps.tacocloud.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by peishen.nie on 2020/7/23.
 */
@Service
public class UserRepositoryUserDetailService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserRepositoryUserDetailService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(null != user){
            return user;
        }

        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
