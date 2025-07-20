package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "application",
        "domain",
        "infrastructure"
})
@EnableJpaRepositories(basePackages = "infrastructure.persistence.repository")
@EntityScan(basePackages = "domain.models")

public class RunexusApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunexusApplication.class, args);
    }
}
