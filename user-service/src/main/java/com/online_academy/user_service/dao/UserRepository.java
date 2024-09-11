package com.online_academy.user_service.dao;
import com.online_academy.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
