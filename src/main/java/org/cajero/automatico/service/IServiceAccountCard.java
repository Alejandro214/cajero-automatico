package org.cajero.automatico.service;

public interface IServiceAccountCard {
    String login(Integer numberCard);
    String extraction(Integer numberCard, Integer numberAccount, Double amount);

    String deposit(Integer numberCard, String cbu,Double amount);

    Double balance(Integer numberCard, Integer numberAccount);

}
