package com.awbd.proiect.services;

import com.awbd.proiect.domain.Participant;

public interface ParticipantService {
    Participant findById(Long l);
    Participant save(Participant participant);
}
