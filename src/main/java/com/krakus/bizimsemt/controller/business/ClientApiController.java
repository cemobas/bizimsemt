package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.model.Client;
import com.krakus.bizimsemt.service.BuyerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/clients")
public class ClientApiController {
    @Autowired
    private BuyerServices buyerServices;
    @Autowired
    private ClientAssembler assembler;

    @Loggable
    @GetMapping(path = "/{clientId}")
    public Client find(@PathVariable(value = "clientId") String id) {
        Client client = assembler.toModel(buyerServices.find(id)
                .orElseThrow(() -> new NoSuchElementException("Client " + id + " not found"))
        );
        return client;
    }
}
