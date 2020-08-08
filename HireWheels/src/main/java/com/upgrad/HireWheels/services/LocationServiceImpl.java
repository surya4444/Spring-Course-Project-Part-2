package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.LocationDAO;
import com.upgrad.HireWheels.entities.Location;
import com.upgrad.HireWheels.exception.LocationNotFoundException;
import com.upgrad.HireWheels.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService{

    @Qualifier("locationDAO")
    @Autowired
    LocationDAO locationDAO;

    @Override
    public Location getLocationDetails(int id) throws LocationNotFoundException {
        return locationDAO.findById(id).orElseThrow(
                ()->  new LocationNotFoundException("Location not found for " + id));
    }

    @Override
    public List<Integer> findAllLocationId() {
        List<Location> locationList = locationDAO.findAll();
        List<Integer> locationListId = new ArrayList<>();
        for(int i = 0;i<locationList.size() ;i++){
            locationListId.add(locationList.get(i).getId());
        }
        return locationListId;
    }
}
