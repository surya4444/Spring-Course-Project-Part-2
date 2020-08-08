package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.UserNotRegisterVehicleException;
import com.upgrad.HireWheels.exception.changeAvailabilityException;

public interface AdminService {

    public Vehicle registerVehicle(Vehicle vehicle) throws UserNotRegisterVehicleException;
    public Vehicle changeAvailability(Vehicle vehicle , int changeAvailability) throws changeAvailabilityException;
}
