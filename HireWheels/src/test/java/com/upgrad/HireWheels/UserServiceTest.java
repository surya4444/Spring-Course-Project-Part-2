package com.upgrad.HireWheels;

import com.upgrad.HireWheels.dao.UsersDAO;
import com.upgrad.HireWheels.entities.Users;
import com.upgrad.HireWheels.exception.UserAlreadyExistsException;
import com.upgrad.HireWheels.exception.UserNotFoundException;
import com.upgrad.HireWheels.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:HireWheels.xml"})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Qualifier("usersDAO")
    @Autowired
    private UsersDAO usersDAO;

    static Users Admin;
    static int id;
    static Users raja;


    @BeforeEach
    public void dataInsert(){
        Admin = new Users( "Admin" , "Admin" , "admin@123" , "upgrad@gmail.com" , "9999999999" , 10000);
        id = usersDAO.save(Admin).getId();
    }

    //Below unit test for to check if already present mail try to store in table it throw "UserAlreadyExistsException" are not
    @Test
    public void checkEmailForSignUp() throws UserAlreadyExistsException {
        //Users london = userService.createUser(new Users( "london" , "usa" , "don@123" , "upgrad@gmail.com" , "9999899999" , 100));
        Assertions.assertThrows(UserAlreadyExistsException.class , () -> userService.createUser(new Users( "london" , "usa" , "don@123" , "upgrad@gmail.com" , "9999899999" , 100)));
    }

    @Test
    public void checkPhoneNumberForSignUp() throws UserAlreadyExistsException {
        //Users london = userService.createUser(new Users( "london" , "usa" , "don@123" , "upgrad123@gmail.com" , "9999999999" , 100));
        Assertions.assertThrows(UserAlreadyExistsException.class , () -> userService.createUser(new Users( "london" , "usa" , "don@123" , "upgrad123@gmail.com" , "9999999999" , 100)));
    }

    //correct for signIn
//    @Test
//    public Users checkIfBothCorrect() throws UserAlreadyExistsException {
//        //raja = userService.createUser(new Users( "raja" , "" , "rajaTheGreat" , "don123@gmail.com" , "1234567890" , 10000.23));
//        //System.out.println(raja);
//        Assertions.assertTrue(
//                (BooleanSupplier) (raja = new Users( "raja" , "" , "rajaTheGreat" , "don123@gmail.com" , "1234567890" , 10000.23))
//        );
//        return usersDAO.save(raja);
//    }

    @Test
    public void checkEmailForLogIn() throws UserNotFoundException {
        //Users raja = userService.getUser(new Users("don@gmail.com","rajaTheGreat"));
        Assertions.assertThrows(UserNotFoundException.class ,()-> userService.getUser(new Users("don@gmail.com","rajaTheGreat")));
    }

    @Test
    public void checkPasswordForLogIn() throws UserNotFoundException {
        //Users raja = userService.getUser(new Users("don123@gmail.com","rajaGreat"));
        Assertions.assertThrows(UserNotFoundException.class ,()-> userService.getUser(new Users("don123@gmail.com","rajaGreat")));
    }

    @AfterEach
    public void dataDelete(){
        usersDAO.deleteById(id);
    }

}
