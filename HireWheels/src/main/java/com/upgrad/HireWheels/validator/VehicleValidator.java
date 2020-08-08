package com.upgrad.HireWheels.validator;

import com.upgrad.HireWheels.dto.VehicleDTO;
import org.springframework.http.ResponseEntity;

public interface VehicleValidator {

    public ResponseEntity vehicleValidatorForAdding(VehicleDTO vehicleDTO);
}
