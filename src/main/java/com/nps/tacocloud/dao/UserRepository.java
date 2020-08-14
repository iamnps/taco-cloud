package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by peishen.nie on 2020/7/23.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
