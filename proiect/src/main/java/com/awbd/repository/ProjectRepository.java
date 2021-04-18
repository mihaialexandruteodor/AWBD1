package com.awbd.repository;

import com.awbd.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT COUNT(*) FROM Project")
    long getNumberOfProjects();
}
