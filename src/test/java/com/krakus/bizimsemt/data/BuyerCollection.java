package com.krakus.bizimsemt.data;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.function.FetchCollectionFunction;
import com.krakus.bizimsemt.repository.BuyerRepository;
import com.mongodb.BasicDBObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@ConditionalOnProperty(
        name = "initialize.buyer.data",
        havingValue = "true",
        matchIfMissing = true)
public class BuyerCollection {

    public BuyerCollection(@Autowired MongoTemplate mongoTemplate) {
        FetchCollectionFunction fetchCollectionFunction = new FetchCollectionFunction();
        JSONArray buyerJson = fetchCollectionFunction.apply("buyers");
        buyerJson.stream().forEach(b -> mongoTemplate.save(convert((JSONObject) b)));
    }

    private Buyer convert(JSONObject obj) {
        BasicDBObject dbObject = BasicDBObject.parse(obj.toJSONString());
        String id = dbObject.getString("_id");
        String name = dbObject.getString("name");
        String kimlikNo = dbObject.getString("kimlikNo");
        String surname = dbObject.getString("surname");
        LocalDateTime birthDate = LocalDateTime.ofInstant(((Date) dbObject.get("birthDate")).toInstant(), ZoneId.systemDefault());
        List<String> addresses = (List<String>) dbObject.get("addresses");
        Buyer buyer = new Buyer(id, name, surname, kimlikNo, birthDate, addresses);
        return buyer;
    }
}
