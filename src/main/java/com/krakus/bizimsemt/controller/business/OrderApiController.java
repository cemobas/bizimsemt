package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.model.OrderDto;
import com.krakus.bizimsemt.service.BuyerService;
import com.krakus.bizimsemt.service.OrderService;
import com.krakus.bizimsemt.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private OrderAssembler orderAssembler;

    @Loggable
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return this.orderService.getAllOrders().stream().map(order -> orderAssembler.toModel(orderService.find(order.getId())
            .orElseThrow(() -> new NoSuchElementException("Order " + order.getId() + " not found"))
    )).collect(Collectors.toList());
    }

    @Loggable
    @GetMapping(path = "/all")
    public PagedModel<Order> getAllOrders(Pageable pageable, PagedResourcesAssembler pagedAssembler) {
        return pagedAssembler.toModel(this.orderService.getAllOrders(pageable), orderAssembler);
    }


    @Loggable
    @GetMapping(path = "/{orderId}")
    public OrderDto find(@PathVariable(value = "orderId") String id) {
        OrderDto orderDto = orderAssembler.toModel(orderService.find(id)
                .orElseThrow(() -> new NoSuchElementException("Order " + id + " not found"))
        );
        return orderDto;
    }

    @Loggable
    @RequestMapping(path = "/addOrder", method=RequestMethod.POST)
    public String addOrder(@RequestBody Order order) {
        order.setBuyer(buyerService.add(order.getBuyer()));
        order.setSeller(sellerService.add(order.getSeller()));
        Order newOrder = orderService.add(order);
        if (newOrder != null) {
            return "success";
        }
        return "failure";
    }

    // You can apply @Validated in DTOs for put/post/patch operations (and verify existence of updated data explicitly inside the service method)
}
