package interf;

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


        //接受輸入，並根据输入建query语句
        Scanner in = new Scanner(System.in);
        String queryTable = in.nextLine();
        String selectQuery = String.format("SELECT * FROM %s", queryTable);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            metaData = resultSet.getMetaData();

            // 打印表头
            System.out.println("Table: " + queryTable);
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ": " + metaData.getColumnLabel(i) + " (" + metaData.getColumnTypeName(i) + ")");
            }


            while (resultSet.next()) {
//                int id = resultSet.getInt("wno");
////                String name = resultSet.getString("first_name");
////                System.out.println("ID: " + id + ", Name: " + name);
//                System.out.println(id);

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
            System.out.println("end");
        }
    }
}
