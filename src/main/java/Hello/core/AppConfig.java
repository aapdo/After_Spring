package Hello.core;

import Hello.core.discount.DiscountPolicy;
import Hello.core.discount.FixDiscountPolicy;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemberService;
import Hello.core.member.MemberServiceImpl;
import Hello.core.member.MemoryMemberRepository;
import Hello.core.order.OrderService;
import Hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * AppConfig가 어떤 객체를 생성할지 정해줌. 원래는 Impl이 선택했었음.
     * 이로써 DIP완성.
     * Impl 입장에서는 의존관계를 외부에서 주입해주는 것과 같다고 해서
     * DI(Dependency Injection)이라고 한다.
     * 정적인 클래스 의존 관계와 동적인 객체 의존 관계를 구분해야한다.
     * 예를 들어 OrderServiceImpl은 MemberRepository, DiscountPolicy 를 의존한다.
     *         반면 Rate, Fix, Memory 는 동적으로 인스턴스 관계가 만들어진다.
     * 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서
     * 클라이언트와 서버의 실제 의존 관계가 만들어 지는 것을 의존관계 주입이라고 한다.
     */

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository() new FixDiscountPolicy()
    //Memory가 두번 호출되는데 싱글톤이 깨질까?
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }//역할과

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }//구현을 나누어 시인성이 좋아진다.

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //IoC : 제어의 역전 프로그래머가 아닌 AppConfig가 어떤 객체를 생성하고 구현 클래스를 선택할지 정한다.
        //제어가 역전되었다고 해서 제어의 역전이라고함.
        //제어권을 코드가 가져가면 프레임워크, 제어의 흐름을 프로그래머가 가지면 라이브러리라고 한다.
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }


}
