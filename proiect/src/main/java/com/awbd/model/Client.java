package com.awbd.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private long clientid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projid")
    private Project clientProject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organisationid")
    private Organisation organisation;

    public long getClientid() { return clientid; }
    public void setClientid(long clientid) { this.clientid = clientid; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public Project getClientProject() { return clientProject; }
    public void setClientProject(Project clientProject) { this.clientProject = clientProject; }
    public Organisation getOrganisation() { return organisation; }
    public void setOrganisation(Organisation organisation) { this.organisation = organisation; }
}
