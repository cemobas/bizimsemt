package com.krakus.bizimsemt.controller.web;

import com.krakus.bizimsemt.controller.business.OrderApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderWebController {
    //private OrderServices orderServices;
    private OrderApiController orderApiController;

    @Autowired
    public OrderWebController(OrderApiController orderApiController){
        super();
        this.orderApiController = orderApiController;
    }

    @GetMapping
    public String getOrders(Model model){
        model.addAttribute("orders", this.orderApiController.getAllOrders());
        return "orders";
    }
}
