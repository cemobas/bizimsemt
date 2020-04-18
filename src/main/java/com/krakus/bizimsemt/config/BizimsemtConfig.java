package com.krakus.bizimsemt.config;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.domain.Seller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class BizimsemtConfig {

    @Bean
    @Loggable
    public RepositoryRestConfigurer repositoryRestConfigurer()
    {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(Buyer.class);
            config.exposeIdsFor(Seller.class);
            config.exposeIdsFor(Order.class);
        });
    }
}
