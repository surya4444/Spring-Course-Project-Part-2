package com.upgrad.HireWheels.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Data
public class Vehicle {

    @Id
    @Column(name = "vehicle_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "vehicle_model" , nullable = false)
    private String vehicleModel;

    @Column(name = "vehicle_number" )
    private String vehicleNumber;

    @Column(nullable = false )
    private String color;

    @Column(name = "vehicle_image_url" , nullable = false , columnDefinition = "VARCHAR2(500)")
    private String vehicleImageUrl;

    @Column(name = "availability_status" , columnDefinition = "NUMBER(1)" , nullable = false)
    private int availabilityStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    Users user;

    //vehicleSubcategory_vehicle_type_id
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    VehicleSubcategory vehicleSubcategory;

    @ManyToOne
    FuelType fuelType;

    @ManyToOne
    Location location;

    @OneToMany(mappedBy = "vehicle")
    List<Booking> booking;

    @OneToMany(mappedBy = "vehicle")
    List<Request> request;

    public Vehicle() {
    }

    public Vehicle(String vehicleModel, String vehicleNumber, String color, String vehicleImageUrl , Users user  , VehicleSubcategory vehicleSubcategory, FuelType fuelType, Location location) {
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
        this.vehicleImageUrl = vehicleImageUrl;
        this.user = user;
        this.vehicleSubcategory = vehicleSubcategory;
        this.fuelType = fuelType;
        this.location = location;
    }

    public Vehicle(String vehicleModel, String vehicleNumber, String color, String vehicleImageUrl , int availabilityStatus , Users user  , VehicleSubcategory vehicleSubcategory, FuelType fuelType, Location location) {
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
        this.vehicleImageUrl = vehicleImageUrl;
        this.availabilityStatus = availabilityStatus;
        this.user = user;
        this.vehicleSubcategory = vehicleSubcategory;
        this.fuelType = fuelType;
        this.location = location;
    }

    public Vehicle(String vehicleModel, String vehicleNumber, String color, String vehicleImageUrl ,int availabilityStatus) {
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
        this.vehicleImageUrl = vehicleImageUrl;
        this.availabilityStatus =availabilityStatus ;
    }

    public Vehicle(String vehicleModel, String vehicleNumber, String color, String vehicleImageUrl) {
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
        this.vehicleImageUrl = vehicleImageUrl;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", color='" + color + '\'' +
                ", vehicleImageUrl='" + vehicleImageUrl + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", user=" + user +
                '}';
    }
}


//vehicle
//        vehicle_id	CHAR(5)	PRIMARY KEY
//        vehicle_model	VARCHAR2(50)	NOT NULL
//        user_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        vehicle_number	CHAR(10)	PRIMARY KEY
//        vehicle_type_id	CHAR(5)	FOREIGN KEY
//        color	VARCHAR2(50)	NOT NULL
//        fuel_type_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        location_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        vehicle_image_url	VARCHAR2(500)	NOT NULL
