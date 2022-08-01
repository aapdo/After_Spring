package Hello.core.order;

import Hello.core.discount.DiscountPolicy;
import Hello.core.member.Member;
import Hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 할인 정책을 바꾸려면 위 코드를 주석처리하고 밑에 코드를 다시 써야함
     * OCP, DIP 원칙을 잘 준수한 것 같지만
     * DIP : OrderServiceImpl은 DiscountPolicy에 의존하면서 DIP를 지킨 것 같지만
     *       Fix- or Rate-에도 의존하고 있다. 의존하지 않는다는 것은 코드를 안 바꿔도 되는 것.
     * OCP : Fix- => Rate- 로 변경하는 것이 OCP 위반이다. 역할과 구현을 분리하면
     *       클라이언트를 안 바꿔도 가능해야함.
     * 밑과 같이 인터페이스만 적으면 두 개를 지킬 수 있다. 하지만 구현체가 없어서 코드를 실행할 수 없다.
     * 이 문제를 해결하려면 누군가 DiscountPoliy에 구현체를 주입해줘야 한다. 이게 바로 의존관계 주입..ㄷ
     private final MemberRepository memberRepository = new MemoryMemberRepository();
     private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     원래 위 코드처럼 했었음.
     * */
    //private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
