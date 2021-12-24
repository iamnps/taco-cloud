package com.nps.tacocloud.kafka;

import com.nps.tacocloud.domain.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);
}
