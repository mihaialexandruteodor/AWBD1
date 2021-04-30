package com.awbd.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private long organisationid;

    @Column(name = "name")
    private String organisationName;

    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    private Set<Client> clientList;

    public long getOrganisationid() { return organisationid; }
    public void setOrganisationid(long organisationid) { this.organisationid = organisationid; }
    public String getOrganisationName() { return organisationName; }
    public void setOrganisationName(String organisationName) { this.organisationName = organisationName; }
    public Set<Client> getClientList() { return clientList; }
    public void setClientList(Set<Client> clientList) { this.clientList = clientList; }
}
