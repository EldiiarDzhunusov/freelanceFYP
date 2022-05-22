package com.uca.freelance.BussinessLogicLayer.serviceImplementations;

import com.uca.freelance.BussinessLogicLayer.serviceInterfaces.SkillServiceInterface;
import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService implements SkillServiceInterface {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> findByKeyword(String keyword) {
        return skillRepository.findByKeyword(keyword);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Long deleteById(Long id) {
        skillRepository.deleteById(id);
        return id;
    }
}
