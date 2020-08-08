package com.upgrad.HireWheels.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Users
{
    @Id
    @Column(name = "user_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name" , nullable = false )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column(nullable = false , columnDefinition = "VARCHAR2(50) check(length(password)>5)")
    private String password;

    @Column(nullable = false , unique = true)
    private String email;

    //@ElementCollection    //we can do this but in schema it is not provide to create separate phone number table
    @Column(name = "mobile_no", nullable = false , unique = true)
    private String mobileNumber;
//    private List<String> mobileNumber;

    @Column(name = "wallet_money" , columnDefinition = " NUMBER(10,2) DEFAULT(100000.00)")
    private double walletMoney;

    @OneToMany(mappedBy = "users")
    List<Booking> bookings;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<Vehicle> vehicles;

    @OneToMany(mappedBy = "users")
    List<Request> requests;

    @ManyToMany( mappedBy = "users" ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
//    @ManyToOne(fetch = FetchType.EAGER)
    //manytomany because one user may have both customer and admin
    //Example amazon employee have admin account to change or modified the
    //app and he also need customer account to buy products so i think manytomany
    List<Role> roles;

    public Users() {
    }

    public Users(String firstName , String lastName , String password , String email ,String mobileNumber, double walletMoney) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.walletMoney = walletMoney;
    }

    public Users(String email , String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String firstName, String lastName, String password, String email, String mobileNumber, double walletMoney, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.walletMoney = walletMoney;
        this.roles = roles;
    }

    public Users(String email) {
        this.email = email ;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", walletMoney=" + walletMoney +
                '}';
  }

}

//users
//        user_id	CHAR(5)	PRIMARY KEY
//        first_name	VARCHAR2(50)	NOT NULL
//        last_name	VARCHAR2(50)
//        password	VARCHAR2(50)	NOT NULL	CHECK = length> 5 characters
//        email	VARCHAR2(50)	NOT NULL	UNIQUE
//        mobile_no	CHAR(10)	NOT NULL	UNIQUE
//        wallet_money	NUMBER(10,2)	DEFAULT = 100000.00

