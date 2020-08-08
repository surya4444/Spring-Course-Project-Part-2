package com.upgrad.HireWheels.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Role
{
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role_name" , unique = true ,nullable = false)
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
//    @OneToMany(mappedBy = "roles" , fetch = FetchType.EAGER)
    List<Users> users;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
//role
//        role_id	CHAR(5)	PRIMARY KEY
//        role_name	VARCHAR2(50)	NOT NULL	UNIQUE
