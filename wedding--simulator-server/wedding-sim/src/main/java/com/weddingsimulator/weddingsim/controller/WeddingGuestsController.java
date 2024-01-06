package com.weddingsimulator.weddingsim.controller;


import com.weddingsimulator.weddingsim.model.Guest;
import com.weddingsimulator.weddingsim.model.GuestsCalculation;
import com.weddingsimulator.weddingsim.model.Supply;
import com.weddingsimulator.weddingsim.model.WeddingCostCalculation;
import com.weddingsimulator.weddingsim.service.GuestsCalculationService;
import com.weddingsimulator.weddingsim.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weddingsimulator.weddingsim.service.GuestService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("wedding")
public class WeddingGuestsController {


    private final GuestService guestService;

    private final SupplyService supplyService;


    @Autowired
    public WeddingGuestsController(final GuestService guestService, final SupplyService supplyService) {
        this.guestService = guestService;
        this.supplyService = supplyService;
    }

    @PostMapping("/guests")
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.createGuestService(guest);
    }

    @PutMapping("/guests/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable String id, @RequestBody Guest updatedGuest) {
        return this.guestService.updateGuestService(id, updatedGuest);
    }

    @DeleteMapping("/guests/{id}")
    public ResponseEntity<Object> deleteGuest(@PathVariable String id) {
        return this.guestService.deleteGuest(id);
    }

    @GetMapping("/guests")
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping("/guests/{id}")
    public ResponseEntity<Guest> getGuest(@PathVariable String id) {
        Optional<Guest> guest = guestService.getGuest(id);
        return guest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/guests/calculate/{id}")
    public Optional<GuestsCalculation> getGuestCalculation(@PathVariable String id) {
        return this.guestService.getGuestsCalculation(id);
    }

    @PostMapping("/supplies")
    public Supply addSupply(@RequestBody Supply supply) {
        return this.supplyService.addSupply(supply);
    }

    @GetMapping("/supplies")
    public List<Supply> getSupplies(){
        return this.supplyService.getSupplies();
    }

    @GetMapping("/supplies/{id}")
    public Optional<Supply> getSupply(@PathVariable String id) {
        return this.supplyService.getSupply(id);
    }

    @GetMapping("/supplies/calculate/{id}")
    public Optional<WeddingCostCalculation> getSuppliesCalculation(@PathVariable String id) {
        return this.supplyService.getSuppliesCalculation(id);
    }

    @PutMapping("/supplies/{id}")
    public Supply updateSupply(@PathVariable String id, @RequestBody Supply supply) throws Exception {
        return this.supplyService.updateSupply(id, supply);
    }

    @DeleteMapping("/supplies/{id}")
    public String deleteSupply(@PathVariable String id) {
        return this.supplyService.deleteSupply(id);
    }


}
