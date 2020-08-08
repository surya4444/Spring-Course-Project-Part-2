package com.upgrad.HireWheels.controller;

import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.response.CustomResponse;
import com.upgrad.HireWheels.dto.LoginDTO;
import com.upgrad.HireWheels.dto.UsersDTO;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.APIException;
import com.upgrad.HireWheels.services.UserService;
import com.upgrad.HireWheels.utills.DTOEntityConverter;
import com.upgrad.HireWheels.utills.EntityDTOConverter;
import com.upgrad.HireWheels.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    UserValidator userValidator;

    @Autowired
    UsersDAO usersDAO;

    @PostMapping(value = "/users" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newUser(@RequestBody UsersDTO usersDTO) throws APIException {
        ResponseEntity responseEntity = null;
        if (usersDTO.getFirstName() == null || (usersDTO.getFirstName()).length() <= 0){
            CustomResponse response = new CustomResponse(new Date(), "FirstName cannot be null or empty", 400);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if (usersDTO.getPassword() == null || (usersDTO.getPassword()).length() <= 5){
            CustomResponse response = new CustomResponse(new Date(), "Password cannot be null or less than 5 characters", 400);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if ((usersDTO.getMobileNo()).length() != 10 ){
            CustomResponse response = new CustomResponse(new Date(), "Mobile Number cannot be null or empty and must be 10 digits", 400);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        Users users = userValidator.createUserForDTO(new Users(usersDTO.getEmail() , usersDTO.getMobileNo() ));
        if (users == null){
            CustomResponse response = new CustomResponse(new Date(), "Mobile Number / Email already exists", 400);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        try {
            Users newUsers = dtoEntityConverter.convertToUsersEntity(usersDTO);
            Users storeUser = userService.acceptUsersDetails(newUsers);
            UsersDTO responseUserDTO = entityDTOConverter.convertToUsersDTO(storeUser);
            CustomResponse response = new CustomResponse(new Date(), "User Successfully Signed Up", 200);
            responseEntity = new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping(value = "users/access_token" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity LogIn(@RequestBody LoginDTO loginDTO) throws APIException {
        ResponseEntity responseEntity = null;
        userValidator.validateuserLogin(loginDTO);

        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        Users savedCustomer = usersDAO.checkEmailId(email);

        if (savedCustomer == null){
            CustomResponse response = new CustomResponse(new Date(), "User Not Registered", 404);
            responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        if(!savedCustomer.getPassword().equals(password)){
            CustomResponse response = new CustomResponse(new Date(), "Invalid Credentials", 404);
            responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        UsersDTO savedCustomerDTO = entityDTOConverter.convertToUsersDTO(savedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
    }

}
