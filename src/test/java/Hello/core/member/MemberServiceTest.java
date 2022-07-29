package Hello.core.member;

import Hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    // test 실행 전에 실행되는 코드
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join(){
        //given 이러한게 주어졌을 때,
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then 이렇게 된다.
        //Assertions asserj.core.api를 사용
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
