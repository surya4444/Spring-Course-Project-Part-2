package com.upgrad.HireWheels.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class VehicleDTO {

    int vehicleId;

    String vehicleModel;

    String vehicleNumber;

    String color;

    String vehicleImageUrl;

    int availabilityStatus;

    int userId;

    int vehicleSubcategoryId;

    int fuelTypeId;

    int locationId;

}
