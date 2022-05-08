package com.uca.freelance.DataAccessLayer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "userSkills")
    private Collection<User> userList;

    @ManyToMany(mappedBy = "jobSkills")
    private Collection<Job> jobList;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }
}
