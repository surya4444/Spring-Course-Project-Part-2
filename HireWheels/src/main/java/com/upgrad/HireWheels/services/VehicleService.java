package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.VehicleNotFoundException;

import java.util.List;

public interface VehicleService {

    public Vehicle acceptVehicleDetails(Vehicle vehicle);
    public List<Vehicle> getAllVehicles();
    public Vehicle updateVehicleDetails(int id , Vehicle vehicle) throws VehicleNotFoundException;
    public List<Vehicle> getVehicleByUserID(int id);
    public List<Vehicle> getAvailableVehicles(Booking booking);
    public Vehicle getVehicleDetails(int id) throws VehicleNotFoundException;
}
