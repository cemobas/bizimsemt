package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.service.BuyerServices;
import com.krakus.bizimsemt.service.OrderServices;
import com.krakus.bizimsemt.service.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderApiController {
    @Autowired
    private OrderServices orderServices;
    @Autowired
    private BuyerServices buyerServices;
    @Autowired
    private SellerServices sellerServices;

    @GetMapping(path = "/getOrders")
    public List<Order> getAllOrders() {
        return this.orderServices.getAllOrders();
    }

    @RequestMapping(path = "/addOrder", method=RequestMethod.POST)
    public String addOrder(@RequestBody Order order) {
        order.setBuyer(buyerServices.add(order.getBuyer()));
        order.setSeller(sellerServices.add(order.getSeller()));
        Order newOrder = orderServices.add(order);
        if (newOrder != null) {
            return "success";
        }
        return "failure";
    }
}
