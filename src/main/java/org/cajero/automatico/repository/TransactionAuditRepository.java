package org.cajero.automatico.repository;

import org.cajero.automatico.model.TransactionAudit;
import org.springframework.data.repository.CrudRepository;

public interface TransactionAuditRepository extends CrudRepository<TransactionAudit,Long> {

}
