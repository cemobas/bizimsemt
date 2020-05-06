package com.krakus.bizimsemt;

import com.krakus.bizimsemt.config.BizimsemtConfig;
import com.krakus.bizimsemt.config.BizimsemtProperties;
import org.bson.Document;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@Import({BizimsemtConfig.class, BizimsemtProperties.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
public class MongodbHealthTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @DisplayName("given db" +
            "when we ping db" +
            "then gives a signal")
    @Test
    public void dbPrimaryIsOk() {
        try {
            Document ping = mongoTemplate.getDb().runCommand(new Document("ping", 1));
            Document serverStatus = mongoTemplate.getDb().runCommand(new Document("serverStatus", 1));
            Map connections = (Map) serverStatus.get("connections");
            Integer currentConnections = (Integer) connections.get("current");

			assertThat(ping, is(notNullValue()));
            assertThat(ping.getDouble("ok"), is(1.0));
			assertThat(currentConnections, Matchers.greaterThan(0));
            assertThat(mongoTemplate.getDb().getCollection("buyers").countDocuments(),  Matchers.greaterThan(0L));
            assertThat(mongoTemplate.getDb().getCollection("sellers").countDocuments(),  Matchers.greaterThan(0L));
            assertThat(mongoTemplate.getDb().getCollection("orders").countDocuments(),  Matchers.greaterThan(0L));
        } catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }
}
