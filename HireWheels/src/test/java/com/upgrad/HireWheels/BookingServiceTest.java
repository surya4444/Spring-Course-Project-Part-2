package com.upgrad.HireWheels;

import com.upgrad.HireWheels.dao.BookingDAO;
import com.upgrad.HireWheels.dao.LocationDAO;
import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.dao.VehicleDAO;
import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Location;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import com.upgrad.HireWheels.exception.InsufficientBalanceException;
import com.upgrad.HireWheels.services.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:HireWheels.xml"})
public class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Autowired
    VehicleDAO vehicleDAO;

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    LocationDAO locationDAO;

    @Autowired
    BookingDAO bookingDAO;

    Users suryaUser;
    Vehicle vehicle1;
    Location worli;

    @BeforeEach
    public void dataInsert() {

        suryaUser = usersDAO.save(new Users( "surya" , "teja" , "surya@123" , "surya@gmail.com" , "1467011111" , 200));
        vehicle1 = vehicleDAO.save(new Vehicle( "dirtBike125CC" , "12AP1234" , "White" , "https://www.google.com/url?sa=i&url=https%" ,1));
        worli =  locationDAO.save(new Location("Worli" , "Dr E Moses Rd, Worli Naka, Upper Worli" ,"400018"));
    }

    @Test
    public void test(){
        //Booking booking1 = bookingService.addBooking(new Booking(new Date("31/07/2020") ,new Date("2/08/2020") , new Date("31/07/2020") , 500 , suryaUser ,vehicle1 , worli ));
        Assertions.assertThrows(InsufficientBalanceException.class , ()-> bookingService.addBooking(new Booking(new Date("31/07/2020") ,new Date("2/08/2020") , new Date("31/07/2020") , 500 , suryaUser ,vehicle1 , worli )));
    }

    @AfterEach
    public void deleteBooking() {
        vehicleDAO.deleteAll();
        usersDAO.deleteAll();
        locationDAO.deleteAll();
        bookingDAO.deleteAll();
    }

    

}
