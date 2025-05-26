package org.cajero.automatico.service;

import jakarta.transaction.Transactional;
import org.cajero.automatico.model.Account;
import org.cajero.automatico.model.AccountCard;
import org.cajero.automatico.repository.AccountCardRepository;
import org.cajero.automatico.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountCardImpl implements IServiceAccountCard {
    @Autowired
    private AccountCardRepository accountCardRepository;



    @Override
    public String login(Integer numberCard) {
        if(accountCardRepository.existsByCard_NumberCardAndCard_Activa(numberCard,"S"))
            return "Ingreso exitoso";
        return "Ingreso no exitoso";
    }

    @Override
    @Transactional
    public String extraction(Integer numberCard, Integer numberAccount, Double amount) {
        AccountCard accountCard = this.accountCardRepository.findByCard_NumberCardAndAccount_NumberAccount(numberCard,numberAccount).get();
        Account account = accountCard.getAccount();
        Double amountAccount = account.getBalance();
        if(amountAccount >= amount){
            amountAccount-= amount;
            account.setBalance(amountAccount);
            accountCard.setAccount(account);
            this.accountCardRepository.save(accountCard);
            return "Retire su dinero";
        }
        return "Error: Saldo insuficiente";

    }

    @Override
    @Transactional
    public String deposit(Integer numberCard, String cbu, Double amount) {
      AccountCard accountCard = this.accountCardRepository.findByCard_NumberCard(numberCard).get();
        Account account = accountCard.getAccount();
        Double amountAccount = account.getBalance();
        if(amountAccount >= amount){
            amountAccount-= amount;
            account.setBalance(amountAccount);
            accountCard.setAccount(account);
            AccountCard accountCarddeposit= this.accountCardRepository.findByAccount_Cbu(cbu);
            Double amountAccountDeposit = accountCarddeposit.getAccount().getBalance() + amount;
            accountCarddeposit.getAccount().setBalance(amountAccountDeposit);
            this.accountCardRepository.save(accountCard);
            this.accountCardRepository.save(accountCarddeposit);
            return "Depósito exitoso";
        }
        return "Error en el deposito!";
    }

    @Override
    public Double balance(Integer numberCard, Integer numberAccount) {
       Double balance = this.accountCardRepository.findBalanceByCardAndAccount(numberCard,numberAccount);
       return  balance;
    }
}
