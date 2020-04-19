package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.model.OrderDto;
import com.krakus.bizimsemt.service.BuyerServices;
import com.krakus.bizimsemt.service.OrderServices;
import com.krakus.bizimsemt.service.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
    @Autowired
    private OrderServices orderServices;
    @Autowired
    private BuyerServices buyerServices;
    @Autowired
    private SellerServices sellerServices;
    @Autowired
    private OrderAssembler orderAssembler;

    @Loggable
    @GetMapping(path = "/all")
    public List<Order> getAllOrders() {
        return this.orderServices.getAllOrders();
    }


    @Loggable
    @GetMapping(path = "/{orderId}")
    public OrderDto find(@PathVariable(value = "orderId") String id) {
        OrderDto orderDto = orderAssembler.toModel(orderServices.find(id)
                .orElseThrow(() -> new NoSuchElementException("Order " + id + " not found"))
        );
        return orderDto;
    }

    @Loggable
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
