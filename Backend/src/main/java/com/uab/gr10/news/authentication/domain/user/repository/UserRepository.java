package com.uab.gr10.news.authentication.domain.user.repository;

import com.uab.gr10.news.authentication.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Boolean existsByEmailIgnoreCase(String email);
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);
}
