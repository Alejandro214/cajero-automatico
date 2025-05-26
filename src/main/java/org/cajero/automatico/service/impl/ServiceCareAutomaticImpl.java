package org.cajero.automatico.service.impl;

import org.cajero.automatico.service.inter.ICareAutomatic;
import org.cajero.automatico.service.inter.IServiceAccountCard;
import org.cajero.automatico.service.inter.ITransactionAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Este servicio se encarga se hacer las operaciones del cajero y de registrar cada una
@Service
public class ServiceCareAutomaticImpl implements ICareAutomatic {

    @Autowired
    private IServiceAccountCard iServiceAccountCard;

    @Autowired
    private ITransactionAuditService iTransactionAuditService;

    @Override
    public String login(Integer numberCard) {
        String login = this.iServiceAccountCard.login(numberCard);
        this.iTransactionAuditService.registerTranctionAudit("LOGIN",numberCard,null,"",null,login);
        return login;
    }

    @Override
    public String extraction(Integer numberCard, Integer numberAccount, Double amount) {
        String extraction = this.iServiceAccountCard.extraction(numberCard,numberAccount,amount);
        this.iTransactionAuditService.registerTranctionAudit("extraction",numberCard,numberAccount,"",amount,extraction);
        return extraction;

    }
    @Override
    public String deposit(Integer numberCard, String cbu, Double amount) {
        String deposit = this.iServiceAccountCard.deposit(numberCard,cbu,amount);
        this.iTransactionAuditService.registerTranctionAudit("deposit",numberCard,null,cbu,amount,deposit);
        return deposit;
    }

    @Override
    public String balance(Integer numberCard, Integer numberAccount) {
        String balance = this.iServiceAccountCard.balance(numberCard,numberAccount);
        this.iTransactionAuditService.registerTranctionAudit("balance",numberCard,numberAccount,"",null,balance);
        return balance;
    }

}
