package com.awbd.repositories;

import com.awbd.model.Project;
import com.awbd.repository.ProjectRepository;
import com.awbd.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;


    @Test
    public void findProject()
    {
        long projectId = projectRepository.getNumberOfProjects() + 1;
        Project project = new Project();
        project.setProjName("starwars");
        projectRepository.save(project);
        Optional<Project> optional = projectRepository.findById(projectId);
        Project projFound = null;
        if (optional.isPresent()) {
            projFound = optional.get();
        } else {
            fail(" Project not found for id :: " + projectId);
        }
        assertTrue(projFound.getProjName().equals("starwars"));
    }
}
