package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.VehicleDAO;
import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value = "vehicleService")
public class VehicleServiceImpl implements VehicleService{

    @Qualifier("vehicleDAO")
    @Autowired
    VehicleDAO vehicleDAO;

    @Override
    public Vehicle acceptVehicleDetails(Vehicle vehicle) {
        return vehicleDAO.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }

    @Override
    public Vehicle updateVehicleDetails(int id, Vehicle vehicle) throws VehicleNotFoundException {
        Vehicle  vehicle1 = getVehicleDetails(id);
        System.out.println("Vehicle Details" +vehicle1);
        vehicle1.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        return vehicleDAO.save(vehicle1);
    }

    @Override
    public Vehicle getVehicleDetails(int id) throws VehicleNotFoundException {
        return vehicleDAO.findById(id).orElseThrow(
                ()->  new VehicleNotFoundException("Vehicle not found for " + id));
    }

    @Override
    public List<Vehicle> getVehicleByUserID(int id) {
//        List<Vehicle> usersVehicles = vehicleDAO.vehiclesByUserId(id);
//        System.out.println("usersVehicles only for that id :"+usersVehicles);

        List<Vehicle> vehicles = getAllVehicles();
        List<Vehicle> usersVehicles = new ArrayList<>() ;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getUser().getId() == id) {
                usersVehicles.add(vehicle);
            }
        }
        return usersVehicles;
    }

    @Override
    public List<Vehicle> getAvailableVehicles(Booking booking) {
        List<Vehicle> vehicles = getAllVehicles();
        int location = booking.getLocation().getId();

        List<Vehicle> filter = new ArrayList<>() ;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getAvailabilityStatus() == 1) {
                if (vehicle.getLocation().getId() == location) {
                    filter.add(vehicle);
                }
            }
        }
        return filter;
    }


}
