package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.model.Client;
import com.krakus.bizimsemt.repository.OrderRepository;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Rating Assembler, convert TourRating to a Hateoas Supported Rating class
 *
 * Created by maryellenbowman.
 */
@Component
public class ClientAssembler extends RepresentationModelAssemblerSupport<Buyer, Client> {

    //Helper to fetch Spring Data Rest Repository links.
    private RepositoryEntityLinks entityLinks;

    public ClientAssembler(RepositoryEntityLinks entityLinks) {
        super(ClientApiController.class, Client.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public Client toModel(Buyer buyer) {
        Client client = new Client(buyer.getId(), buyer.getName(), buyer.getSurname(), buyer.getAddresses());

        // "self" : ".../clients/{clientId}"
        LinkBuilder ratingLink = linkTo(methodOn(ClientApiController.class).find(buyer.getId()));
        client.add(ratingLink.withSelfRel());

        return client;
    }
}
