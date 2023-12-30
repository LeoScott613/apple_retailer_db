package func;
import java.util.Scanner;
import interf.userInterface;
public class queryConstructor {
    public static String selectQuery() {
        Scanner in = new Scanner(System.in);
        userInterface.selectHint();
        String tableName = in.next();
        switch (tableName) {
            case "仓库":
                tableName = "warehouse";
                break;
            case "管理员":
                tableName = "admin";
                break;
            case "原料":
                tableName = "raw";
                break;
            case "产品":
                tableName = "product";
                break;
            case "账号":
                tableName = "account";
                break;
            case "日志":
                tableName = "log";
                break;
            case "供应商":
                tableName = "supplier";
                break;
            default:
                break;
        }
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
