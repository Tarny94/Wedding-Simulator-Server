package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.GuestsCalculation;

import java.util.Optional;

public interface GuestsCalculationService {
    Optional<GuestsCalculation> getWeddingGuestsCalculation(String id);

    void calculateGuests();
}
