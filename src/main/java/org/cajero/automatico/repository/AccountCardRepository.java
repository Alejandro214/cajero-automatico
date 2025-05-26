package org.cajero.automatico.repository;

import org.cajero.automatico.model.AccountCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountCardRepository  extends CrudRepository<AccountCard,Long> {

    Optional<AccountCard> findByCard_NumberCard(Integer numberCard);

    Optional<AccountCard> findByCard_NumberCardAndAccount_NumberAccount(Integer numberCard, Integer numberAccount);

    boolean existsByCard_NumberCardAndCard_Activa(Integer numberCard,String activa);

    @Query("SELECT ac.account.balance FROM AccountCard ac " +
            "WHERE ac.card.numberCard = :numberCard AND ac.account.numberAccount = :numberAccount")
    Double findBalanceByCardAndAccount(@Param("numberCard") Integer numberCard,
                                       @Param("numberAccount") Integer numberAccount);

    AccountCard findByAccount_Cbu(String cbu);

}
