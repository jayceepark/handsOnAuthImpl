package com.example.handsonjwtandsession.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="member")
public class Member{

    @Id
    private String id;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    private String deptName;

}
