package com.uca.freelance.DataAccessLayer.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;


    private double earnedMoney;

    @ManyToMany(mappedBy = "freelancerSet")
    Set<Skill> skillSet;


}
