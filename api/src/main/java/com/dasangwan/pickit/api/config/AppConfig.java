package com.dasangwan.pickit.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.dasangwan.pickit.api.repository")
public class AppConfig {
}
