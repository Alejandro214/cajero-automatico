package org.cajero.automatico.service.inter;

import org.cajero.automatico.model.Account;

import java.util.Optional;

public interface IServiceAccount {
    Optional<Account> findByCbu(String cbu);

    void save(Account account);
}
