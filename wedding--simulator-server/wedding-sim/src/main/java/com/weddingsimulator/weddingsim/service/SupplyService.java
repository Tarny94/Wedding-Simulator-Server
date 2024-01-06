package com.weddingsimulator.weddingsim.service;

import com.weddingsimulator.weddingsim.model.Supply;
import com.weddingsimulator.weddingsim.model.WeddingCostCalculation;
import com.weddingsimulator.weddingsim.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;

    private final WeddingCostCalculationService weddingCostCalculationService;

    @Autowired
    public SupplyService(SupplyRepository supplyRepository, WeddingCostCalculationService weddingCostCalculationService) {
        this.supplyRepository = supplyRepository;
        this.weddingCostCalculationService = weddingCostCalculationService;
    }

    public List<Supply> getSupplies() {
        return this.supplyRepository.findAll();
    }

    public Optional<Supply> getSupply(String id) {
        return this.supplyRepository.findById(id);
    }

    public Supply addSupply(Supply supply) {
        supply.setTotalPrice(calculateTotalPrice(supply));
        Supply supplyDB = this.supplyRepository.insert(supply);
        this.weddingCostCalculationService.calculateWeddingCostAndIncome();
        return supplyDB;
    }

    public Supply updateSupply(String id, Supply updateSupply) throws Exception {
        Optional<Supply> supplyDB = this.supplyRepository.findById(id);

        if(supplyDB.isPresent()) {
            updateSupply.setId(id);
            updateSupply.setTotalPrice(calculateTotalPrice(updateSupply));
            Supply data = this.supplyRepository.save(updateSupply);
            this.weddingCostCalculationService.calculateWeddingCostAndIncome();
            return data;
        }
        throw new Exception("Supply not found");
    }

    public String deleteSupply(String id) {
        Optional<Supply> supplyDB = this.supplyRepository.findById(id);

        if(supplyDB.isPresent()) {
            this.supplyRepository.delete(supplyDB.get());
            this.weddingCostCalculationService.calculateWeddingCostAndIncome();
            return "Supply Deleted";
        }
        return "Supply not Found";
    }

    public Optional<WeddingCostCalculation> getSuppliesCalculation(String id) {
        return this.weddingCostCalculationService.getWeddingCostAndIncome(id);
    }

    private double calculateTotalPrice(Supply supply) {
        if(supply.getPrice() == 0) {
            return 0;
        }

        if(supply.getUnit() == 0 || supply.getUnit() == 1) {
            supply.setUnit(1);
            return supply.getPrice();
        }

      return supply.getPrice() * supply.getUnit();
    }
}
