package swp.charite.feedbacks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class FeedbacksApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedbacksApplication.class, args);
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

}
