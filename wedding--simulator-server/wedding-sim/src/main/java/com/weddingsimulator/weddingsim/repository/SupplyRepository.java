package com.weddingsimulator.weddingsim.repository;

import com.weddingsimulator.weddingsim.model.Supply;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplyRepository extends MongoRepository<Supply, String> {
}
