package io.sandes.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.originPatterns}")
    private String corsOrigin = "";

    @Bean
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOrigin.split(",");

        registry
                .addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
