package org.example.springstart.service;

import java.util.List;
import java.util.Optional;
import org.example.springstart.domain.Member;
import org.example.springstart.repository.MemberRepository;
import org.example.springstart.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final static MemberRepository memberRepository = new MemoryMemberRepository();

  /**
   * 회원 가입
   */
  public Long join(Member member) {
    //같은 이름이 있는 중복 회원 가입 불가
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  private static void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
            .ifPresent(m -> {
              throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
  }
}
