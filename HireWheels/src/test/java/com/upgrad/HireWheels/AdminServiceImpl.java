package com.upgrad.HireWheels;

import com.upgrad.HireWheels.dao.*;
import com.upgrad.HireWheels.entities.*;
import com.upgrad.HireWheels.exception.UserNotRegisterVehicleException;
import com.upgrad.HireWheels.services.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:HireWheels.xml"})
public class AdminServiceImpl {

    @Autowired
    AdminService adminService;

    @Qualifier("usersDAO")
    @Autowired
    UsersDAO usersDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    VehicleSubcategoryDAO vehicleSubcategoryDAO;

    @Autowired
    FuelTypeDAO fuelTypeDAO;

    @Autowired
    LocationDAO locationDAO;

    @Autowired
    CityDAO cityDAO;


    static Users Admin1;
    static VehicleSubcategory suv;
    static FuelType petrol;
    static Location powai;
    static City mumbai;
    static Users vamsiUsers;
    static VehicleSubcategory hatchback;
    static Location worli;


    @BeforeEach
    public void dataInsert() {

        Role userRole = roleDAO.save(new Role("User"));
        Role adminRole = roleDAO.save(new Role("Admin"));
        Admin1 = usersDAO.save(new Users( "Admin" , "Admin" , "admin@123" , "upgrad@gmail.com" , "9999999999" , 10000));

        Admin1 = usersDAO.findById(Admin1.getId()).get();
        adminRole = roleDAO.findById(adminRole.getId()).get();

        adminRole.getUsers().add(Admin1);
        roleDAO.save(adminRole);
        Admin1.getRoles().add(adminRole);
        usersDAO.save(Admin1);

        suv = vehicleSubcategoryDAO.save(new VehicleSubcategory("suv" , 300));
        petrol = fuelTypeDAO.save(new FuelType("petrol"));
        mumbai = cityDAO.save(new City("mumbai"));
        powai = locationDAO.save(new Location("Powai" , "Hiranandani Towers","400020" , mumbai));


        vamsiUsers = usersDAO.save(new Users( "vamsi" , "rahul" , "vamsi@123" , "vamsi@gmail.com" , "9835671205" , 1000));

        vamsiUsers = usersDAO.findById(vamsiUsers.getId()).get();
        userRole = roleDAO.findById(userRole.getId()).get();

        userRole.getUsers().add(vamsiUsers);
        roleDAO.save(userRole);
        vamsiUsers.getRoles().add(userRole);
        usersDAO.save(vamsiUsers);

        hatchback = vehicleSubcategoryDAO.save(new VehicleSubcategory("hatchback" , 250));
        worli = locationDAO.save(new Location("Worli" , "Dr E Moses Rd, Worli Naka, Upper Worli" ,"400018" , mumbai));


    }

//    @Test
//    public void test1() throws UserNotRegisterVehicleException {
//        Vehicle vehicle2 = adminService.registerVehicle(new Vehicle("SUVjet", "04MP0998", "RED", "https://www.google.com/url?sa=&143fsh=dfs" , Admin1 , suv, petrol, powai));
//        Assertions.assertThrows( 1 , ()-> System.out.println(vehicle2.getAvailabilityStatus()));
//    }

    @Test
    public void test2(){
        //Vehicle vehicle3 = adminService.registerVehicle(new Vehicle("hatchbackRocket", "99TL7503", "orange", "https://www.google.com/url?sa=12#19ij=u%" , vamsiUsers , hatchback, petrol, worli));
        Assertions.assertThrows(UserNotRegisterVehicleException.class , ()-> adminService.registerVehicle(new Vehicle("hatchbackRocket", "99TL7503", "orange", "https://www.google.com/url?sa=12#19ij=u%" , vamsiUsers , hatchback, petrol, worli)));

    }

    @AfterEach
    public void deleteBooking() {
        roleDAO.deleteAll();
        usersDAO.deleteAll();
        locationDAO.deleteAll();
        vehicleSubcategoryDAO.deleteAll();
        fuelTypeDAO.deleteAll();
        cityDAO.deleteAll();
    }




}
