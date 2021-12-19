package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.TacoUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by peishen.nie on 2020/7/23.
 */
public interface UserRepository extends CrudRepository<TacoUser, Long> {

    TacoUser findByUsername(String username);
}
