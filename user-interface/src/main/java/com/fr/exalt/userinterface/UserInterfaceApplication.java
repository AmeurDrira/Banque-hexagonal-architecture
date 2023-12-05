package com.fr.exalt.userinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.fr.exalt.infra.entity")
@EnableJpaRepositories("com.fr.exalt.infra.repository")
@ComponentScan({"com.fr.exalt.core.port.primary","com.fr.exalt.userinterface"})

public class UserInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInterfaceApplication.class, args);
    }

}
