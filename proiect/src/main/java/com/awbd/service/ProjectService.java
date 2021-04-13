package com.awbd.service;

import com.awbd.model.Project;
import com.awbd.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<Project> getAllProjects();
    void saveProject(Project project);
    Project getProjectById(long id);
    void deleteProjectById(long id);
    void addTeamToProject(Project project, Team team) ;
    void removeTeamFromProject(Project project, Team team) ;
    Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
