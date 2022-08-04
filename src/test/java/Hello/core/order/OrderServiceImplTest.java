package Hello.core.order;

import Hello.core.discount.RateDiscountPolicy;
import Hello.core.member.Grade;
import Hello.core.member.Member;
import Hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    @DisplayName("생성자 주입을 사용해야하는 이유")
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        //내가 createOrder를 테스트하고 싶을 때에도 discountpolicy memberRepository를 넣어줘야한다.
        //수정자를 사용하면 new OrderServiceImpl에 빨간줄이 안떠서 뭐가 문제인지 알기 힘들다.

        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}