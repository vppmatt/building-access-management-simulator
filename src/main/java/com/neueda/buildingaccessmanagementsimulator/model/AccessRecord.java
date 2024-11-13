package com.neueda.buildingaccessmanagementsimulator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    private Integer dataset;

    @ManyToOne
    private User user;
    private LocalTime time;
    @ManyToOne
    private Building building;
    Boolean status;


}
