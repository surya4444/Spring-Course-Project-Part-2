package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.VehicleDAO;
import com.upgrad.HireWheels.entities.*;
import com.upgrad.HireWheels.exception.UserNotRegisterVehicleException;
import com.upgrad.HireWheels.exception.changeAvailabilityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService{

    @Qualifier("vehicleDAO")
    @Autowired
    VehicleDAO vehicleDAO;

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) throws UserNotRegisterVehicleException {

        /**
         * The below line used to fetch role of the vehicle register user
         * There may be one user can both user and Admin so I store in list and compare with string Admin
         */
        List<Role> roles = vehicle.getUser().getRoles().stream().distinct().collect(Collectors.toList());
        String role = null;
        for(int i = 0; i < roles.size() ; i++) {
            role = roles.get(i).getRoleName().toLowerCase();
        }

        //String AdminId = vehicleDAO.needRoleId("Admin").getRoleName();

        if(!role.contains("admin"))
            //throw new UserNotRegisterVehicleException("Only Admin can register the vehicle");
            System.out.println("\n Only Admin can register the vehicle \n");
        vehicle.setAvailabilityStatus(1);
        return vehicleDAO.save(vehicle);
    }

    @Override
    public Vehicle changeAvailability(Vehicle vehicle , int changeAvailability) throws changeAvailabilityException {

        int status = vehicle.getAvailabilityStatus();

        if(status == changeAvailability)
            //throw new changeAvailabilityException("Now your vehicle status is same as what you give");
            System.out.println("\n Now your vehicle status is same as what you give \n");
        else {
            if (status == 0) {
                status = 1;
            }
            else if(status == 1){
                status = 0;
            }
        }
        vehicle.setAvailabilityStatus(status);
        return vehicleDAO.save(vehicle);
    }
}
