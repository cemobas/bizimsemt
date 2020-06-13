package com.krakus.bizimsemt.config;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.converter.StringToEnumConverter;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class BizimsemtConfig implements WebMvcConfigurer {

    @Autowired
    private BizimsemtProperties properties;

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

    public BizimsemtProperties getProperties() {
        return properties;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }

    @Bean
    public MessageSource validationMsgs() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    //  https://www.baeldung.com/spring-data-mongodb-transactions
//    @Bean
//    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
}
