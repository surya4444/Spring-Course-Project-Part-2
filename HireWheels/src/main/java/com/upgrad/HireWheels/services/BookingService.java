package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.InsufficientBalanceException;

public interface BookingService {

    public Booking addBooking(Booking booking) throws InsufficientBalanceException;
    public Booking acceptBookingDetials(Booking booking);
    //public Users updateAmount(Users users);
}
