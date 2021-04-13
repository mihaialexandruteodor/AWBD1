package com.awbd.service;

import com.awbd.model.Employee;
import com.awbd.model.Team;
import com.awbd.repository.EmployeeRepository;
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
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Team> getAllTeams() {return teamRepository.findAll();}

    @Override
    public void saveTeam(Team team) {this.teamRepository.save(team);}

    @Override
    public Team getTeamById(long id) {
        Optional<Team> optional = teamRepository.findById(id);
        Team team = null;
        if (optional.isPresent()) {
            team = optional.get();
        } else {
            throw new RuntimeException(" Team not found for id :: " + id);
        }
        return team;
    }

    @Override
    public void deleteTeamById(long id) {
        this.teamRepository.deleteById(id);
    }

    @Override
    public void addEmployeeToTeam(Team team, Employee employee) {
        team.addEmployee(employee);
        employee.setTeam(team);
        this.teamRepository.save(team);
        this.employeeRepository.save(employee);
    }

    @Override
    public void removeEmployeeFromTeam(Team team, Employee employee) {
        team.removeEmployee(employee);
        employee.setTeam(null);
        this.teamRepository.save(team);
        this.employeeRepository.save(employee);
    }

    @Override
    public Page<Team> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.teamRepository.findAll(pageable);
    }


}
