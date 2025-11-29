package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByMail(String mail);
}
