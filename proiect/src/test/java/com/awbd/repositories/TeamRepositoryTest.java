package com.awbd.repositories;

import com.awbd.model.Employee;
import com.awbd.model.Team;
import com.awbd.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void addTeam(){
        Team team = new Team();
        Set<Employee> employees = new HashSet<>();
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        employees.add(e1);
        employees.add(e2);
        team.setTeamName("Justice League");
        team.setEmployeeList(employees);
        teamRepository.save(team);
    }
}
