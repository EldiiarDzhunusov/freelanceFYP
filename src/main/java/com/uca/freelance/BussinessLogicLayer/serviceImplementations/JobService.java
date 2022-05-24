package com.uca.freelance.BussinessLogicLayer.serviceImplementations;

import com.uca.freelance.BussinessLogicLayer.serviceInterfaces.JobServiceInterface;
import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService implements JobServiceInterface {

    @Autowired
    JobRepository jobRepository;

    @Override
    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public Job getById(Long id) {
        return jobRepository.getById(id);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findAllUnstartedJobs() {
        return jobRepository.findAllUnstartedJobs();
    }


    @Override
    public Long deleteById(Long id) {
        jobRepository.deleteById(id);
        return id;
    }

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> findByKeyword(String keyword) {
        return jobRepository.findByKeyword(keyword);
    }

    @Override
    public List<Job> findByKeywordUnstartedJobs(String keyword) {
        return jobRepository.findByKeywordUnstartedJobs(keyword);
    }
}
