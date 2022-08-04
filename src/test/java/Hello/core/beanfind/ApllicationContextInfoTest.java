package Hello.core.beanfind;

import Hello.core.AppConfig;
import Hello.core.AutoAppConfig;
import Hello.core.member.MemberService;
import Hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApllicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //이렇게 한 이유는 반환 타입을 MemberService로 했지만 인스턴스 타입을 보고 결정하기 때문에 구현체로 해도 된다.
        //물론 안 좋긴 하다. 왜냐면 역할과 구현을 분리해야되기 때문에 인터페이스로 하는게 좋긴하다.
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX() {
        //MemberService xxxx = ac.getBean("xxxx", MemberService.class);

        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
        //이건 무조건 에러가 떠야 테스트가 성공함.
    }


}
