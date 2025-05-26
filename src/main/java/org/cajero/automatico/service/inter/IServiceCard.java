package org.cajero.automatico.service.inter;

public interface IServiceCard {
    boolean existsByNumberCardAndActiva(Integer numberCard, String activa);
}
