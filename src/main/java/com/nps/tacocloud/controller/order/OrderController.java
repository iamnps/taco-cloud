package com.nps.tacocloud.controller.order;

import com.nps.tacocloud.dao.OrderRepository;
import com.nps.tacocloud.data.Order;
import com.nps.tacocloud.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * Created by peishen.nie on 2020/7/14.
 */

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        /*if(errors.hasErrors()){
            return "orderForm";
        }*/
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        logger.info("Order submitted:" + order);
        return "redirect:/";
    }
}
