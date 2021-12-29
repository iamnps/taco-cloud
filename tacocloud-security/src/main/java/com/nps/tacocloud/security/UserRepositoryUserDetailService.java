package com.nps.tacocloud.security;

import com.nps.tacocloud.data.TacoUserRepository;
import com.nps.tacocloud.domain.TacoUser;
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

    private TacoUserRepository repository;

    @Autowired
    public UserRepositoryUserDetailService(TacoUserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TacoUser user = repository.findByUsername(username);
        if(null != user){
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
