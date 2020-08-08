package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.RoleDAO;
import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.entities.Role;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService{

    @Qualifier("roleDAO")
    @Autowired
    RoleDAO roleDAO;

    @Override
    public Role acceptRoleDetails(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public Role getRoleDetails(int id) throws UserNotFoundException {
        Role role = roleDAO.findById(id).orElseThrow(
                ()->  new UserNotFoundException("Role Not found for " +id));
        return role;
    }
}
