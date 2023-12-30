package interf;

import func.queryConstructor;

import java.sql.*;
import java.util.Scanner;

public class controller {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/apple";
        String username = "root";
        String password = "220610";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;


        userInterface.welcome();
        //接受輸入，並根据输入建query语句
        Scanner in = new Scanner(System.in);
//        String queryTable = in.nextLine();
//        String selectQuery = String.format("SELECT * FROM %s", queryTable);
        String midQuery = new String();
        String tableName = new String();

        String operation = in.nextLine();
        switch (operation) {
            case "select":
            case "select ":
                userInterface.selectHint();
                tableName = in.nextLine();
                switch (tableName) {
                    case "仓库":
                        tableName = "warehouse";
                    case "管理员":
                        tableName = "admin";
                    case "原料":
                        tableName = "raw";
                    case "产品":
                        tableName = "product";
                    case "账号":
                        tableName = "account";
                    case "日志":
                        tableName = "log";
                    case "供应商":
                        tableName = "supplier";
                }
                midQuery = queryConstructor.selectQuery(tableName);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(midQuery);
            metaData = resultSet.getMetaData();

            // 打印表头
            System.out.println("Table: " + tableName);
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
//                System.out.println(metaData.getColumnLabel(i) + " (" + metaData.getColumnTypeName(i) + ")");
                System.out.print(metaData.getColumnLabel(i) + "\t\t");
            }
            System.out.println();


            while (resultSet.next()) {
                // 通用输出模式
                for (int i = 1; i <= columnCount; i++) {
                    // 获取不同类型的数据
                    Object value = resultSet.getObject(i);
                    System.out.print(value + "\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL操作失败");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            in.close();
            System.out.println("程序结束");
        }
    }
}
