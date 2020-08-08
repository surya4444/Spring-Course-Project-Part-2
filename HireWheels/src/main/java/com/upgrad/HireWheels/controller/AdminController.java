package com.upgrad.HireWheels.controller;

import com.upgrad.HireWheels.dao.LocationDAO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.dto.VehicleDTO;
import com.upgrad.HireWheels.entities.Location;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.*;
import com.upgrad.HireWheels.response.CustomResponse;
import com.upgrad.HireWheels.services.LocationService;
import com.upgrad.HireWheels.services.VehicleService;
import com.upgrad.HireWheels.utills.DTOEntityConverter;
import com.upgrad.HireWheels.utills.EntityDTOConverter;
import com.upgrad.HireWheels.validator.VehicleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class AdminController {

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleValidator vehicleValidator;

    @Autowired
    LocationService locationService;

    @Autowired
    LocationDAO locationDAO;

    @PostMapping(value = "/vehicles" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newVehicle(@RequestBody VehicleDTO vehicleDTO) {
        ResponseEntity responseEntity = null;
        //vehicleValidator.vehicleValidatorForAdding(vehicleDTO);

        if (vehicleDTO.getColor() == null || vehicleDTO.getColor().isEmpty()) {
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        if (vehicleDTO.getVehicleImageUrl() == null || vehicleDTO.getVehicleImageUrl().isEmpty()) {
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        if (vehicleDTO.getVehicleModel() == null || vehicleDTO.getVehicleModel().isEmpty()){
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        if (vehicleDTO.getVehicleNumber() == null || vehicleDTO.getVehicleNumber().isEmpty()){
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        List<Integer> locationList = locationService.findAllLocationId();
        if (!locationList.contains(vehicleDTO.getLocationId())){
            CustomResponse response = new CustomResponse(new Date(), "Invalid Location Id for vehicle", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Vehicle newVehicle = dtoEntityConverter.convertToVehicleEntity(vehicleDTO);
            Vehicle storeVehicle = vehicleService.acceptVehicleDetails(newVehicle);
            VehicleDTO responseUserDTO = entityDTOConverter.convertTOVehicleDTO(storeVehicle);
            CustomResponse response = new CustomResponse(new Date(), "Vehicle Added Successfully", 200);
            responseEntity = new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PutMapping("/vehicles/{vehicleid}")
    public ResponseEntity updateVehicleDetails(@PathVariable(name = "vehicleid") int vehicleid , @RequestBody VehicleDTO vehicleDTO) throws VehicleNotFoundException, UserNotFoundException, LocationNotFoundException, FuelTypeNotFoundException {
        if(vehicleDTO.getAvailabilityStatus() != 0 && vehicleDTO.getAvailabilityStatus() !=1){
            CustomResponse response = new CustomResponse(new Date(), "Invalid status value", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        Vehicle vehicle = dtoEntityConverter.forUpdateAvailabilityStatus(vehicleDTO);
        Vehicle updateVehicle = vehicleService.updateVehicleDetails(vehicleid , vehicle);
        VehicleDTO updateVehicleDTO = entityDTOConverter.convertTOVehicleDTO(updateVehicle);
        CustomResponse response = new CustomResponse(new Date(), "Activity Performed Successfully", 200);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
