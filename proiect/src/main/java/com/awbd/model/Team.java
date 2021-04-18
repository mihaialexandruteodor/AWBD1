package com.awbd.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private long teamid;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Employee> employeeList;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Team_Project",
            joinColumns = { @JoinColumn(name = "teamid") },
            inverseJoinColumns = { @JoinColumn(name = "projid") }
    )
    private Set<Project> projects;

    public void addEmployee(Employee employee)
    {
        this.employeeList.add(employee);
    }

    public void removeEmployee(Employee employee)
    {
        this.employeeList.remove(employee);
        employee.setTeam(null);
    }

    public void removeAllEmployees()
    {
        for(Employee employee : employeeList)
            removeEmployee(employee);
    }

    public void addProject(Project project)
    {
        this.projects.add(project);
    }

    public void removeProject(Project project)
    {
        this.projects.remove(project);
    }

    public void removeAllProjects()
    {
        for(Project project : projects)
            removeProject(project);
    }

    public void setId(long id) {
        this.teamid = id;
    }
    public long getId() { return teamid; }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String name) {
        this.teamName = name;
    }
    public Set<Employee> getEmployeeList() { return employeeList; }
    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    public Set<Project> getProjects() { return projects; }
    public void setProjects(Set<Project> projects) { this.projects = projects; }

}
