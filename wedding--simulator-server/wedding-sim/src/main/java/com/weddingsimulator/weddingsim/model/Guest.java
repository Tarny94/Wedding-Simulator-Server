package com.weddingsimulator.weddingsim.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
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
