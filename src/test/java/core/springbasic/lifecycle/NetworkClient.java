package core.springbasic.lifecycle;

public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void init() {
    System.out.println("NetworkClient.afterPropertiesSet");
    connect();
    call("초기화 연결 메시지");
  }

  public void close() {
    System.out.println("NetworkClient.destroy");
    disconnect();
  }

  // 서비스 시작 시, 호출
  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + ", massage = " + message);
  }

  // 서비스 종료 시, 호출
  public void disconnect() {
    System.out.println("disconnect: " + url);
  }
}
