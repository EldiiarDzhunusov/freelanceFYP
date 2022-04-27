package com.uca.freelance.DataAccessLayer.Entities;

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
    Set<Freelancer> freelancerSet;
}
