package core.springbasic.common;

public class UUIDContext {

  private static final ThreadLocal<String> uuidHolder = new ThreadLocal<>();

  public static String getUUID() {
    return uuidHolder.get();
  }

  public static void setUUID(String uuid) {
    uuidHolder.set(uuid);
  }

  public static void clear() {
    uuidHolder.remove();
  }

}
