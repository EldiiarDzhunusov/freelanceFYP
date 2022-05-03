package com.uca.freelance.DataAccessLayer.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String first_name;
    private String last_name;

    private Role role;

    public User() {
    }

    public User(String email, String password, String first_name, String last_name, Role role) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
    }
}
