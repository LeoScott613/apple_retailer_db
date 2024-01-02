package func;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;
import java.sql.Types.*;
import java.util.ArrayList;

import interf.userInterface;

public class queryConstructor {
    private String tableName;

    public queryConstructor(String tableName) {
        this.tableName = tableName;
    }

    public String selectQuery() {
        tableName = translate(tableName);
        return String.format("SELECT * FROM %s;", tableName);
    }

    public String insertQuery(ResultSetMetaData metaData) {
        //准备获取输入
        userInterface.insertHint();
        Scanner in = new Scanner(System.in);
        StringBuilder resultBuilder = new StringBuilder("INSERT INTO ");
        resultBuilder.append(tableName).append(" VALUES (");

        //根据元数据循环获取输入并构造结果
        try {
            int columnLength = metaData.getColumnCount();
            for (int i = 0; i < columnLength; i++) {
                if (metaData.getColumnType(i + 1) != Types.INTEGER) {
                    //如果不是数字类型的，都要在插入数据前后用引号包围
                    resultBuilder.append("'").append(in.nextLine()).append("'");
                } else
                    resultBuilder.append(in.nextLine());
                if (i < columnLength - 1) {
                    resultBuilder.append(", ");
                }
            }
            resultBuilder.append(");");
        } catch (SQLException e) {
            System.out.println("insert语句构造器中的SQL操作失败");
            e.printStackTrace();
        }

        in.close();
        return resultBuilder.toString();
    }

    /**
     * update语句构造器
     *
     * @param metaData 相关表的元数据
     * @return SQL语句
     */
    public String updateQuery(ResultSetMetaData metaData) throws SQLException {
        //表头存储器
        List<String> column = new ArrayList<>();

        //显示表头
        System.out.println("该表的属性: " + tableName);
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            column.add(metaData.getColumnLabel(i));
            System.out.print(metaData.getColumnLabel(i) + "\t");
        }

        Scanner in = new Scanner(System.in);
        System.out.println("输入想改的属性名");
        String columnName = in.nextLine();
        System.out.println("输入更改值");
        String columnValue = in.nextLine();

        //根据属性名找到待更新的列号
        int colNo = 0;
        for (String col : column) {
            colNo++;
            if (columnName == col)
                break;
        }

        if(metaData.getColumnType(colNo)==Types.INTEGER)
            return String.format("update %s set %s=%s",tableName,columnName,columnValue);
        else
            return String.format("update %s set %s='%s'",tableName,columnName,columnValue);
    }

    public static String deleteQuery() {
        return "";
    }

    private void clean() {
        tableName = null;
    }

    /**
     * 将中文表名翻译成英文
     *
     * @param tableName 待翻译的表名
     * @return 英文结果，如果不是中文表名输入就返回原文
     */
    private static String translate(String tableName) {
        switch (tableName) {
            case "仓库" -> tableName = "warehouse";
            case "管理员" -> tableName = "admin";
            case "原料" -> tableName = "raw";
            case "产品" -> tableName = "product";
            case "账号" -> tableName = "account";
            case "日志" -> tableName = "log";
            case "供应商" -> tableName = "supplier";
            default -> {
            }
        }
        return tableName;
    }
}
