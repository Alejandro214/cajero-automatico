package org.cajero.automatico.service;

import jakarta.transaction.Transactional;
import org.cajero.automatico.model.Account;
import org.cajero.automatico.model.AccountCard;
import org.cajero.automatico.repository.AccountCardRepository;
import org.cajero.automatico.repository.AccountRepository;
import org.cajero.automatico.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountCardImpl implements IServiceAccountCard {
    @Autowired
    private AccountCardRepository accountCardRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String login(Integer numberCard) {
        if(cardRepository.existsByNumberCardAndActiva(numberCard,"S"))
            return "Ingreso exitoso";
        return "Ingreso no exitoso";
    }

    @Override
    @Transactional
    public String extraction(Integer numberCard, Integer numberAccount, Double amount) {
        String login = this.login(numberCard);
        if(login.equals("Ingreso exitoso")){
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
        return "Ocurrio un error en la extraccion!";
    }

    @Override
    @Transactional
    public String deposit(Integer numberCard, String cbu, Double amount) {
        String login = this.login(numberCard);
        if(login.equals("Ingreso exitoso")){
            Account accountDeposit = this.accountRepository.findByCbu(cbu).get();
            Double updateAccount = accountDeposit.getBalance() + amount;
            accountDeposit.setBalance(updateAccount);
            this.accountRepository.save(accountDeposit);
            return "Depósito exitoso";
        }
        return "Error en el deposito!";
    }

    @Override
    public String balance(Integer numberCard, Integer numberAccount) {
        String login = this.login(numberCard);
        if(login.equals("Ingreso exitoso")) {
            Double balance = this.accountCardRepository.findBalanceByAccount(numberAccount);
            return  "Su saldo es " + balance;
        }
        return "Error en consulta de saldo!";
    }
}
