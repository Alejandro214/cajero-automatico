package org.cajero.automatico.repository;

import org.cajero.automatico.model.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card,Long> {
    boolean existsByNumberCardAndActiva(Integer numberCard, String activa);

}
