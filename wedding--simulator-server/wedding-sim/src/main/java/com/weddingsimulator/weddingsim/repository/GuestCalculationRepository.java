package com.weddingsimulator.weddingsim.repository;

import com.weddingsimulator.weddingsim.model.GuestsCalculation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuestCalculationRepository extends MongoRepository<GuestsCalculation, String> {
}
