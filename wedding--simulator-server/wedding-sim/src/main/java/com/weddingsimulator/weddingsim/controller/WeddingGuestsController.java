package com.weddingsimulator.weddingsim.controller;


import com.weddingsimulator.weddingsim.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weddingsimulator.weddingsim.service.GuestService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("guests")
public class WeddingGuestsController {

    @Autowired
    private GuestService guestService;

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.createGuestService(guest);
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
