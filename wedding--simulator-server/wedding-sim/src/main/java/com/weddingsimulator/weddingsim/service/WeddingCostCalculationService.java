package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.WeddingCostCalculation;

import java.util.Optional;

public interface WeddingCostCalculationService {

    void calculateWeddingCostAndIncome();

     Optional<WeddingCostCalculation> getWeddingCostAndIncome(String id);
}
