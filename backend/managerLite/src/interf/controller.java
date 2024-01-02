package interf;

import func.queryConstructor;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class controller {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/apple";
        String username = "root";
        String password = "220610";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;


        try {
            // 将数据库连接和statement对象初始化好，以便实现CRUD的各个功能
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            //登录流程
            userInterface.login();
            Scanner in = new Scanner(System.in);
            System.out.print("账号:");
            String account = in.nextLine();
            System.out.println("密码:");
            String accpass = in.nextLine();

            //检查用户是否匹配
            ResultSet auth = statement.executeQuery(
                    String.format("SELECT * from account where (acc='%s' and pass='%s')", account, accpass)
            );
            if(!auth.next()) {
                userInterface.loginFailed();
                exit(1);
            }
            else {
                //登录成功
                userInterface.welcome();
            }

            //输入要操作的表
            String midQuery;
            String tableName;
            userInterface.tableNameHint();
            tableName = in.nextLine();


            //输入操作，并创建SQL语句构造器
            userInterface.operationHint();
            String operation = in.nextLine();
            queryConstructor qConstructor = new queryConstructor(tableName);

            switch (operation) {
                case "select":
                case "select ":
                    midQuery = qConstructor.selectQuery();

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
                case "insert ":
                    //1.获取表格元数据
                    midQuery = qConstructor.selectQuery();
                    resultSet = statement.executeQuery(midQuery);
                    metaData = resultSet.getMetaData();
                    //2.将表格元数据递交给SQL构造器
                    midQuery = qConstructor.insertQuery(metaData);
                    System.out.println(midQuery);//将插入语句直接回显，直到稳定了再去数据库中执行插入语句
                    break;
                case "update":
                case "update ":
                    //1.获取表格元数据
                    midQuery = qConstructor.selectQuery();
                    resultSet = statement.executeQuery(midQuery);
                    metaData = resultSet.getMetaData();
                    //2.将表格元数据递交给SQL构造器
                    midQuery = qConstructor.insertQuery(metaData);
                    System.out.println(midQuery);
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
//            in.close();
            userInterface.end();
        }
    }
}
