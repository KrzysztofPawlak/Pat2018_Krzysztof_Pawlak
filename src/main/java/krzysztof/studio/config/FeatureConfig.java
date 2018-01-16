package krzysztof.studio.config;

import krzysztof.studio.car.CarOperations;
import krzysztof.studio.car.CarService;
import krzysztof.studio.car.CarServiceH2;
import krzysztof.studio.customer.CustomerOperations;
import krzysztof.studio.customer.CustomerService;
import krzysztof.studio.customer.CustomerServiceH2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureConfig {
    @Bean
    @ConditionalOnProperty(name = "H2_STORAGE_ENABLED", havingValue="false")
    public CarOperations carOldService(){
        return new CarService();
    }

    @Bean
    @ConditionalOnProperty(name = "H2_STORAGE_ENABLED", havingValue="true")
    public CarOperations CarH2Service(){
        return new CarServiceH2();
    }

    @Bean
    @ConditionalOnProperty(name = "H2_STORAGE_ENABLED", havingValue="false")
    public CustomerOperations CustomerOldService(){
        return new CustomerService();
    }

    @Bean
    @ConditionalOnProperty(name = "H2_STORAGE_ENABLED", havingValue="true")
    public CustomerOperations CustomerH2Service(){
        return new CustomerServiceH2();
    }
}