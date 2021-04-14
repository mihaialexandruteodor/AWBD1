package com.awbd.model;

import javax.persistence.*;

@Entity
@Table(name = "info")
public class Info {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long infoid;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projid")
    private Project infoProject;

    public long getInfoid() { return infoid; }
    public void setInfoid(long infoid) { this.infoid = infoid; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Project getInfoProject() { return infoProject; }
    public void setInfoProject(Project infoProject) { this.infoProject = infoProject; }
}
