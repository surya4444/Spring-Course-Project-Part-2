package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.UserAlreadyExistsException;
import com.upgrad.HireWheels.exception.UserNotFoundException;

public interface UserService {

    public Users createUser(Users users) throws UserAlreadyExistsException;
    public Users getUser(Users users) throws UserNotFoundException;
    public Users acceptUsersDetails(Users users);
    public Users getUsersDetails(int id) throws UserNotFoundException;

}
