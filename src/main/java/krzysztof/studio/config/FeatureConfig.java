package krzysztof.studio.config;

import krzysztof.studio.car.CarOperations;
import krzysztof.studio.car.CarService;
import krzysztof.studio.car.CarServiceH2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureConfig {
    @Bean
    @ConditionalOnProperty(name = "H2_STORAGE_ENABLED", havingValue="false")
    public CarOperations oldService(){
        return new CarService();
    }

    @Bean
    @ConditionalOnProperty(name = "H2_STORAGE_ENABLED", havingValue="true")
    public CarOperations h2Service(){
        return new CarServiceH2();
    }
}