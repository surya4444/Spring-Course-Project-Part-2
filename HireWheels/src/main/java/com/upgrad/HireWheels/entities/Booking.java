package com.upgrad.HireWheels.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Data
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name = "pickup_date" , nullable = false , columnDefinition = "DATE")
    private Date pickupDate;

    @Column(name = "dropoff_date" , nullable = false , columnDefinition = "DATE")
    private Date dropoffDate;

    @Column(name = "booking_date" , nullable = false , columnDefinition = "DATE")
    private Date bookingDate;

    @Column(nullable = false , columnDefinition = "NUMBER(10,2)")
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_Id")
    Users users;

    @ManyToOne
    @JoinColumn(name = "vehicle_Id")
    Vehicle vehicle;

    @ManyToOne
    Location location;

    public Booking() {
    }

    public Booking(Date pickupDate , Date dropoffDate , Date bookingDate , double amount ,Users users ,Vehicle vehicle , Location location) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.bookingDate = bookingDate;
        this.amount = amount;
        this.users = users;
        this.vehicle = vehicle;
        this.location = location;
    }

    public Booking(Date pickupDate, Date dropoffDate, Date bookingDate, Location location) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.bookingDate = bookingDate;
        this.location = location;
    }
}
//booking
//        booking_id	CHAR(5)	PRIMARY KEY
//        user_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        vehicle_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        pickup_date	DATE	NOT NULL
//        dropoff_date	DATE	NOT NULL
//        booking_date	DATE	NOT NULL
//        location_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        amount	NUMBER(10,2)	NOT NULL
