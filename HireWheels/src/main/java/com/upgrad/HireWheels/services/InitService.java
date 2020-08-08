package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.*;

public interface InitService {

    public Role acceptRoleTable(Role role);
    public Users acceptUsersTable(Users users);
    public VehicleCategory acceptVehicleCategoryTable(VehicleCategory vehicleCategory);
    public VehicleSubcategory acceptVehicleSubcategoryTable(VehicleSubcategory vehicleSubcategory);
    public FuelType acceptFuelTypeTable(FuelType fuelType);
    public City acceptCityTable(City city);
    public Location acceptLocationTable(Location location);
}
