package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.weddingsimulator.weddingsim.repository.GuestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuest(String id) {
        return guestRepository.findById(id);
    }

    public Guest createGuestService(Guest guest) {
        return guestRepository.insert(guest);
    }

    public ResponseEntity<Guest> updateGuestService(String id, Guest updatedGuest) {
        Optional<Guest> guestDB = this.guestRepository.findById(id);

        if(guestDB.isPresent()) {
            updatedGuest.setId(id);
            this.guestRepository.save(updatedGuest);
            return ResponseEntity.ok(updatedGuest);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> deleteGuest(String id) {
        return guestRepository.findById(id)
                .map(existingGuest -> {
                    guestRepository.delete(existingGuest);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
