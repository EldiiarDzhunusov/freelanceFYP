package com.uca.freelance.DataAccessLayer.repositories;

import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "select * from users u where u.first_name like %:keyword% or u.last_name like %:keyword% or u.description like %:keyword% ",nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from users u where u.role=1 and (u.first_name like %:keyword% or  u.last_name like %:keyword% or  u.description like %:keyword%) ",nativeQuery = true)
    List<User> findFreelancersByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from users u where u.role=1",nativeQuery = true)
    List<User> findAllFreelancers();

    @Query(value = "select * from users u where u.role=2",nativeQuery = true)
    List<User> findAllEmployers();
}
