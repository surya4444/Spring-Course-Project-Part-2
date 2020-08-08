package com.upgrad.HireWheels.validator;

import com.upgrad.HireWheels.dto.VehicleDTO;
import com.upgrad.HireWheels.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VehicleValidatorImpl implements VehicleValidator{

    @Override
    public ResponseEntity vehicleValidatorForAdding(VehicleDTO vehicleDTO) {
        System.out.println("This is starting point.....");
        if (vehicleDTO.getColor() == null || vehicleDTO.getColor().isEmpty()) {
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if (vehicleDTO.getVehicleImageUrl() == null || vehicleDTO.getVehicleImageUrl().isEmpty()) {
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if (vehicleDTO.getVehicleModel() == null || vehicleDTO.getVehicleModel().isEmpty()){
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if (vehicleDTO.getVehicleNumber() == null || vehicleDTO.getVehicleNumber().isEmpty()){
            CustomResponse response = new CustomResponse(new Date(), "Fields should n’t be null or empty", 400);
            ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        System.out.println("This is end......");
        return null;
    }
}
