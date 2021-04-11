package com.awbd.repository;

import com.awbd.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    //@Query("")
    //void addMemberToTeam(@Param("id")long employeeID, @Param("teamID") long teamID);
}
