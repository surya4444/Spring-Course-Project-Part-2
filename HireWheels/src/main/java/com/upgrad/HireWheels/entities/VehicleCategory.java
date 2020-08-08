package com.upgrad.HireWheels.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vehicle_category")
public class VehicleCategory {
    //vechileCategory_vehicle_category_id
    @Id
    @Column(name = "vehi_cat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "vehicle_category_name" , nullable = false , unique = true)
    private String vehicleCategoryName;

    @OneToMany(mappedBy = "vehicleCategory")
    List<VehicleSubcategory> vehicleSubcategories;

    public VehicleCategory() {
    }

    public VehicleCategory(String vehicleCategoryName) {
        this.vehicleCategoryName = vehicleCategoryName;
    }

}
//vehicle_category
//        vehicle_category_id	CHAR(5)	PRIMARY KEY
//        vehicle_category_name	VARCHAR2(50)	NOT NULL	UNIQUE
