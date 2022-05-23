package com.uca.freelance.DataAccessLayer.entities;

import com.uca.freelance.DataAccessLayer.models.Role;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "users_skills",
            joinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "skill_id", referencedColumnName = "id"))
    private Collection<Skill> userSkills;

    @OneToMany(mappedBy="employer")
    private List<Job> jobsOwned;

    @OneToMany(mappedBy="freelancer")
    private List<Job> jobsDoing;

    @OneToMany(mappedBy="user")
    private Collection<Application> applicationsList;

    private Role role;

    private String description;

    public User() {
    }

    public String getSkillsToString(){
        Collection<Skill> userSkills = this.userSkills;
        String ans = "";
        boolean isFirst = true;
        for(Skill skill : userSkills){
            if(isFirst){
                ans+=skill.getName();
                isFirst =false;
            }else{
                ans+=", "+skill.getName();
            }
        }
        return ans;
    }

    public String getFullNameAbbreviation() {
        return Character.toUpperCase(this.lastName.charAt(0))+". "+ this.firstName ;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String password, String firstName, String lastName, Role role, String description) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.description = description;
    }
}
