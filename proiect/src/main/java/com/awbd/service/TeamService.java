package com.awbd.service;

import com.awbd.model.Employee;
import com.awbd.model.Team;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    void saveTeam(Team team);
    Team getTeamById(long id);
    void deleteTeamById(long id);
    void addEmployeeToTeam(Team team, Employee employee) ;
    void removeEmployeeFromTeam(Team team, Employee employee) ;
    Page<Team> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
