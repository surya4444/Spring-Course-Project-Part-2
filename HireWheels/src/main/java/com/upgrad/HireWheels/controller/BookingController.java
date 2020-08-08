package com.upgrad.HireWheels.controller;

import com.upgrad.HireWheels.dao.BookingDAO;
import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.dto.BookingDTO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.dto.VehicleDTO;
import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.response.CustomResponse;
import com.upgrad.HireWheels.services.BookingService;
import com.upgrad.HireWheels.utills.DTOEntityConverter;
import com.upgrad.HireWheels.utills.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookingController {

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingDAO bookingDAO;

    @Autowired
    UsersDAO usersDAO;

    @PostMapping(value = "/bookings" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newBooking(@RequestBody BookingDTO bookingDTO) {
        ResponseEntity responseEntity = null;
        try {
            Booking newBooking = dtoEntityConverter.convertToBookingEntity(bookingDTO);
            Booking storeBooking = bookingService.acceptBookingDetials(newBooking);
            Users details = bookingDAO.detailsOfId(storeBooking.getUsers().getId());
            double amount = details.getWalletMoney();
            double bookingAmount = newBooking.getAmount();
            if(bookingAmount > amount){
                CustomResponse response = new CustomResponse(new Date(), "Insuffication amount", 400);
                responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            usersDAO.save(newBooking.getUsers());
            BookingDTO responseUserDTO = entityDTOConverter.convertToBookingDto(storeBooking);
            CustomResponse response = new CustomResponse(new Date(), "Booked Successfully", 200);
            responseEntity = new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
