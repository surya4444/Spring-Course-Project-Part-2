package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.*;
import com.upgrad.HireWheels.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "initService")
public class InitServiceImpl implements InitService{

    @Qualifier("roleDAO")
    @Autowired
    RoleDAO roleDAO;

    @Qualifier("usersDAO")
    @Autowired
    UsersDAO usersDAO;

    @Qualifier("vehicleCategoryDAO")
    @Autowired
    VehicleCategoryDAO vehicleCategoryDAO;

    @Qualifier("vehicleSubcategoryDAO")
    @Autowired
    VehicleSubcategoryDAO vehicleSubcategoryDAO;

    @Qualifier("fuelTypeDAO")
    @Autowired
    FuelTypeDAO fuelTypeDAO;

    @Qualifier("cityDAO")
    @Autowired
    CityDAO cityDAO;

    @Qualifier("locationDAO")
    @Autowired
    LocationDAO locationDAO;

    @Override
    public Role acceptRoleTable(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public Users acceptUsersTable(Users users) {
        return usersDAO.save(users);
    }

    @Override
    public VehicleCategory acceptVehicleCategoryTable(VehicleCategory vehicleCategory) {
        return vehicleCategoryDAO.save(vehicleCategory);
    }

    @Override
    public VehicleSubcategory acceptVehicleSubcategoryTable(VehicleSubcategory vehicleSubcategory) {
        return vehicleSubcategoryDAO.save(vehicleSubcategory);
    }

    @Override
    public FuelType acceptFuelTypeTable(FuelType fuelType) {
        return fuelTypeDAO.save(fuelType);
    }

    @Override
    public City acceptCityTable(City city) {
        return cityDAO.save(city);
    }

    @Override
    public Location acceptLocationTable(Location location) {
        return locationDAO.save(location);
    }
}
