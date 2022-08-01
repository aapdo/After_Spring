package Hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceTestSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB: B가 10000원 주문
        int userBPrice = statefulService1.order("userA", 20000);

        //ThreadA: A가 주문 금액 조회
        System.out.println("price = " + userAPrice);

        //인스턴스 하나를 공유하기 때문에 마지막 주문이 20000이기 때문에 그 값이 할당되어 있다.
        assertThat(userAPrice).isNotSameAs(userBPrice);
    }

    @Configuration
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}