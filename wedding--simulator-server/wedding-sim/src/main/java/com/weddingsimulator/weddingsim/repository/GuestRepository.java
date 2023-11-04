package com.weddingsimulator.weddingsim.repository;

import com.weddingsimulator.weddingsim.model.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuestRepository extends MongoRepository<Guest, String> {
}
