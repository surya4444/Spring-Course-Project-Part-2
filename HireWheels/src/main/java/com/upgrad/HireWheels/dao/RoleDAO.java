package com.upgrad.HireWheels.dao;

import com.upgrad.HireWheels.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleDAO")
public interface RoleDAO extends JpaRepository<Role, Integer> {
}
