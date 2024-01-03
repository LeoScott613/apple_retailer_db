package func;

import interf.userInterface;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class queryConstructor {
    private String tableName;

    public queryConstructor(String tableName) {
        this.tableName = tableName;
    }

    public String selectQuery() {
        tableName = translate(tableName);
        return String.format("SELECT * FROM %s;", tableName);
    }

    public String insertQuery(ResultSetMetaData metaData) throws SQLException{
        System.out.print("\033[H\033[2J");
        System.out.flush();

        //��ӡ��ͷ
        System.out.println("����: " + tableName);
        int columnCount = metaData.getColumnCount();
        userInterface.insertHint();
        for (int i = 1; i <= columnCount; i++) {
//                System.out.println(metaData.getColumnLabel(i) + " (" + metaData.getColumnTypeName(i) + ")");
            System.out.print(metaData.getColumnLabel(i) + "\t");
        }
        System.out.println();

        //׼����ȡ����
        Scanner in = new Scanner(System.in);
        StringBuilder resultBuilder = new StringBuilder("INSERT INTO ");
        resultBuilder.append(tableName).append(" VALUES (");

        //����Ԫ����ѭ����ȡ���벢������
        try {
            int columnLength = metaData.getColumnCount();
            for (int i = 0; i < columnLength; i++) {
                if (metaData.getColumnType(i + 1) != Types.INTEGER) {
                    //��������������͵ģ���Ҫ�ڲ�������ǰ�������Ű�Χ
                    resultBuilder.append("'").append(in.nextLine()).append("'");
                } else
                    resultBuilder.append(in.nextLine());
                if (i < columnLength - 1) {
                    resultBuilder.append(", ");
                }
            }
            resultBuilder.append(");");
        } catch (SQLException e) {
            System.out.println("insert��乹�����е�SQL����ʧ��");
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }

    /**
     * update��乹����
     *
     * @param metaData ��ر��Ԫ����
     * @return SQL���
     */
    public String updateQuery(ResultSetMetaData metaData) throws SQLException {
        //��ͷ�洢��
        List<String> column = new ArrayList<>();

        //��ʾ��ͷ
//        System.out.println("�ñ������: ");
        System.out.println("������ĵ�������");
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            column.add(metaData.getColumnLabel(i));
            System.out.print(metaData.getColumnLabel(i) + ", ");
        }
        System.out.println();

        Scanner in = new Scanner(System.in);
        String columnName = in.nextLine();
        System.out.println("�������ֵ");
        String columnValue = in.nextLine();
        System.out.println("��������е��������");
        String id = in.nextLine();
        if (columnName.isEmpty() | columnValue.isEmpty() | id.isEmpty())
            throw new SQLException();//��֤������

        //�����������ҵ������µ��к�
        int colNo = 0;
        for (String col : column) {
            colNo++;
            if (Objects.equals(columnName, col))
                break;
        }

        String firstColName = column.get(0);
        if (metaData.getColumnType(colNo) == Types.INTEGER)
            return String.format("update %s set %s=%s where %s=%s", tableName, columnName, columnValue, firstColName, id);
        else
            return String.format("update %s set %s='%s' where %s=%s", tableName, columnName, columnValue, firstColName, id);
    }

    public String deleteQuery(ResultSetMetaData metaData) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("������ɾ���ļ�¼id��");
        String rowid = in.nextLine();
        String idcolumn = metaData.getColumnLabel(1);

        return String.format("DELETE FROM %s WHERE %s=%s",tableName,idcolumn,rowid);
    }

    public void clean() {
        tableName = null;
    }

    /**
     * �����ı��������Ӣ��
     *
     * @param tableName ������ı���
     * @return Ӣ�Ľ��������������ı�������ͷ���ԭ��
     */
    private static String translate(String tableName) {
        switch (tableName) {
            case "�ֿ�" -> tableName = "warehouse";
            case "����Ա" -> tableName = "admin";
            case "ԭ��" -> tableName = "raw";
            case "��Ʒ" -> tableName = "product";
            case "�˺�" -> tableName = "account";
            case "��־" -> tableName = "log";
            case "��Ӧ��" -> tableName = "supplier";
            default -> {
            }
        }
        return tableName;
    }
}
