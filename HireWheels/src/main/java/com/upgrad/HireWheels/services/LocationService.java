package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.Location;
import com.upgrad.HireWheels.exception.LocationNotFoundException;

import java.util.List;

public interface LocationService {
    public Location getLocationDetails(int id) throws LocationNotFoundException;
    public List<Integer> findAllLocationId();
}
