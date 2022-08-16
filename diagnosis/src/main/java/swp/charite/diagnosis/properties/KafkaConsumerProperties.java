package swp.charite.diagnosis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("kafka")
@Configuration
@Getter
@Setter
public class KafkaConsumerProperties {
    private String bootstrapAddress;
    private String groupId;
}
