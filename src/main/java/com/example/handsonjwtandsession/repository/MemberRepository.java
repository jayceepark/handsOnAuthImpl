package com.example.handsonjwtandsession.repository;

import com.example.handsonjwtandsession.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
