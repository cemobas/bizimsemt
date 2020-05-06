package com.krakus.bizimsemt.data;

import com.krakus.bizimsemt.aspect.LoggingAspect;
import com.krakus.bizimsemt.function.FetchJSONFunction;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BizimTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizimTemplate.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private FetchJSONFunction fetchJSONFunction;
    private Map<String, Boolean> initMap = new HashMap<>();

    public void remove(Query query, Class entityClass) {
        mongoTemplate.remove(query, entityClass);
    }

    public void init(String collectionName) {
        if(!initMap.containsKey(collectionName)) {
            JSONArray jsonArray = fetchJSONFunction.apply(collectionName);
            jsonArray.stream().forEach(jsonObj -> {
                DBObject dbObject = (DBObject) JSON.parse(jsonObj.toString());
                mongoTemplate.save(dbObject, collectionName);
            });
            initMap.put(collectionName, Boolean.TRUE);
            LOGGER.info(collectionName + " collection initialized.");
        } else {
            LOGGER.info(collectionName + " collection exists.");
        }
    }
}
