package com.uca.freelance.BussinessLogicLayer.serviceInterfaces;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.ApplicationService;
import com.uca.freelance.DataAccessLayer.entities.Application;

import java.util.List;

public interface ApplicationServiceInterface {

    List<Application> findAll();

    Application save(Application application);

    Long deleteById(Long id);

    Application getById(Long id);

}
