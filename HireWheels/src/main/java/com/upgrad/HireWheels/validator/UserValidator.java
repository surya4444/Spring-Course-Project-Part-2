package com.upgrad.HireWheels.validator;

import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.dto.LoginDTO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.APIException;
import com.upgrad.HireWheels.exception.UserAlreadyExistsException;
import com.upgrad.HireWheels.exception.UserNotFoundException;

public interface UserValidator {
    public Users createUserForDTO(Users users) throws APIException;
    public void validateuserLogin(LoginDTO user) throws APIException;
}
