package com.awbd.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Employee> employeeList;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return teamName;
    }
    public void setName(String name) {
        this.teamName = name;
    }

}
