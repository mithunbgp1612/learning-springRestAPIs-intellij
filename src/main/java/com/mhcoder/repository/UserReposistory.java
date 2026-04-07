package com.mhcoder.repository;

import com.mhcoder.dto.UserLogin;
import com.mhcoder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposistory extends JpaRepository<User, Long> {
//    User findByemailAndPassword(String email,String password);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
