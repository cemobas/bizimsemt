package com.krakus.bizimsemt.repository;

import com.krakus.bizimsemt.domain.Seller;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, ObjectId> {

}
