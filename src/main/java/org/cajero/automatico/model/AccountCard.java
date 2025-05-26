package org.cajero.automatico.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class AccountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccountCard;

    @OneToOne
    @JoinColumn(name = "numberCard")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "numberAccount")
    private Account account;

    public AccountCard() {
    }

    public AccountCard(Card card, Account account) {
        this.card = card;
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
