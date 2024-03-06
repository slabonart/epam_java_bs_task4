package pl.slabonart.epam_java_bs_task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slabonart.epam_java_bs_task4.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String username);
}
