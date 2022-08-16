package swp.charite.demo;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public FhirContext fhirContext() {
        FhirContext ctx = FhirContext.forR4();
        // Set how long to try and establish the initial TCP connection (in ms)
        ctx.getRestfulClientFactory().setConnectTimeout(20 * 1000);

        // Set how long to block for individual read/write operations (in ms)
        ctx.getRestfulClientFactory().setSocketTimeout(20 * 1000);
        return ctx;
    }

    @Bean
    public IParser parse() {
        return fhirContext().newJsonParser();
    }

    @Bean
    public IGenericClient client() {
        return fhirContext().newRestfulGenericClient("http://hapi.fhir.org/baseR4/");
    }
}
