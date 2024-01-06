package com.weddingsimulator.weddingsim.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "wedding-cost-calculation")
public class WeddingCostCalculation {
    @Id
    private String id;

    private double incomeTotalGuests;

    private double incomeConfirmedGuests;

    private double totalWeddingCost;

    private double totalPayed;

    private double remainToBePay;

    private double resultIncomeTotalGuests;

    private double resultIncomeConfirmedGuests;

    private int suppliesConfirmed;

    private int suppliesPayed;

    private int totalSupplies;
}
