package com.upgrad.HireWheels.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Request {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_comments")
    private String userComments;

    @Column(name = "admin_comments")
    private String adminComments;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    Users users;

    @ManyToOne
    Activity activity;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    //@JsonBackReference
    @JoinColumn(name = "request_status_id")
    RequestStatus requestStatus;

    @ManyToOne
    Vehicle vehicle;
}
//requests
//        request_id	CHAR(5)	PRIMARY KEY
//        user_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        activity_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        user_comments	VARCHAR2(50)
//        admin_comments	VARCHAR2(50)
//        request_status_id	CHAR(5)	NOT NULL	FOREIGN KEY
//        vehicle_id	CHAR(5)	FOREIGN KEY
