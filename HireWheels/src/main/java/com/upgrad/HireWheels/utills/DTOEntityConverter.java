package com.upgrad.HireWheels.utills;


import com.upgrad.HireWheels.dao.RoleDAO;
import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.dto.BookingDTO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.dto.VehicleDTO;
import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Role;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.FuelTypeNotFoundException;
import com.upgrad.HireWheels.exception.LocationNotFoundException;
import com.upgrad.HireWheels.exception.UserNotFoundException;
import com.upgrad.HireWheels.exception.VehicleNotFoundException;
import com.upgrad.HireWheels.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOEntityConverter
{
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleSubcategoryService vehicleSubcategoryService;

    @Autowired
    FuelTypeService fuelTypeService;

    @Autowired
    LocationService locationService;
    public Users convertToUsersEntity(UsersDTO usersDTO) throws UserNotFoundException{

        Users users = new Users();
        users.setFirstName(usersDTO.getFirstName());
        users.setLastName(usersDTO.getLastName());
        users.setEmail(usersDTO.getEmail());
        users.setPassword(usersDTO.getPassword());
        users.setMobileNumber(usersDTO.getMobileNo());
        users.setWalletMoney(usersDTO.getWalletMoney());
        List<Role> roleList = new ArrayList<>();
        if (usersDTO.getRoleId() != null) {
            for (Integer roleId : usersDTO.getRoleId()) {
                roleList.add(roleService.getRoleDetails(roleId));
            }
        }
        users.setRoles(roleList);
        return users;
    }


    public Vehicle convertToVehicleEntity(VehicleDTO vehicleDTO) throws UserNotFoundException, VehicleNotFoundException, LocationNotFoundException, FuelTypeNotFoundException {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setVehicleImageUrl(vehicleDTO.getVehicleImageUrl());
        vehicle.setAvailabilityStatus(vehicleDTO.getAvailabilityStatus());
        vehicle.setUser(userService.getUsersDetails(vehicleDTO.getUserId()));
        vehicle.setVehicleSubcategory(vehicleSubcategoryService.getVehicleSubcategoryDetails(vehicleDTO.getVehicleSubcategoryId()));
        vehicle.setLocation(locationService.getLocationDetails(vehicleDTO.getLocationId()));
        vehicle.setFuelType(fuelTypeService.getFuelTypeDetails(vehicleDTO.getFuelTypeId()));
        return vehicle;
    }

    public Vehicle forUpdateAvailabilityStatus(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle();
        vehicle.setAvailabilityStatus(vehicleDTO.getAvailabilityStatus());
        return vehicle;
    }

    public Booking convertToBookingEntity(BookingDTO bookingDTO) throws LocationNotFoundException, UserNotFoundException, VehicleNotFoundException {
        Booking booking = new Booking();

        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setPickupDate(bookingDTO.getBookingDate());
        booking.setDropoffDate(bookingDTO.getDropoffDate());
        booking.setAmount(bookingDTO.getAmount());
        booking.setLocation(locationService.getLocationDetails(bookingDTO.getLocationId()));
        booking.setUsers(userService.getUsersDetails(bookingDTO.getUsersId()));
        booking.setVehicle(vehicleService.getVehicleDetails(bookingDTO.getVehicleId()));
        return booking;
    }
}