package com.uca.freelance.DataAccessLayer.Entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Freelancer profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Employer employer;
}
