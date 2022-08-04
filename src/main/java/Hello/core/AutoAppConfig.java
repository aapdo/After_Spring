package Hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "Hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //@Configuration이 붙은 것도 스캔되기 때문에 빼줘야함. 안에 component가 있음.
        //보통 이렇게 하진 않지만 예제에서 config이 많아서 그럼.
)
//@Component 가 있는 곳을 찾아서 스캔함
public class AutoAppConfig {
}
