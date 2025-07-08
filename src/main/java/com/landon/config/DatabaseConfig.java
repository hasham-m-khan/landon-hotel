package com.landon.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.landon.repository")
@EnableTransactionManagement
public class DatabaseConfig {
}
