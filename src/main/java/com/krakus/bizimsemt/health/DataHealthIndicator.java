package com.krakus.bizimsemt.health;

import com.mongodb.MongoSocketWriteException;
import com.mongodb.MongoTimeoutException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataHealthIndicator implements HealthIndicator {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Health health() {
        try {
            Document ping = mongoTemplate.getDb().runCommand(new Document("ping", 1));
            Document serverStatus = mongoTemplate.getDb().runCommand(new Document("serverStatus", 1));
            Map connections = (Map) serverStatus.get("connections");
            Integer currentConnections = (Integer) connections.get("current");
            return Health.up()
                    .withDetail("ping", ping.get("ok"))
                    .withDetail("currentConnections", currentConnections)
                    .withDetail("buyersCount", mongoTemplate.getDb().getCollection("buyers").countDocuments())
                    .withDetail("sellersCount", mongoTemplate.getDb().getCollection("sellers").countDocuments())
                    .withDetail("ordersCount", mongoTemplate.getDb().getCollection("orders").countDocuments()).build();
        } catch (MongoTimeoutException | MongoSocketWriteException e) {
            return Health.down()
                    .withDetail("code", HttpStatus.SERVICE_UNAVAILABLE.value())
                    .withDetail("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                    .withDetail("detailMessage", e.getMessage()).build();
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }
}
