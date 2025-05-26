package org.cajero.automatico.service;

import org.cajero.automatico.model.Account;
import org.cajero.automatico.model.AccountCard;
import org.cajero.automatico.model.Card;
import org.cajero.automatico.repository.AccountCardRepository;
import org.cajero.automatico.repository.AccountRepository;
import org.cajero.automatico.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceSetUp {

    @Bean
    CommandLineRunner initData(
            AccountRepository accountRepo,
            CardRepository cardRepo,
            AccountCardRepository accountCardRepo
    ) {
        return args -> {
            // Crear una cuenta
            Account account = new Account();
            account.setNumberAccount(12345);
            account.setCbu("0012345678901234567890");
            account.setBalance(5000.0);
            account = accountRepo.save(account);

            // Crear una tarjeta
            Card card = new Card();
            card.setNumberCard(987654);
            card.setActiva("S");
            card = cardRepo.save(card);

            // Asociar cuenta y tarjeta
            AccountCard accountCard = new AccountCard();
            accountCard.setAccount(account);
            accountCard.setCard(card);
            accountCardRepo.save(accountCard);
        };
    }
}
