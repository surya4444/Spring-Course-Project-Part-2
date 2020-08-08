package com.upgrad.HireWheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "request_status")
public class RequestStatus {

    @Id
    @Column(name = "request_status_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "request_status_name" , nullable = false , unique = true)
    private String requestStatusName;

    @OneToMany(mappedBy = "requestStatus")
    List<Request> requests;
}
//request_status
//        request_status_id	CHAR(5)	PRIMARY KEY
//        request_status_name	VARCHAR2(50)	NOT NULL	UNIQUE
