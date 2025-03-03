package by.mrfakek.account.subscription.api.repositories;

import by.mrfakek.account.subscription.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
