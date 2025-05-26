package org.cajero.automatico.service.impl;

import org.cajero.automatico.model.Account;
import org.cajero.automatico.repository.AccountRepository;
import org.cajero.automatico.service.inter.IServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ServiceAccountImpl implements IServiceAccount {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Optional<Account> findByCbu(String cbu) {
        return this.accountRepository.findByCbu(cbu);
    }

    @Override
    public void save(Account account) {
        this.accountRepository.save(account);
    }
}
