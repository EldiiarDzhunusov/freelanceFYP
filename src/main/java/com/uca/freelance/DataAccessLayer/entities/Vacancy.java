package com.uca.freelance.DataAccessLayer.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date datePosted;
    private BigDecimal price;
}
