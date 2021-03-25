package com.awbd.lab5.repositories;

import com.awbd.lab5.domain.Participant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class ParticipantRepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    public void addParticipant() {
        Participant participant = new Participant();
        participant.setFirstName("Jhon");
        participant.setLastName("Adam");
        participantRepository.save(participant);
    }
}
