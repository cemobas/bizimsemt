package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.model.Client;
import com.krakus.bizimsemt.service.BuyerService;
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
    private BuyerService buyerService;
    @Autowired
    private ClientAssembler assembler;

    @GetMapping(path = "/{clientId}")
    public Client find(@PathVariable(value = "clientId") String id) {
        Client client = assembler.toModel(buyerService.find(id)
                .orElseThrow(() -> new NoSuchElementException("Client " + id + " not found"))
        );
        return client;
    }
}
