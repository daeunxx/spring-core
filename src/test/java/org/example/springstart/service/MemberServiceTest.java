package org.example.springstart.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.example.springstart.domain.Member;
import org.example.springstart.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void afterEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
    memberRepository.clearStore();
  }

  @Test
  void join() {
    //given
    Member member = new Member();
    member.setName("daeun");

    //when
    Long saveId = memberService.join(member);

    //then
    Member findMember = memberService.findMember(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void exceptJoin() {
    //given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> memberService.join(member2));

    //then
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @Test
  void findMembers() {
  }

  @Test
  void findMember() {
  }
}