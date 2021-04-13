package com.awbd.service;

import com.awbd.model.Project;
import com.awbd.model.Team;
import com.awbd.repository.ProjectRepository;
import com.awbd.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void saveProject(Project project) {
        this.projectRepository.save(project);
    }

    @Override
    public Project getProjectById(long id) {
        Optional<Project> optional = projectRepository.findById(id);
        Project project = null;
        if (optional.isPresent()) {
            project = optional.get();
        } else {
            throw new RuntimeException(" Project not found for id :: " + id);
        }
        return project;
    }

    @Override
    public void deleteProjectById(long id) {
        Project project = getProjectById(id);
        project.removeAllTeams();
        List<Team> teams = teamRepository.findAll();
        for(Team t: teams)
            if (t.getProjects().contains(project) )
                t.removeProject(project);
        this.projectRepository.deleteById(id);
    }

    @Override
    public void addTeamToProject(Project project, Team team) {
        team.addProject(project);
        project.addTeam(team);
        this.teamRepository.save(team);
        this.projectRepository.save(project);
    }

    @Override
    public void removeTeamFromProject(Project project, Team team) {
        team.removeProject(project);
        project.removeTeam(team);
        this.teamRepository.save(team);
        this.projectRepository.save(project);
    }

    @Override
    public Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.projectRepository.findAll(pageable);
    }
}
