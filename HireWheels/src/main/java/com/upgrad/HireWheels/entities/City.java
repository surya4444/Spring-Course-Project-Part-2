package com.upgrad.HireWheels.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "city_name" , nullable = false , unique = true)
    private String cityName;

    @OneToMany(mappedBy = "city")
    List<Location> locations;



    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }


}

//city
//        city_id	CHAR(5)	PRIMARY KEY
//        city_name	VARCHAR2(50)	NOT NULl
