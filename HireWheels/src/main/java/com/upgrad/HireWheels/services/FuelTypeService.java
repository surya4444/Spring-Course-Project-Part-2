package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.FuelType;
import com.upgrad.HireWheels.exception.FuelTypeNotFoundException;

public interface FuelTypeService {

    public FuelType getFuelTypeDetails(int id) throws FuelTypeNotFoundException;
}
