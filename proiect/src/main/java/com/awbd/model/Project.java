package com.awbd.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private long projid;

    @Column(name = "proj_name")
    private String projName;

    @Column(name = "budget")
    private String budget;

    @ManyToMany(mappedBy = "projects")
    private Set<Team> teams;

    @OneToOne(mappedBy = "clientProject")
    private Client client;

    @OneToOne(mappedBy = "infoProject")
    private Info info;

    public long getProjid() {
        return projid;
    }
    public void setProjid(long projid) {
        this.projid = projid;
    }
    public String getProjName() {
        return projName;
    }
    public void setProjName(String projName) {
        this.projName = projName;
    }
    public String getBudget() {
        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }
    public Set<Team> getTeams() {
        return teams;
    }
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    public void addTeam(Team team)
    {
        this.teams.add(team);
    }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Info getInfo() { return info; }
    public void setInfo(Info info) { this.info = info; }
    public void removeTeam(Team team) { this.teams.remove(team); }
    public void removeAllTeams()
    {
        for(Team team : teams)
            removeTeam(team);
    }

    
}
