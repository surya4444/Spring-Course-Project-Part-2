package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.VehicleSubcategory;
import com.upgrad.HireWheels.exception.VehicleNotFoundException;

public interface VehicleSubcategoryService {

    public VehicleSubcategory getVehicleSubcategoryDetails(int id) throws VehicleNotFoundException;
}
