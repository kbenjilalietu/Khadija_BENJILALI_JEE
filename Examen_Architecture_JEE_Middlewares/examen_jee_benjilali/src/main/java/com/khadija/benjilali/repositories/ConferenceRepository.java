package com.khadija.benjilali.repositories;

import com.khadija.benjilali.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Participant, Long> {
}
