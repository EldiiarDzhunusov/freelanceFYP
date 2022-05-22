package com.uca.freelance.DataAccessLayer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private  Double price;

    private String description;

    @Column(name = "is_started")
    private boolean isStarted;


    private Long authorId;

    private Long freelancerId;

    @ManyToMany(mappedBy = "userJobs")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(
                    name = "job_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "skill_id", referencedColumnName = "id"))
    private Collection<Skill> jobSkills;

    @OneToMany(mappedBy="job")
    private Collection<Application> applicationList;




    public String getSkillsToString(){
        Collection<Skill> jobSkills = this.jobSkills;
        String ans = "";
        boolean isFirst = true;
        for(Skill skill : jobSkills){
            if(isFirst){
                ans+=skill.getName();
                isFirst =false;
            }else{
                ans+=", "+skill.getName();
            }
        }
        return ans;
    }
    public Job() {
    }


    public Job(String name, Double price, String description, boolean isStarted) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isStarted = isStarted;
    }
}
