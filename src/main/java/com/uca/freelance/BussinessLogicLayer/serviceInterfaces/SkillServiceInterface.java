package com.uca.freelance.BussinessLogicLayer.serviceInterfaces;

import com.uca.freelance.DataAccessLayer.entities.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillServiceInterface {

    List<Skill> findByKeyword(String keyword);

    List<Skill> findAll();

    Optional<Skill> findById(Long id);

    Skill getById(Long id);

    Skill save(Skill skill);

    Long deleteById(Long id);
}
