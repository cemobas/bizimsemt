package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    public Page<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        this.orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public Optional<Order> find(String id) {
        return orderRepository.findById(new ObjectId(id));
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
