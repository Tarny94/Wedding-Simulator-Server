package com.weddingsimulator.weddingsim.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collation = "guests-details")
public class Guest {
    @Id
    private String id;

    @NonNull
    private String familyName ;

    private int adult;

    private int children;

    @NonNull
    private String status;

    @NonNull
    private String invited;

    @NonNull
    private String zone;

    private int estimate;
}
