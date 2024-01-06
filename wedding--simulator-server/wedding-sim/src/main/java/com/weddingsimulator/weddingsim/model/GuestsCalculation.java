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
@Document(collection = "guests-calculation")
public class GuestsCalculation {
    @Id
    private String id;

    private int totalGuests;

    private int confirmedGuests;

    private int totalAdults;

    private int confirmedAdults;

    private int totalChildren;

    private int confirmedChildren;

    private int totalLocalChildren;

    private int confirmedLocalChildren;

    private int totalLocalAdults;

    private int confirmedLocalAdults;

    private int totalDistanceChildren;

    private int confirmedDistanceChildren;

    private int totalDistanceAdults;

    private int confirmedDistanceAdults;

    private int totalInvitations;

    private int totalInvited;

    private double totalIncome;

    private double confirmedIncome;
}
