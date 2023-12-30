package func;

public class queryConstructor {
    public static String selectQuery(String tableName) {
        String result = String.format("SELECT * FROM %s",tableName);
        return result;
    }
    public static String selectQuery(String tableName,int limit) {
        String result = String.format("SELECT * FROM %s LIMIT %d",tableName,limit);
        return result;
    }
    public static String insertQuery() {
        return "";
    }
    public static String updateQuery() {
        return "";
    }
    public static String deleteQuery() {
        return "";
    }
}
