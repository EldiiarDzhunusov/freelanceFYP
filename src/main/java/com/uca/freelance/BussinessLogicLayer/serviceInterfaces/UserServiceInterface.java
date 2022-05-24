package com.uca.freelance.BussinessLogicLayer.serviceInterfaces;

import com.uca.freelance.DataAccessLayer.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface{
    List<User> findFreelancersByKeyword(String keyword);

    List<User> findByKeyword(String keyword);



    User save(User user);

    List<User> findAll();

    List<User> findAllFreelancers();

    List<User> findAllEmployers();

    Optional<User> findById(Long id);

    User getById(Long id);

    Long deleteById(Long id);

    User findByEmail(String email);


}
