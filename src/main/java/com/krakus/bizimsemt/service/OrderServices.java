package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServices {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServices(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        this.orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public Order add(Order order) {

        Order newOrder = null;

        if (order.getBuyer() != null) {
            order.setLastModified(Calendar.getInstance().getTime());
            newOrder =  orderRepository.save(order);
        }

        return newOrder;
    }
}
