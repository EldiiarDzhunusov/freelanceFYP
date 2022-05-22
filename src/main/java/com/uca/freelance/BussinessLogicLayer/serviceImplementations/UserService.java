package com.uca.freelance.BussinessLogicLayer.serviceImplementations;

import com.uca.freelance.BussinessLogicLayer.serviceInterfaces.UserServiceInterface;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {


    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findByKeyword(String keyword) {
        return userRepository.findByKeyword(keyword);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
