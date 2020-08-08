package com.upgrad.HireWheels.utills;

import com.upgrad.HireWheels.dto.BookingDTO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.dto.VehicleDTO;
import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Role;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.UserNotFoundException;
import com.upgrad.HireWheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class EntityDTOConverter {

    @Autowired
    UserService userService;

    public UsersDTO convertToUsersDTO(Users users){

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserId(users.getId());
        usersDTO.setFirstName(users.getFirstName());
        usersDTO.setLastName(users.getLastName());
        usersDTO.setEmail(users.getEmail());
        //usersDTO.setPassword(users.getPassword());
        usersDTO.setMobileNo(users.getMobileNumber());
        usersDTO.setWalletMoney(users.getWalletMoney());
        if(users.getRoles() != null){
            List<Role> roles =users.getRoles();
            Set<Integer> roleIds = new HashSet<>();
            for (Role role : roles){
                roleIds.add(role.getId());
            }
            usersDTO.setRoleId(roleIds);
        }
        return usersDTO;
    }

    public VehicleDTO convertTOVehicleDTO(Vehicle vehicle)  {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
        vehicleDTO.setVehicleModel(vehicle.getVehicleModel());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setVehicleImageUrl(vehicle.getVehicleImageUrl());
        vehicleDTO.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        vehicleDTO.setUserId(vehicle.getUser().getId());
        vehicleDTO.setVehicleSubcategoryId(vehicle.getVehicleSubcategory().getId());
        vehicleDTO.setLocationId(vehicle.getLocation().getId());
        vehicleDTO.setFuelTypeId(vehicle.getFuelType().getId());
        return vehicleDTO;
    }

    public BookingDTO convertToBookingDto(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setPickupDate(booking.getBookingDate());
        bookingDTO.setDropoffDate(booking.getDropoffDate());
        bookingDTO.setAmount(booking.getAmount());
        bookingDTO.setLocationId(booking.getLocation().getId());
        bookingDTO.setUsersId(booking.getUsers().getId());
        bookingDTO.setVehicleId(booking.getVehicle().getId());
       return bookingDTO;
    }
}