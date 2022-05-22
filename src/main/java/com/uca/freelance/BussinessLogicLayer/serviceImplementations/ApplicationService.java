package com.uca.freelance.BussinessLogicLayer.serviceImplementations;

import com.uca.freelance.BussinessLogicLayer.serviceInterfaces.ApplicationServiceInterface;
import com.uca.freelance.DataAccessLayer.entities.Application;
import com.uca.freelance.DataAccessLayer.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements ApplicationServiceInterface {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Long deleteById(Long id) {
        applicationRepository.deleteById(id);
        return id;
    }

    @Override
    public Application getById(Long id) {
        return applicationRepository.getById(id);
    }
}
