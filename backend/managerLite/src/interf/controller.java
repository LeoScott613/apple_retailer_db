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

        while (true) {
            try {
                // �����ݿ����Ӻ�statement�����ʼ���ã��Ա�ʵ��CRUD�ĸ�������
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();

                //��¼����
                Scanner in = new Scanner(System.in);
                userInterface.login();
                System.out.print("�˺�:");
                String account = in.nextLine();
                System.out.println("����:");
                String accpass = in.nextLine();

                //����û��Ƿ�ƥ��
                ResultSet auth = statement.executeQuery(
                        String.format("SELECT * from account where (acc='%s' and pass='%s')", account, accpass)
                );
                if (!auth.next()) {
                    userInterface.loginFailed();
                    exit(1);
                }

                userInterface.welcome();

                //����Ҫ�����ı�
                String midQuery;
                String tableName;
                userInterface.tableNameHint();
                tableName = in.nextLine();

                //���������������SQL��乹����
                userInterface.operationHint();
                String operation = in.nextLine();
                queryConstructor qConstructor = new queryConstructor(tableName);

                switch (operation) {
                    case "select", "select ", "��ѯ", "��ѯ " -> {
                        midQuery = qConstructor.selectQuery();

                        //��ʼִ�в�ѯ
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //1.��ȡ��ͷ
                        System.out.println("Table: " + tableName);
                        int columnCount = metaData.getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
//                System.out.println(metaData.getColumnLabel(i) + " (" + metaData.getColumnTypeName(i) + ")");
                            System.out.print(metaData.getColumnLabel(i) + "\t\t");
                        }
                        System.out.println();
                        //2.���ȫ�����ݵ�ͨ�÷��������ݱ��Ԫ���ݽ���ѭ�����������������ֻ����Objװ��
                        while (resultSet.next()) {
                            // ͨ�����ģʽ
                            for (int i = 1; i <= columnCount; i++) {
                                // ��ȡ��ͬ���͵�����
                                Object value = resultSet.getObject(i);
                                System.out.print(value + "\t\t");
                            }
                            System.out.println();
                        }
                        System.out.println("��ѯ���");
                        in.nextLine();
                    }
                    //��ѯ����
                    case "insert", "insert ", "����", "���� " -> {
                        //1.��ȡ���Ԫ����
                        midQuery = qConstructor.selectQuery();
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //2.�����Ԫ���ݵݽ���SQL������
                        midQuery = qConstructor.insertQuery(metaData);
                        statement.execute(midQuery);
                        System.out.println("�������");
                        in.nextLine();
                    }
                    case "update", "update ", "����", "���� " -> {
                        //1.��ȡ���Ԫ����
                        midQuery = qConstructor.selectQuery();
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //2.��ȡ��ͷ
                        System.out.println("Table: " + tableName);
                        int columnCount2 = metaData.getColumnCount();
                        for (int i = 1; i <= columnCount2; i++) {
//                System.out.println(metaData.getColumnLabel(i) + " (" + metaData.getColumnTypeName(i) + ")");
                            System.out.print(metaData.getColumnLabel(i) + "\t\t");
                        }
                        System.out.println();
                        //3.���ȫ������
                        while (resultSet.next()) {
                            // ͨ�����ģʽ
                            for (int i = 1; i <= columnCount2; i++) {
                                // ��ȡ��ͬ���͵�����
                                Object value = resultSet.getObject(i);
                                System.out.print(value + "\t\t");
                            }
                            System.out.println();
                        }
                        //4.�����Ԫ���ݵݽ���SQL������
                        midQuery = qConstructor.updateQuery(metaData);
                        statement.execute(midQuery);
                        System.out.println("�������");
                        in.nextLine();
                    }
                    case "delete", "delete ", "ɾ��", "ɾ�� " -> {
                        //1.��ȡ���Ԫ����
                        midQuery = qConstructor.selectQuery();
                        resultSet = statement.executeQuery(midQuery);
                        metaData = resultSet.getMetaData();
                        //2.�����Ԫ���ݵݽ���SQL������
                        midQuery = qConstructor.deleteQuery(metaData);
                        System.out.println(midQuery);
                    }
                    default -> throw new SQLException();
                }
                System.in.read(new byte[System.in.available()]);

            } catch (SQLException e) {
                System.out.println("SQL����ʧ�ܣ���������Ƿ����������");
            e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("��������ʧ��");
            } catch (IOException e) {
                System.out.println("������ˢ��ʧ��");
//            e.printStackTrace();
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
            }
            userInterface.end();
        }
    }
}
