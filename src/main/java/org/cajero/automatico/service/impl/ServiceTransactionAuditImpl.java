package org.cajero.automatico.service.impl;

import org.cajero.automatico.model.TransactionAudit;
import org.cajero.automatico.repository.TransactionAuditRepository;
import org.cajero.automatico.service.inter.ITransactionAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceTransactionAuditImpl implements ITransactionAuditService {

    @Autowired
    private TransactionAuditRepository transactionAuditRepository;

    @Override
    public void registerTranctionAudit(String operationType, Integer numberCard, Integer numberAccount,String cbu, Double amount, String message) {
        TransactionAudit audit = new TransactionAudit();
        audit.setTimestamp(LocalDateTime.now());
        audit.setOperationType(operationType);
        audit.setNumberCard(numberCard);
        audit.setNumberAccount(numberAccount);
        audit.setCbu(cbu);
        audit.setAmount(amount);
        audit.setMessage(message);
        this.transactionAuditRepository.save(audit);
    }


}
