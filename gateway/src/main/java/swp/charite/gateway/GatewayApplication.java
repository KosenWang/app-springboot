package swp.charite.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * Method to route incoming HTTP-Requests to other services (extern and intern)
     * @param builder RouteLocatorBuilder
     * @return redirects HTTP-Request to other service
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("simple", p -> p.path("/demo/**").uri("lb://demo"))
                .route("patient", p -> p.path("/patient/**").uri("lb://patient-service"))
                .route("diagnosis", p -> p.path("/diagnosis/**").uri("lb://diagnosis-service"))
                .route("feedback", p -> p.path("/feedback/**").uri("lb://feedback-service"))
                .route("doctor", p -> p.path("/doctor/**").uri("lb://doctor-service"))
                .build();
    }

}
