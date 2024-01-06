package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.GuestsCalculation;
import com.weddingsimulator.weddingsim.model.Supply;
import com.weddingsimulator.weddingsim.model.WeddingCostCalculation;
import com.weddingsimulator.weddingsim.repository.GuestCalculationRepository;
import com.weddingsimulator.weddingsim.repository.SupplyRepository;
import com.weddingsimulator.weddingsim.repository.WeddingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeddingCostCalculationServiceImpl implements WeddingCostCalculationService{

    private final SupplyRepository supplyRepository;

    private final WeddingCostRepository weddingCostRepository;

    private final GuestCalculationRepository guestCalculationRepository;

    private double incomeTotalGuests = 0;

    private double incomeConfirmedGuests = 0;

    private double totalWeddingCost = 0;

    private double totalPayed = 0;

    private int suppliesConfirmed = 0;

    private double suppliesPayed = 0;

    private int totalSupplies = 0;

    @Autowired
    public WeddingCostCalculationServiceImpl(SupplyRepository supplyRepository, WeddingCostRepository weddingCostRepository, GuestCalculationRepository guestCalculationRepository) {
        this.supplyRepository = supplyRepository;
        this.weddingCostRepository = weddingCostRepository;
        this.guestCalculationRepository = guestCalculationRepository;
    }

    public Optional<WeddingCostCalculation> getWeddingCostAndIncome(String id) {
        return this.weddingCostRepository.findById(id);
    }

    public void calculateWeddingCostAndIncome() {
        Optional<GuestsCalculation> guestsCalculation = this.guestCalculationRepository.findById("MockedId");
        List<Supply> suppliesList = this.supplyRepository.findAll();
        WeddingCostCalculation weddingCostCalculation = new WeddingCostCalculation();

        weddingCostCalculation.setId("MockedId");

        for (Supply supply : suppliesList) {
            this.totalWeddingCost += supply.getTotalPrice();
            this.totalPayed += supply.getPayed();
            this.totalSupplies++;

            if(supply.getConfirmed()) {
                this.suppliesConfirmed++;
            }

            if(supply.getTotalPrice() >= supply.getPayed()) {
                this.suppliesPayed++;
            }
        }
        double remainingToBePay = this.totalWeddingCost - this.totalPayed;

        if(guestsCalculation.isPresent()) {
            this.incomeTotalGuests = guestsCalculation.get().getTotalIncome();
            this.incomeConfirmedGuests = guestsCalculation.get().getConfirmedIncome();
        }
        double resultIncomeTotalGuests = this.incomeTotalGuests - this.totalWeddingCost;
        double resultIncomeConfirmedGuests = this.incomeConfirmedGuests - this.totalWeddingCost;

        weddingCostCalculation.setIncomeTotalGuests(this.incomeTotalGuests);
        weddingCostCalculation.setIncomeConfirmedGuests(this.incomeConfirmedGuests);
        weddingCostCalculation.setTotalWeddingCost(this.totalWeddingCost);
        weddingCostCalculation.setTotalPayed(this.totalPayed);
        weddingCostCalculation.setResultIncomeTotalGuests(resultIncomeTotalGuests);
        weddingCostCalculation.setResultIncomeConfirmedGuests(resultIncomeConfirmedGuests);
        weddingCostCalculation.setSuppliesConfirmed(this.suppliesConfirmed);
        weddingCostCalculation.setSuppliesPayed(this.suppliesPayed);
        weddingCostCalculation.setTotalSupplies(this.totalSupplies);
        weddingCostCalculation.setRemainToBePay(remainingToBePay);

        this.weddingCostRepository.save(weddingCostCalculation);
    }

}
