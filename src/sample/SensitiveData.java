package sample;

public class SensitiveData {
    private static final String DATABASE_URL = "test";
    private static final String DATABASE_USERNAME = "test";
    private static final String DATABASE_PASSWORD = "test";

    /**
     * GETTER METHODS
     */
    static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    static String getDatabaseUsername() {
        return DATABASE_USERNAME;
    }

    static String getDatabasePassword() { return DATABASE_PASSWORD; }
}
