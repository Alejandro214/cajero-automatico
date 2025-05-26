package org.cajero.automatico.service.inter;

public interface ITransactionAuditService {
     void registerTranctionAudit(String operationType, Integer numberCard, Integer numberAccount,String cbu, Double amount, String message);


}
