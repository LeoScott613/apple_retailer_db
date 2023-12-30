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

        try {
            // 将数据库连接和statement对象初始化好，以便实现CRUD的各个功能
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            switch (operation) {
                case "select":
                case "select ":
                    midQuery = queryConstructor.selectQuery();

                    //开始执行查询
                    resultSet = statement.executeQuery(midQuery);
                    metaData = resultSet.getMetaData();
                    //1.获取表头
                    System.out.println("Table: " + tableName);
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
//                System.out.println(metaData.getColumnLabel(i) + " (" + metaData.getColumnTypeName(i) + ")");
                        System.out.print(metaData.getColumnLabel(i) + "\t\t");
                    }
                    System.out.println();
                    //2.输出全表内容的通用方法：根据表格元数据进行循环输出，所有输出结果只是用Obj装载
                    while (resultSet.next()) {
                        // 通用输出模式
                        for (int i = 1; i <= columnCount; i++) {
                            // 获取不同类型的数据
                            Object value = resultSet.getObject(i);
                            System.out.print(value + "\t\t");
                        }
                        System.out.println();
                    }
                    //查询结束
                    break;
                case "insert":
                    midQuery = queryConstructor.insertQuery();

                    break;
                default:
                    throw new SQLException();
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
            userInterface.end();
        }
    }
}
