package Hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    //static으로 가지고 있기 때문에 클래스 레벨에 올라가서 딱 하나만 존재하게 됨.
    //클래스를 instance하지 않아도 static이기 때문에 자기 자신을 참조해서 넣어놓는다.
    //객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택했다.

    public static SingletonService getInstance(){
        return instance;
    }
    private SingletonService(){}// 이렇게 만들면 private 이기 때문에 밖에서 new가 막힘.

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
