package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peishen.nie on 2020/7/15.
 */
@Repository
public interface OrderRepository{

    Order save(Order order);
}
