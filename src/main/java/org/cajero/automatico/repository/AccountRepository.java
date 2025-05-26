package org.cajero.automatico.repository;

import org.cajero.automatico.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByCbu(String cbu);

}
