package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.Guest;
import com.weddingsimulator.weddingsim.model.GuestsCalculation;
import com.weddingsimulator.weddingsim.repository.GuestCalculationRepository;
import com.weddingsimulator.weddingsim.repository.GuestRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestsCalculationServiceImpl implements GuestsCalculationService {

    private final GuestRepository guestRepository;

    private final GuestCalculationRepository guestCalculationRepository;

    private int totalAdults = 0;

    private int confirmedAdults = 0;

    private int totalChildren = 0;
    private int confirmedChildren = 0;

    private int totalLocalChildren = 0;
    private int confirmedLocalChildren = 0;

    private int totalLocalAdults = 0;
    private int confirmedLocalAdults = 0;

    private int totalDistanceChildren = 0;
    private int confirmedDistanceChildren = 0;

    private int totalDistanceAdults = 0;
    private int confirmedDistanceAdults = 0;

    private int totalInvitations = 0;
    private int totalInvited = 0;

    private double totalIncome = 0;
    private double confirmedIncome = 0;

    @Autowired
    public GuestsCalculationServiceImpl( GuestRepository guestRepository, GuestCalculationRepository guestCalculationRepository) {
        this.guestRepository = guestRepository;
        this.guestCalculationRepository = guestCalculationRepository;
    }

    public Optional<GuestsCalculation> getWeddingGuestsCalculation(String id) {
        return this.guestCalculationRepository.findById(id);
    }

    public void calculateGuests() {

        List<Guest> guestList = this.guestRepository.findAll();

        GuestsCalculation guestsCalculation = new GuestsCalculation();

        guestsCalculation.setId("MockedId");

        for (Guest guest : guestList) {
            this.totalAdults += guest.getAdult();
            this.totalChildren += guest.getChildren();
            this.totalIncome += guest.getEstimate();
            this.totalInvitations++;

            if(guest.getInvited().equals("Yes")) {
                this.totalInvited++;
            }

            if(guest.isConfirmed()) {
                this.confirmedAdults += guest.getAdult();
                this.confirmedChildren += guest.getChildren();
                this.confirmedIncome += guest.getEstimate();
            }

            if(guest.getZone().equals("Local")) {
                this.totalLocalAdults += guest.getAdult();
                this.totalLocalChildren += guest.getChildren();

                if(guest.isConfirmed()) {
                    this.confirmedLocalAdults += guest.getAdult();
                    this.confirmedLocalChildren += guest.getChildren();
                }
            }

            if(guest.getZone().equals("Distance")) {
                this.totalDistanceAdults += guest.getAdult();
                this.totalDistanceChildren += guest.getChildren();

                if(guest.isConfirmed()) {
                    this.confirmedDistanceAdults += guest.getAdult();
                    this.confirmedDistanceChildren += guest.getChildren();
                }
            }
        }

        this.saveGuestsCalculation(guestsCalculation);
        this.resetVariables();

    }

    private void resetVariables(){
        totalAdults = 0;

        confirmedAdults = 0;

        totalChildren = 0;
        confirmedChildren = 0;

        totalLocalChildren = 0;
        confirmedLocalChildren = 0;

        totalLocalAdults = 0;
        confirmedLocalAdults = 0;

        totalDistanceChildren = 0;
        confirmedDistanceChildren = 0;

        totalDistanceAdults = 0;
        confirmedDistanceAdults = 0;

        totalInvitations = 0;
        totalInvited = 0;

        totalIncome = 0;
        confirmedIncome = 0;
    }

    private void saveGuestsCalculation(GuestsCalculation guestsCalculation) {
        guestsCalculation.setTotalGuests(totalAdults+totalChildren);
        guestsCalculation.setConfirmedGuests(confirmedAdults+confirmedChildren);
        guestsCalculation.setTotalAdults(totalAdults);
        guestsCalculation.setTotalChildren(totalChildren);
        guestsCalculation.setConfirmedAdults(confirmedAdults);
        guestsCalculation.setConfirmedChildren(confirmedChildren);
        guestsCalculation.setConfirmedLocalAdults(confirmedLocalAdults);
        guestsCalculation.setConfirmedLocalChildren(confirmedLocalChildren);
        guestsCalculation.setTotalLocalAdults(totalLocalAdults);
        guestsCalculation.setTotalLocalChildren(totalLocalChildren);
        guestsCalculation.setConfirmedDistanceAdults(confirmedDistanceAdults);
        guestsCalculation.setConfirmedDistanceChildren(confirmedDistanceChildren);
        guestsCalculation.setTotalDistanceAdults(totalDistanceAdults);
        guestsCalculation.setTotalDistanceChildren(totalDistanceChildren);
        guestsCalculation.setTotalInvited(totalInvited);
        guestsCalculation.setTotalInvitations(totalInvitations);
        guestsCalculation.setTotalIncome(totalIncome);
        guestsCalculation.setConfirmedIncome(confirmedIncome);

        this.guestCalculationRepository.save(guestsCalculation);
    }
}
