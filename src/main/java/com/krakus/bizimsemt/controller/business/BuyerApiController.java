package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buyers")
public class BuyerApiController {
    @Autowired
    private BuyerRepository buyerRepository;

    @Loggable
    @PostMapping(path = "/create")
    public Buyer create(Buyer buyer) {
        Buyer newBuyer = buyerRepository.save(buyer);
        return newBuyer;
    }
}
