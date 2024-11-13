package com.neueda.buildingaccessmanagementsimulator.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userdata")
public class User {

    @Id
    private Integer id;
    private String firstname;
    private String surname;
}
