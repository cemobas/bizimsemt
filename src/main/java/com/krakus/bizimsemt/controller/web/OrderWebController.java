package com.krakus.bizimsemt.controller.web;

import com.krakus.bizimsemt.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderWebController {
    private OrderServices orderServices;

    @Autowired
    public OrderWebController(OrderServices orderServices){
        super();
        this.orderServices = orderServices;
    }

    @GetMapping
    public String getOrders(Model model){
        this.orderServices.getAllOrders().stream().forEach(o -> {
            System.out.println(o.getId().toString());
        });
        model.addAttribute("orders", this.orderServices.getAllOrders());
        return "orders";
    }
}
