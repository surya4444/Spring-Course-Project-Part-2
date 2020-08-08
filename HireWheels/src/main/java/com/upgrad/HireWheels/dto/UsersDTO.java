package com.upgrad.HireWheels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UsersDTO {

    int userId;

    String firstName;

    String lastName;

    String email;

    String password;

    @JsonProperty("mobileNumber")
    String mobileNo;

    double walletMoney;

    Set<Integer> roleId;
}

