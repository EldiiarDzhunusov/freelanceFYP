package com.uca.freelance.DataAccessLayer.repositories;

import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(value = "select * from skills s where s.name like %:keyword%",nativeQuery = true)
    List<Skill> findByKeyword(@Param("keyword") String keyword);
}
