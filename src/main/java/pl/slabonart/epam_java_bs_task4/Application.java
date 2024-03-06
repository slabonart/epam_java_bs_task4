package pl.slabonart.jabs_4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.slabonart.jabs_4.model.UserEntity;
import pl.slabonart.jabs_4.repository.UserRepository;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printUserEntities(UserRepository repository) {
        return args -> {
            List<UserEntity> users = repository.findAll();
            log.info("User entities: ");
            log.info("--------------------------");
            log.info(users.toString());
            log.info("");
        };
    }


}
