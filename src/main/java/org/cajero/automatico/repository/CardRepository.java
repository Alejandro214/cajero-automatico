package org.cajero.automatico.repository;

import org.cajero.automatico.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card,Long> {
}
