package com.uca.freelance.DataAccessLayer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Skill {
    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(mappedBy = "skillSet")
    Set<User> freelancerSet;
}
