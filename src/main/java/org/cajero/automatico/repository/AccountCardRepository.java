package org.cajero.automatico.repository;

import org.cajero.automatico.model.AccountCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountCardRepository  extends CrudRepository<AccountCard,Long> {

    Optional<AccountCard> findByCard_NumberCardAndAccount_NumberAccount(Integer numberCard, Integer numberAccount);

    @Query("SELECT ac.account.balance FROM AccountCard ac WHERE ac.account.numberAccount = :numberAccount")
    Double findBalanceByAccount(@Param("numberAccount") Integer numberAccount);

}
