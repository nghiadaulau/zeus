package vn.tdtu.edu.sneaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"vn.tdtu.edu.commons.*","vn.tdtu.edu.sneaker.*"})
@EnableJpaRepositories(value = "vn.tdtu.edu.commons.repository")
@EntityScan(value = "vn.tdtu.edu.commons.model")
public class SneakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SneakerApplication.class, args);
    }

}
