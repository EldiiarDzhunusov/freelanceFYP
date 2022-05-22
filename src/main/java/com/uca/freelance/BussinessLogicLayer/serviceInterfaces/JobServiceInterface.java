package com.uca.freelance.BussinessLogicLayer.serviceInterfaces;

import com.uca.freelance.DataAccessLayer.entities.Job;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JobServiceInterface {
    Optional<Job> findById(Long id);

    Job getById(Long id);

    List<Job> findAll();


    Long deleteById(Long id);

    Job save(Job job);

    List<Job> findByKeyword(String keyword);



}
