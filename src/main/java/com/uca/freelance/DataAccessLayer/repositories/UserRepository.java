package com.uca.freelance.DataAccessLayer.repositories;

import com.uca.freelance.DataAccessLayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
