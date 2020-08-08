package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.FuelTypeDAO;
import com.upgrad.HireWheels.entities.FuelType;
import com.upgrad.HireWheels.exception.FuelTypeNotFoundException;
import com.upgrad.HireWheels.exception.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fuelTypeService")
public class FuelTypeServiceImpl implements FuelTypeService{

    @Qualifier("fuelTypeDAO")
    @Autowired
    FuelTypeDAO fuelTypeDAO;

    @Override
    public FuelType getFuelTypeDetails(int id) throws FuelTypeNotFoundException {
        return fuelTypeDAO.findById(id).orElseThrow(
                ()->  new FuelTypeNotFoundException("FuelType not found for " + id));
    }
}
