package com.upgrad.HireWheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Location {

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "location_name" , nullable = false)
    private String locationName;

    @Column(nullable = false , columnDefinition = "VARCHAR2(100)")
    private String address;

    @Column(nullable = false)
    private String pincode;

    @ManyToOne
    City city;

    @OneToMany(mappedBy = "location")
    List<Booking> bookings;

    @OneToMany(mappedBy = "location")
    List<Vehicle> vehicles;

    public Location() {
    }

    public Location(String locationName, String address , String pincode) {
        this.locationName = locationName;
        this.address = address;
        this.pincode = pincode;
    }

    public Location(String locationName, String address, String pincode, City city) {
        this.locationName = locationName;
        this.address = address;
        this.pincode = pincode;
        this.city = city;
    }
}

//location
//        location_id	CHAR(5)	PRIMARY KEY
//        location_name	VARCHAR2(50)	NOT NUL
//        address	VARCHAR2(100)	NOT NULL
//        city_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        pincode	CHAR(6)	NOT NULL

