package com.uca.freelance.DataAccessLayer.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;


    private double earnedMoney;

    @ManyToMany(mappedBy = "freelancerSet")
    Set<Skill> skillSet;


}
