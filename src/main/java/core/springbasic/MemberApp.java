package core.springbasic;

import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;

public class MemberApp {

  public static void main(String[] args) {
    MemberService memberService = new MemberServiceImpl();
    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(member.getId());
    System.out.println("new Member = " + member.getName());
    System.out.println("find Member = " + findMember.getName());
  }
}
