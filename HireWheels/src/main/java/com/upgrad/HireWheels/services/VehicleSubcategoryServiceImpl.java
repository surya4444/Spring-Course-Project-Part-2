package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.VehicleDAO;
import com.upgrad.HireWheels.dao.VehicleSubcategoryDAO;
import com.upgrad.HireWheels.entities.VehicleSubcategory;
import com.upgrad.HireWheels.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("vehicleSubcategoryService")
public class VehicleSubcategoryServiceImpl implements VehicleSubcategoryService{

    @Qualifier("vehicleSubcategoryDAO")
    @Autowired
    VehicleSubcategoryDAO vehicleSubcategoryDAO;

    @Override
    public VehicleSubcategory getVehicleSubcategoryDetails(int id) throws VehicleNotFoundException {
        return vehicleSubcategoryDAO.findById(id).orElseThrow(
                ()->  new VehicleNotFoundException("VehicleSubcategory not found for " + id));
    }
}
