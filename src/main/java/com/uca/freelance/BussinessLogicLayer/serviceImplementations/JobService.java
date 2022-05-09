package com.uca.freelance.BussinessLogicLayer.serviceImplementations;

import com.uca.freelance.BussinessLogicLayer.serviceInterfaces.JobServiceInterface;
import com.uca.freelance.DataAccessLayer.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService implements JobServiceInterface {

    @Autowired
    JobRepository jobRepository;
}
