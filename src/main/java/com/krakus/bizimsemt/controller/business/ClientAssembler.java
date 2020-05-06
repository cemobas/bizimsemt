package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientAssembler extends RepresentationModelAssemblerSupport<Buyer, Client> {

    // Helper to fetch Spring Data Rest Repository links.
    @Autowired
    private RepositoryEntityLinks entityLinks;

    public ClientAssembler() {
        super(ClientApiController.class, Client.class);
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
