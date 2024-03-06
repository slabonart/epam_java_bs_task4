package pl.slabonart.jabs_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slabonart.jabs_4.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String username);
}
