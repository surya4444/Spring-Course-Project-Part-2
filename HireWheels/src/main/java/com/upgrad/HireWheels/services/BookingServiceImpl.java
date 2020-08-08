package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.BookingDAO;
import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "bookingService")
public class BookingServiceImpl implements BookingService{

    @Qualifier("usersDAO")
    @Autowired
    UsersDAO usersDAO;

    @Qualifier("bookingDAO")
    @Autowired
    BookingDAO bookingDAO;

    @Override
    public Booking acceptBookingDetials(Booking booking) {
        return bookingDAO.save(booking);
    }

    @Override
    public Booking addBooking(Booking booking) throws InsufficientBalanceException {

        Users details = bookingDAO.detailsOfId(booking.getUsers().getId());
        double amount = details.getWalletMoney();

        double bookingAmount = booking.getAmount();

        if(bookingAmount > amount)
            throw new InsufficientBalanceException("Insufficient Balance. Please Check With Admin");
        booking.getUsers().setWalletMoney(amount-bookingAmount);//This line for update wallet amount if booking amount is less than wallet amount
        usersDAO.save(booking.getUsers());//This line for save
        return bookingDAO.save(booking);




    }


}
