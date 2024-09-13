package pl.sages.training.restassured.framework.commons;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String getBaseUri() {
        return dotenv.get("API_URL");
//        return "https://api.breathtaking.website";
    }

    public static boolean getIsApiLoggingEnabled() {
        return "true".equalsIgnoreCase(dotenv.get("API_LOGGING"));
//        return true;
    }

    public static String getAdminLogin() {
        return dotenv.get("API_LOGIN");
//        return "admin";
    }

    public static String getAdminPassword() {
        return  dotenv.get("API_PASSWORD");
//        return "admin1";
    }

//    public static boolean getIsRequestLoggingEnabled() {
//        return true;
//    }
//
//    public static boolean getIsResponseLoggingEnabled() {
//        return true;
//    }

}
