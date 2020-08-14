package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by peishen.nie on 2020/7/15.
 */
@Repository
public interface OrderRepository{

    Order save(Order order);
}
