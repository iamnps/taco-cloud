package com.nps.tacocloud.controller.order;

import com.nps.tacocloud.data.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by peishen.nie on 2020/7/14.
 */

@Controller
@RequestMapping("orders")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        if(errors.hasErrors()){
            return "orderForm";
        }
        logger.info("Order submitted:" + order);
        return "redirect:/";
    }
}
