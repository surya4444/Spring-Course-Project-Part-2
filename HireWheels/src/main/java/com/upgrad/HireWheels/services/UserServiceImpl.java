package com.upgrad.HireWheels.services;

import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.UserAlreadyExistsException;
import com.upgrad.HireWheels.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Qualifier("usersDAO")
    @Autowired
    private UsersDAO usersDAO;

    @Override
    public Users acceptUsersDetails(Users users){
        return usersDAO.save(users);
    }

    @Override
    public Users getUsersDetails(int id) throws UserNotFoundException {
        Users users = usersDAO.findById(id).orElseThrow(
                ()->  new UserNotFoundException("User Not found for " +id));
        return users;
    }

    @Override
   public Users createUser(Users users) throws UserAlreadyExistsException {

//        List<Users> user = usersDAO.findAll();
//
//        List<String> email = new ArrayList<>();
//        user.forEach(customer -> email.add(customer.getEmail()));//Fetch only email and add add to email list
//
//        List<String> phone = new ArrayList<>();
//        user.forEach(customer -> phone.add(customer.getMobileNumber()));//Fetch only phone numbers and add to phone list
//
//        if(email.contains(users.getEmail()))
//            throw new UserAlreadyExistsException("Email Already Exists");
//        if(phone.contains(users.getMobileNumber()))
//            throw new UserAlreadyExistsException("Mobile Number Already Exists");

//Above code also work fine but it retrive all email and phone number and store in collection of varibles and check with the user mail and phone number
//It is time taken process so i write below code it check directly in table if match retrive all data of respective email and password

        Users emailId = usersDAO.checkEmailId(users.getEmail());
        Users mobileNumber = usersDAO.checkMobileNumber(users.getMobileNumber());

        if(emailId != null)
            throw new UserAlreadyExistsException("Email Already Exists");
        if(mobileNumber != null)
            throw new UserAlreadyExistsException("Mobile Number Already Exists");
        return usersDAO.save(users);
    }

    @Override
    public Users getUser(Users users) throws UserNotFoundException {

//        List<Users> user = usersDAO.findAll();
//
//        List<String> email = new ArrayList<>();
//        user.forEach(customer -> email.add(customer.getEmail()));//Fetch only email and add add to email list
//
//        List<String> password = new ArrayList<>();
//        user.forEach(customer -> password.add(customer.getPassword()));//Fetch only password and add add to email list
//
//        if(!email.contains(users.getEmail()))
//            throw new UserNotFoundException("User not Registered");
//        if(!password.contains(users.getPassword()))
//            throw new UserNotFoundException("Unauthorized User");

//Above code also work fine but it retrive all email and password and store in collection of varibles and check with the user mail and phone number
//It is time taken process so i write below code it check directly in table if match retrive all data of respective email and password

        Users emailId = usersDAO.checkEmailId(users.getEmail());

        if(emailId == null)
            throw new UserNotFoundException("User not Registered");
        if( !(emailId.getPassword()).equals(users.getPassword()) )
            throw new UserNotFoundException("Unauthorized User");
        //Users user = usersDAO.getCustomerDetails(users.getEmail() , users.getPassword());
        return emailId;
    }

}


