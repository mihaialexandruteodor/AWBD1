package com.awbd.lab7.services;

import com.awbd.lab7.domain.Participant;
import com.awbd.lab7.exceptions.ResourceNotFoundException;
import com.awbd.lab7.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    ParticipantRepository participantRepository;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Participant findById(Long l) {
        Optional<Participant> participantOptional = participantRepository.findById(l);
        if (!participantOptional.isPresent()) {
            //throw new RuntimeException("Participant not found!");
            throw new ResourceNotFoundException("participant " + l + " not found");
        }
        return participantOptional.get();
    }

    @Override
    public Participant save(Participant participant) {
        Participant savedParticipant = participantRepository.save(participant);
        return savedParticipant;
    }
}
