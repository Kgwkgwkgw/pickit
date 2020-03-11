package com.dasangwan.pickit.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackages = "com.dasangwan.pickit.api.repository")
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 내부적으로 raondom salt를 사용함
        // 60자의 해시 문자열을 생성한다.
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
