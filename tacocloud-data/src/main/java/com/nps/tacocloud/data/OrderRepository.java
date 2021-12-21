package com.nps.tacocloud.data;

import com.nps.tacocloud.domain.Order;
import java.util.List;
import com.nps.tacocloud.domain.TacoUser;

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
