package com.krakus.bizimsemt.repository;

import com.krakus.bizimsemt.domain.Buyer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<Buyer, ObjectId> {
}
