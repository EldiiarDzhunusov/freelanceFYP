package com.uca.freelance.BussinessLogicLayer.serviceInterfaces;

import com.uca.freelance.DataAccessLayer.dto.UserDto;
import com.uca.freelance.DataAccessLayer.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    User save(UserDto userDto);
    List<User> findAll();
}
