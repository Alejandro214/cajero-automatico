package org.cajero.automatico.config;

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
public class SetUpConfig {

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



            // Crear una segunda cuenta
            Account account2 = new Account();
            account2.setNumberAccount(54321);
            account2.setCbu("0987654321098765432100");
            account2.setBalance(1000.0);
            account2 = accountRepo.save(account2);


            // Crear una segunda tarjeta
            Card card1 = new Card();
            card1.setNumberCard(456789);
            card1.setActiva("S");
            card1 = cardRepo.save(card1);

            // Asociar cuenta y tarjeta
            AccountCard accountCard1 = new AccountCard();
            accountCard1.setAccount(account2);
            accountCard1.setCard(card1);
            accountCardRepo.save(accountCard1);
        };
    }
}
