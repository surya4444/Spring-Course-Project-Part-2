package com.upgrad.HireWheels.dao;

import com.upgrad.HireWheels.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("usersDAO")
public interface UsersDAO extends JpaRepository<Users, Integer> {

//    @Query("update Users u set u.wallet_money := amount where u.id := id")
//    void updateAmount(@Param("amount") double amount , @Param("id") double id);

    @Query("From Users u Where u.email = :email")
    Users checkEmailId(@Param("email") String email);

    @Query("From Users u Where u.mobileNumber = :mobileNumber")
    Users checkMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query("From Users u Where u.password = :password")
    Users checkPassword(@Param("password") String password);

    @Query("From Users u Where u.email = :email AND u.password = :password")
    Users getCustomerDetails(@Param("email") String email , @Param("password") String password);


}
