package com.krakus.bizimsemt.config;

import com.krakus.bizimsemt.aspect.Loggable;
import com.krakus.bizimsemt.converter.StringToEnumConverter;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.domain.Seller;
import com.krakus.bizimsemt.interceptors.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // async is enabled by default with Spring Boot
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(mvcTaskExecutor());
    }

    @Bean
    public AsyncTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("bizimsemt-thread-");
        return threadPoolTaskExecutor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
    }

    //  https://www.baeldung.com/spring-data-mongodb-transactions
//    @Bean
//    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
}
