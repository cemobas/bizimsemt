package com.krakus.bizimsemt;

import com.krakus.bizimsemt.config.BizimsemtConfig;
import com.krakus.bizimsemt.config.BizimsemtProperties;
import com.krakus.bizimsemt.health.DataHealthIndicator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Import({BizimsemtConfig.class, BizimsemtProperties.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
public class MongodbHealthCheck {

    @Autowired
    DataHealthIndicator dataHealthIndicator;

    @DisplayName("given db " +
            "when we ping db " +
            "then gives a signal")
    @Test
    public void dbPrimaryIsOk() {
        try {
            Health health = dataHealthIndicator.health();
            if (Status.UP.equals(health.getStatus())) {
                assertMongoHealth(health);
            } else {
                fail(health.getDetails().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    private void assertMongoHealth(Health health) {
        Double ping = (Double) health.getDetails().get("ping");
        Integer currentConnections = (Integer) health.getDetails().get("currentConnections");
        Long buyersCount = (Long) health.getDetails().get("buyersCount");
        Long sellersCount = (Long) health.getDetails().get("sellersCount");
        Long ordersCount = (Long) health.getDetails().get("ordersCount");

        assertThat(ping, is(1.0));
        assertThat(currentConnections, Matchers.greaterThan(0));
        assertThat(buyersCount, Matchers.greaterThan(0L));
        assertThat(sellersCount, Matchers.greaterThan(0L));
        assertThat(ordersCount, Matchers.greaterThan(0L));
    }
}
