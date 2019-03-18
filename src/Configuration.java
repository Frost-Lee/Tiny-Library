public class Configuration {

    static String iconURL = "/Users/Frost/Documents/Schoolwork/CS/Database System/Projects/Tiny Library/Resources/MainIcon.png";
    static String administratorName = "Administrator";
    static String administratorPassword = "admin";

    private static String dbURL = "mysql://localhost/TinyLibrary";
    private static String dbUserName = "root";
    private static String dbPassword = "alert";
    private static String dbUseUnicode = "true";
    private static String dbEncoding = "UTF8";
    private static String dbUseSSL = "true";

    static String DB_URL = "jdbc:" + dbURL +
            "?user=" + dbUserName +
            "&password=" + dbPassword +
            "&useUnicode=" + dbUseUnicode +
            "&characterEncoding=" + dbEncoding +
            "&useSSL=" + dbUseSSL;
    static int maxItemSize = 100;

}
