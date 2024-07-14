package properties;

public class SystemProperties {

    public static String wdHost = System.getProperty("wdHost", "selenoid.autotests.cloud");
    public static String login = System.getProperty("login", "");
    public static String password = System.getProperty("password", "");
}