package Hello.core.beanDefinition;

import Hello.core.AppConfig;
import Hello.core.beanfind.ApplicationContextExtendsFindTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    //xml도 가능하다. 근데 사실 이 기능을 쓸일이 많이 없다
    //xml로 하면 조금 더 명확한 데이터들이 등록되어 있다.
    // java 코드를 사용하면 factory method를 사용하여 등록하게 된다. 하지만 xml은 바로 빈 컨테이너에 넣는다..

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinition = " + beanDefinition
                + " beanDefName = " + beanDefinitionName);
            }
        }
    }

}
