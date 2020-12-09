package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Order;
import com.nps.tacocloud.data.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by peishen.nie on 2020/7/22.
 */
public interface OrderARepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}


