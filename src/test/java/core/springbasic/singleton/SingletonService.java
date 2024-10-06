package core.springbasic.singleton;

public class SingletonService {

  private static final SingletonService instance = new SingletonService();

  private SingletonService() {
  }

  public static SingletonService getInstance() {
    return instance;
  }

  public void login() {
    System.out.println("Singleton 객체 로직 호출₩");
  }
}
