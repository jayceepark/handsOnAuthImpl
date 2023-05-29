package com.example.handsonjwtandsession.Repository;

import com.example.handsonjwtandsession.domain.Member;
import com.example.handsonjwtandsession.domain.Role;
import com.example.handsonjwtandsession.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 사용자_등록(){

        Member memberAdmin = new Member();
        memberAdmin.setId("admin");
        memberAdmin.setEnabled(true);
        memberAdmin.setPassword(passwordEncoder.encode("admin"));
        memberAdmin.setDeptName("개발1팀");
        memberAdmin.setRole(Role.ROLE_ADMIN);
        memberRepository.save(memberAdmin);

        Member memberManager = new Member();
        memberManager.setId("manager");
        memberManager.setEnabled(true);
        memberManager.setPassword(passwordEncoder.encode("manager"));
        memberManager.setDeptName("개발2팀");
        memberManager.setRole(Role.ROLE_MANAGER);
        memberRepository.save(memberManager);

        Member member = new Member();
        member.setId("member");
        member.setEnabled(true);
        member.setPassword(passwordEncoder.encode("member"));
        member.setDeptName("개발3팀");
        member.setRole(Role.ROLE_MEMBER);
        memberRepository.save(member);
    }
}
