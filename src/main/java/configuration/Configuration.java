package configuration;

public class Configuration {

    public static String iconPATH = "src/main/resources/MainIcon.png";
    public static String administratorName = "Administrator";
    public static String administratorPassword = "admin";

    private static String dbURL = "mysql://localhost/TinyLibrary";
    private static String dbUserName = "root";
    private static String dbPassword = "alert";
    private static String dbUseUnicode = "true";
    private static String dbEncoding = "UTF8";
    private static String dbUseSSL = "true";

    public static String DB_URL = "jdbc:" + dbURL +
            "?user=" + dbUserName +
            "&password=" + dbPassword +
            "&useUnicode=" + dbUseUnicode +
            "&characterEncoding=" + dbEncoding +
            "&useSSL=" + dbUseSSL;
    public static int maxItemSize = 100;

}
