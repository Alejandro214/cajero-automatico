package org.cajero.automatico.service.inter;

public interface IServiceAccountCard {
    String login(Integer numberCard);
    String extraction(Integer numberCard, Integer numberAccount, Double amount);

    String deposit(Integer numberCard, String cbu,Double amount);

    String balance(Integer numberCard, Integer numberAccount);

}
