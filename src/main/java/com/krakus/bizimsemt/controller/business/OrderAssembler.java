package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.model.Client;
import com.krakus.bizimsemt.model.OrderDto;
import com.krakus.bizimsemt.repository.BuyerRepository;
import com.krakus.bizimsemt.repository.OrderRepository;
import com.krakus.bizimsemt.repository.SellerRepository;
import org.bson.types.ObjectId;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Rating Assembler, convert TourRating to a Hateoas Supported Rating class
 *
 * Created by maryellenbowman.
 */
@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<Order, OrderDto> {

    //Helper to fetch Spring Data Rest Repository links.
    private RepositoryEntityLinks entityLinks;

    public OrderAssembler(RepositoryEntityLinks entityLinks) {
        super(OrderApiController.class, OrderDto.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public OrderDto toModel(Order order) {
        OrderDto orderDto = new OrderDto(order.getId(),
                order.getBuyer().getName() + " " + order.getBuyer().getSurname(),
                order.getSeller().getName(), order.getItems(), new ObjectId(order.getId()).getDate(), order.getLastModified());

        // "self" : ".../api/orders/{orderId}"
        LinkBuilder orderLink = linkTo(methodOn(OrderApiController.class).find(order.getId()));
        orderDto.add(orderLink.withSelfRel());

        //"order" : ".../api/clients/{clientId}"
        LinkBuilder clientLink = linkTo(methodOn(ClientApiController.class).find(order.getBuyer().getId()));
        orderDto.add(clientLink.withRel("client"));

        //"order" : ".../sellers/{sellerId}"
        Link sellerLink = entityLinks.linkToItemResource(SellerRepository.class, order.getSeller().getId());
        orderDto.add(sellerLink.withRel("seller"));

        return orderDto;
    }
}
