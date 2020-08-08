package com.upgrad.HireWheels.dao;

import com.upgrad.HireWheels.entities.Booking;
import com.upgrad.HireWheels.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("bookingDAO")
public interface BookingDAO extends JpaRepository<Booking, Integer> {

    @Query("From Users u Where u.id = :id")
    Users detailsOfId(@Param("id") int id);

//    @Query("update Users u set u.wallet_money := amount where u.id := id")
//    int updateAmount(@Param("amount") double amount , @Param("id") int id);
}
