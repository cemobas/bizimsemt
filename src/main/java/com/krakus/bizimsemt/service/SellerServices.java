package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.domain.Seller;
import com.krakus.bizimsemt.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServices {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerServices(SellerRepository sellerRepository) {
        super();
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers(){
        List<Seller> sellers = new ArrayList<>();
        this.sellerRepository.findAll().forEach(sellers::add);
        return sellers;
    }

    public Seller add(Seller seller) {
        Seller newSeller = null;
        if (seller.getName() != null) {
            newSeller =  sellerRepository.save(seller);
        }
        System.out.println("Seller saved with id: " + newSeller.getId().toString());
        return newSeller;
    }
}
