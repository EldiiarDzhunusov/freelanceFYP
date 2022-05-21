package com.uca.freelance.DataAccessLayer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "author_id")
    private Long authorId;

    @ManyToMany
    @JoinTable(
            name = "application_employer",
            joinColumns = @JoinColumn(
                    name = "application_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "employer_id", referencedColumnName = "id"))
    private Collection<User> employerList;

    @ManyToMany
    @JoinTable(
            name = "application_freelancer",
            joinColumns = @JoinColumn(
                    name = "application_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "freelancer_id", referencedColumnName = "id"))
    private Collection<User> freelancerList;




}
