package com.uca.freelance.DataAccessLayer.entities;

import com.uca.freelance.DataAccessLayer.models.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "applications")
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @Column(name = "proposed_price")
    private double proposedPrice;

    @Column(name = "application_status")
    private ApplicationStatus applicationStatus;

    @Column(name = "job_take_id")
    private Long jobTakeId;


    @ManyToOne
    @JoinColumn(name="job_id", nullable=false)
    private Job job;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;






}
