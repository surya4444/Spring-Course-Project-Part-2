package com.upgrad.HireWheels.dao;


import com.upgrad.HireWheels.entities.VehicleSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vehicleSubcategoryDAO")
public interface VehicleSubcategoryDAO extends JpaRepository<VehicleSubcategory, Integer> {
}
