package com.uca.freelance.DataAccessLayer.entities;

import com.uca.freelance.DataAccessLayer.models.JobStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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

    @Column(name = "job_status")
    private JobStatus jobStatus;


    private Long authorIdToFindEntity;

    private Long freelancerIdToFindEntity;

    @ManyToOne
    @JoinColumn(name="freelancer_id", nullable = true)
    private User freelancer;

    @ManyToOne
    @JoinColumn(name="employer_id", nullable=false)
    private User employer;

    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(
                    name = "job_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "skill_id", referencedColumnName = "id"))
    private List<Skill> jobSkills;

    @OneToMany(mappedBy="job")
    private List<Application> applicationList;




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


}
