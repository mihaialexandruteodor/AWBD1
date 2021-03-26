package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Participant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Slf4j
public class FinderParticipantTest {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    public void findByName() {
        List<Participant> participants = participantRepository.findByLastNameLike("%no%");
        assertFalse(participants.isEmpty());
        log.info("findByLastNameLike ...");
        participants.forEach(participant -> log.info(participant.getLastName()));
    }

    @Test
    public void findByIds() {
        List<Participant> participants = participantRepository.findByIdIn(Arrays.asList(1L,3L));
        assertFalse(participants.isEmpty());
        log.info("findByIds ...");
        participants.forEach(participant -> log.info(participant.getLastName()));
    }

}
