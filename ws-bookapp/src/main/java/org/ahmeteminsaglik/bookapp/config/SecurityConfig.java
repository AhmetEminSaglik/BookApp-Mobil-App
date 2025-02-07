package org.ahmeteminsaglik.bookapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private final ApiKeyFilter apiKeyFilter;

    @Autowired
    public SecurityConfig(ApiKeyFilter apiKeyFilter) {
        this.apiKeyFilter = apiKeyFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer.anyRequest().permitAll()
        );
        http.csrf(e -> e.disable()
                .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class));
        return http.build();
    }
}
