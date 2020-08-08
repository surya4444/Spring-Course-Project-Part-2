package com.upgrad.HireWheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "fuel_type")
public class FuelType {

    @Id
    @Column(name = "fuel_type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fuel_type" , nullable = false , unique = true)
    private String fuelType;

    @OneToMany(mappedBy = "fuelType")
    List<Vehicle> vehicles;

    public FuelType() {
    }

    public FuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}

//fuel_type
//        fuel_type_id	CHAR(5)	PRIMARY KEY
//        fuel_type	VARCHAR2(50)	NOT NULL	UNIQUE
