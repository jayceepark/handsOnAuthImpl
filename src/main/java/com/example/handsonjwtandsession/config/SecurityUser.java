package com.example.handsonjwtandsession.config;

import com.example.handsonjwtandsession.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SecurityUser extends User implements Serializable {

    private Member member;

    public SecurityUser(Member member) {
        super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
        this.member = member;
    }
}
