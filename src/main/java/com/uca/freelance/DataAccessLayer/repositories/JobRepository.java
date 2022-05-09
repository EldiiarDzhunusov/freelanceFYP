package com.uca.freelance.DataAccessLayer.repositories;

import com.uca.freelance.DataAccessLayer.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(value = "SELECT * FROM user where user.is_started != 1 ", nativeQuery = true)
    List<Job> findAllUnstartedJobs();

    @Query(value = "select * from jobs j where j.description like %:keyword% or j.name like %:keyword%",nativeQuery = true)
    List<Job> findByKeyword(@Param("keyword") String keyword);
}
