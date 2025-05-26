package org.cajero.automatico.service.impl;


import org.cajero.automatico.repository.CardRepository;
import org.cajero.automatico.service.inter.IServiceCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCardImpl implements IServiceCard {

    @Autowired
    private CardRepository cardRepository;


    @Override
    public boolean existsByNumberCardAndActiva(Integer numberCard, String activa) {
        return this.cardRepository.existsByNumberCardAndActiva(numberCard,activa);
    }
}
