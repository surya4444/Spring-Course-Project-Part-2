package com.upgrad.HireWheels.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "vehicle_subcategory")
public class VehicleSubcategory {

    @Id @Column(name = "subcat_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "vehicle_type_name" , nullable = false , unique = true)
    private String vehicleTypeName;

    @Column(name = "cost_per_hour" , nullable = false , columnDefinition = "NUMBER(10,2)")
    private double costPerHour;
//vehicleSubcategory_vehicle_type_id

    @ManyToOne
    VehicleCategory vehicleCategory;

    @OneToMany(mappedBy = "vehicleSubcategory" , fetch = FetchType.EAGER)
    //@JsonBackReference
    List<Vehicle> vehicle;

    public VehicleSubcategory() {
    }

    public VehicleSubcategory(String vehicleTypeName , double costPerHour) {
        this.vehicleTypeName = vehicleTypeName;
        this.costPerHour = costPerHour;
    }


}
//vehicle_subcategory
//        vehicle_type_id	CHAR(5)	PRIMARY KEY
//        vehicle_type_name	VARCHAR2(50)	NOT NULL	UNIQUE
//        cost_per_hour	NUMBER(10,2)	NOT NULL
//        vehicle_category_id	CHAR(5)	NOT NULL	FOREIGN KEY
