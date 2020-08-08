package com.upgrad.HireWheels.dao;

import com.upgrad.HireWheels.entities.Role;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("vehicleDAO")
public interface VehicleDAO extends JpaRepository<Vehicle, Integer> {

//    @Query("From Users u Where u.id = :user")
//    Users needUserRoleId(@Param("user") int user);

//    @Query("From Role r Where r.roleName =:role")
//    Role needRoleId(@Param("role") String role);

//    @Query("From Vehicle v Where v.user =:id")
//    List<Vehicle> vehiclesByUserId(@Param("id") int id);




}
