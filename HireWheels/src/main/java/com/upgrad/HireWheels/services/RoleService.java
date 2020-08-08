package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.Role;
import com.upgrad.HireWheels.exception.UserNotFoundException;

public interface RoleService {

    public Role acceptRoleDetails(Role role);
    public Role getRoleDetails(int id) throws UserNotFoundException;
}
