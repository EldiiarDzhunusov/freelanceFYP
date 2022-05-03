package com.uca.freelance.DataAccessLayer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;

    public UserDto() {
    }

    public UserDto(String email, String password, String first_name, String last_name) {
        super();
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
