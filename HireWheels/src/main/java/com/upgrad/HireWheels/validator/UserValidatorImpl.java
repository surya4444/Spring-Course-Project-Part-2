package com.upgrad.HireWheels.validator;

import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.dto.LoginDTO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.APIException;
import com.upgrad.HireWheels.exception.UserAlreadyExistsException;
import com.upgrad.HireWheels.exception.UserNotFoundException;
import com.upgrad.HireWheels.services.UserService;
import com.upgrad.HireWheels.utills.DTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator{

    @Autowired
    UserService userService;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    @Autowired
    UsersDAO usersDAO;


    @Override
    public Users createUserForDTO(Users users) throws APIException {
        Users user = users;
        Users emailId = usersDAO.checkEmailId(users.getEmail());
        Users mobileNumber = usersDAO.checkMobileNumber(users.getMobileNumber());
        if(emailId != null)
            user = null;
        if(mobileNumber != null)
            user = null;
        return user;
    }

    @Override
    public void validateuserLogin(LoginDTO users) throws APIException {
        if (users.getEmail() == null || users.getEmail().length() <= 0)
            throw new APIException("Invalid username");
        if(users.getPassword() == null || users.getPassword().length() <= 0 )
            throw new APIException("Invalid password");
    }

}
