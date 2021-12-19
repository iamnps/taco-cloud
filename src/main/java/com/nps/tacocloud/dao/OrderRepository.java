package com.nps.tacocloud.dao;

import java.util.List;

import com.nps.tacocloud.data.Order;
import com.nps.tacocloud.data.TacoUser;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by peishen.nie on 2020/7/15.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(TacoUser user, Pageable pageable);
}
