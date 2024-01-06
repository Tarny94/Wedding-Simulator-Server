package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.Guest;
import com.weddingsimulator.weddingsim.model.GuestsCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.weddingsimulator.weddingsim.repository.GuestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    private final GuestsCalculationService guestsCalculationService;

    private final WeddingCostCalculationService weddingCostCalculationService;

    @Autowired
    public GuestService(GuestRepository guestRepository, GuestsCalculationService guestsCalculationService, WeddingCostCalculationService weddingCostCalculationService) {
       this.guestRepository = guestRepository;
       this.guestsCalculationService = guestsCalculationService;
        this.weddingCostCalculationService = weddingCostCalculationService;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuest(String id) {
        return guestRepository.findById(id);
    }

    public Guest createGuestService(Guest guest) {
        Guest savedGuest = guestRepository.insert(guest);
        this.guestsCalculationService.calculateGuests();
        this.weddingCostCalculationService.calculateWeddingCostAndIncome();
        return savedGuest;
    }

    public ResponseEntity<Guest> updateGuestService(String id, Guest updatedGuest) {
        Optional<Guest> guestDB = this.guestRepository.findById(id);

        if(guestDB.isPresent()) {
            updatedGuest.setId(id);
            this.guestRepository.save(updatedGuest);
            this.guestsCalculationService.calculateGuests();
            this.weddingCostCalculationService.calculateWeddingCostAndIncome();
            return ResponseEntity.ok(updatedGuest);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> deleteGuest(String id) {
        ResponseEntity<Object> result = guestRepository.findById(id)
                .map(existingGuest -> {
                    guestRepository.delete(existingGuest);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        this.guestsCalculationService.calculateGuests();
        this.weddingCostCalculationService.calculateWeddingCostAndIncome();
        return result;
    }

    public Optional<GuestsCalculation> getGuestsCalculation(String id) {
       return this.guestsCalculationService.getWeddingGuestsCalculation(id);
    }
}
