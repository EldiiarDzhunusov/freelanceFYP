package com.uca.freelance.BussinessLogicLayer.serviceImplementations;

import com.uca.freelance.BussinessLogicLayer.serviceInterfaces.SkillServiceInterface;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService implements SkillServiceInterface {

    @Autowired
    private SkillRepository skillRepository;
}
