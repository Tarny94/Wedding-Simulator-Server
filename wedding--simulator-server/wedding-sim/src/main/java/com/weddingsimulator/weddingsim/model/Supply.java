package com.weddingsimulator.weddingsim.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "supplies")
public class Supply {
    @Id
    private String id;

    @NonNull
    private String supply;

    private double price;

    private int unit;

    private double totalPrice;

    private double payed;

    private Boolean confirmed;

    private String observation;

}
