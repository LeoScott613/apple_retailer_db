package interf;

import func.queryConstructor;

import java.io.IOException;
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
        ResultSet resultSet;
        ResultSetMetaData metaData;

        boolean firstRunFlag = true;
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                if (firstRunFlag) {
                    // 将数据库连接和statement对象初始化好，以便实现CRUD的各个功能
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(url, username, password);
                    statement = connection.createStatement();

                    //登录流程
                    userInterface.login();
                    System.out.print("账号:");
                    String account = in.nextLine();
                    System.out.println("密码:");
                    String accpass = in.nextLine();

                    //检查用户是否匹配
                    ResultSet auth = statement.executeQuery(
                            String.format("SELECT * from account where (acc='%s' and pass='%s')", account, accpass)
                    );
                    if (!auth.next()) {
                        userInterface.loginFailed();
                        exit(1);
                    }

                    firstRunFlag = false;
                }

                userInterface.welcome();

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
                    case "select", "select ", "查询", "查询 " -> {
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
                        System.out.println("查询完成");
                    }
                    //查询结束
                    case "insert", "insert ", "插入", "插入 " -> {
                        //1.获取表格元数据
                        midQuery = qConstructor.selectQuery();
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //2.将表格元数据递交给SQL构造器
                        midQuery = qConstructor.insertQuery(metaData);
                        statement.execute(midQuery);
                        System.out.println("插入完成");
                    }
                    case "update", "update ", "更新", "更新 " -> {
                        //1.获取表格元数据
                        midQuery = qConstructor.selectQuery();
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //2.获取表头
                        System.out.println("Table: " + tableName);
                        int columnCount2 = metaData.getColumnCount();
                        for (int i = 1; i <= columnCount2; i++) {
                            System.out.print(metaData.getColumnLabel(i) + "\t\t");
                        }
                        System.out.println();
                        //3.输出全表内容
                        while (resultSet.next()) {
                            // 通用输出模式
                            for (int i = 1; i <= columnCount2; i++) {
                                // 获取不同类型的数据
                                Object value = resultSet.getObject(i);
                                System.out.print(value + "\t\t");
                            }
                            System.out.println();
                        }
                        //4.将表格元数据递交给SQL构造器
                        midQuery = qConstructor.updateQuery(metaData);
                        statement.execute(midQuery);
                        System.out.println("更新完成");
                    }
                    case "delete", "delete ", "删除", "删除 " -> {
                        //1.获取表格元数据
                        midQuery = qConstructor.selectQuery();
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //2.获取表头
                        System.out.println("Table: " + tableName);
                        int columnCount2 = metaData.getColumnCount();
                        for (int i = 1; i <= columnCount2; i++) {
                            System.out.print(metaData.getColumnLabel(i) + "\t\t");
                        }
                        System.out.println();
                        //3.输出全表内容
                        while (resultSet.next()) {
                            // 通用输出模式
                            for (int i = 1; i <= columnCount2; i++) {
                                // 获取不同类型的数据
                                Object value = resultSet.getObject(i);
                                System.out.print(value + "\t\t");
                            }
                            System.out.println();
                        }
                        //2.将表格元数据递交给SQL构造器
                        midQuery = qConstructor.deleteQuery(metaData);
                        statement.execute(midQuery);
                        System.out.println("删除完成");
                    }
                    default -> throw new SQLException();
                }
                System.in.read(new byte[System.in.available()]);

            } catch (SQLException e) {
                System.out.println("SQL操作失败，请检查操作是否符合完整性");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("加载驱动失败");
            } catch (IOException e) {
                System.out.println("缓冲区刷新失败");
//            e.printStackTrace();
            } finally {
                in.nextLine();
            }
//            userInterface.end();
        }
    }
}
