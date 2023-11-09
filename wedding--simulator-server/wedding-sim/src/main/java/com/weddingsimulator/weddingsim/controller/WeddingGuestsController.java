package com.weddingsimulator.weddingsim.controller;


import com.weddingsimulator.weddingsim.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weddingsimulator.weddingsim.service.GuestService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("guests")
public class WeddingGuestsController {


    private final GuestService guestService;

    @Autowired
    public WeddingGuestsController(final GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.createGuestService(guest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable String id, @RequestBody Guest updatedGuest) {
        return this.guestService.updateGuestService(id, updatedGuest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuest(@PathVariable String id) {
        return this.guestService.deleteGuest(id);
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuest(@PathVariable String id) {
        Optional<Guest> guest = guestService.getGuest(id);
        return guest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
