package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.repository.BuyerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerServices {

    private BuyerRepository buyerRepository;

    @Autowired
    public BuyerServices(BuyerRepository buyerRepository) {
        super();
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getAllBuyers(){
        List<Buyer> buyers = new ArrayList<>();
        this.buyerRepository.findAll().forEach(buyers::add);
        return buyers;
    }

    @Loggable
    public Optional<Buyer> find(String id){
        return buyerRepository.findById(new ObjectId(id));
    }

    public Buyer add(Buyer buyer) {
        Buyer newBuyer = null;
        if (buyer.getName() != null) {
            newBuyer =  buyerRepository.save(buyer);
        }
        System.out.println("Buyer saved with id: " + newBuyer.getId().toString());
        return newBuyer;
    }
}
