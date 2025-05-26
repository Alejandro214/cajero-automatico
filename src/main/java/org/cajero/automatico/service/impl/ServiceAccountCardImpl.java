package org.cajero.automatico.service.impl;

import jakarta.transaction.Transactional;
import org.cajero.automatico.model.Account;
import org.cajero.automatico.model.AccountCard;
import org.cajero.automatico.repository.AccountCardRepository;
import org.cajero.automatico.repository.AccountRepository;
import org.cajero.automatico.repository.CardRepository;
import org.cajero.automatico.service.inter.IServiceAccountCard;
import org.cajero.automatico.service.inter.IServiceCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAccountCardImpl implements IServiceAccountCard {
    @Autowired
    private AccountCardRepository accountCardRepository;

    @Autowired
    private IServiceCard iServiceCard;

    @Autowired
    private ServiceAccountImpl serviceAccount;

    @Override
    public String login(Integer numberCard) {
        if(iServiceCard.existsByNumberCardAndActiva(numberCard,"S"))
            return "Ingreso exitoso";
        return "Ingreso no exitoso";
    }

    @Override
    @Transactional
    public String extraction(Integer numberCard, Integer numberAccount, Double amount) {
        String login = this.login(numberCard);
        if(!login.equals("Ingreso exitoso"))
            return "Error en consulta de saldo!";
        Optional<AccountCard> accountCardOptional = this.accountCardRepository.findByCard_NumberCardAndAccount_NumberAccount(numberCard,numberAccount);
        if(accountCardOptional.isEmpty())
            return "Error Cuenta no encontrada para el numero de tarjeta y numero de cuenta proporcionados!";

        AccountCard accountCard = accountCardOptional.get();
        Account account = accountCard.getAccount();
        Double amountAccount = account.getBalance();
        if(amountAccount >= amount){
            amountAccount-= amount;
            account.setBalance(amountAccount);
            accountCard.setAccount(account);
            this.accountCardRepository.save(accountCard);
            return "Retire su dinero";
        }
        return "Error el monto seleccionado excede la cantidad disponible!";
    }

    @Override
    @Transactional
    public String deposit(Integer numberCard, String cbu, Double amount) {
        String login = this.login(numberCard);
        if(!login.equals("Ingreso exitoso"))
            return "Error en consulta de saldo!";
        Optional<Account> accountOptional = this.serviceAccount.findByCbu(cbu);
        if(accountOptional.isEmpty())
            return "Error Cuenta no encontrada para el CBU proporcionado!";
        Account accountDeposit = accountOptional.get();
        Double updateAccount = accountDeposit.getBalance() + amount;
        accountDeposit.setBalance(updateAccount);
        this.serviceAccount.save(accountDeposit);
        return "Depósito exitoso";
    }

    @Override
    public String balance(Integer numberCard, Integer numberAccount) {
        String login = this.login(numberCard);
        if(!login.equals("Ingreso exitoso"))
            return "Error en consulta de saldo!";
        Double balance = this.accountCardRepository.findBalanceByAccount(numberAccount);
        if(balance == null)
            return "Error en consulta de saldo: cuenta no encontrada!";
        return  "Su saldo es " + balance;
    }
}
