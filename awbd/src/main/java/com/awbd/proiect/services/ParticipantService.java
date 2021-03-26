package com.awbd.lab7.services;

import com.awbd.lab7.domain.Participant;

public interface ParticipantService {
    Participant findById(Long l);
    Participant save(Participant participant);
}
