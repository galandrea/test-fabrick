package it.gala.test.fabrick.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api.fabrick")
public class ApiFabrickConfiguration {

    private String baseUrl;
    private String authSchema;
    private String apiKey;
    private String accountId;
    private String clientTimeZone;

}