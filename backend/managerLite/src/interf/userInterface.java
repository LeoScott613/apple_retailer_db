package interf;

public class userInterface {
    public static void login() {
        System.out.println("*********���¼*********");
    }

    public static void loginFailed() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Login Failed");
    }
    public static void welcome() {
        String tableList = "warehouse admin raw\nproduct supplier log";
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("------------��ӭ------------");
        System.out.println("���ݿ����е����ݱ�");
        System.out.println(tableList);
        System.out.println("\n֧�ֵĲ�����select, insert, update");
        System.out.println("----------------------------");
    }

    public static void operationHint() {
        System.out.println("���������\n(��ѯ�����룬���£�ɾ��)");
    }

    /**
     * ��ʾ�����������ʾ��
     */
    public static void tableNameHint() {

        System.out.println("������Ҫ�����ı�֧����Ӣ��(����Ҫ�ж���ո�)��");
    }

    /**
     * ��ʾselect���ܵ���ʾ��
     */
    public static void selectHint() {

        System.out.println("��������Ҫ��ѯ�ı�����������������߱��ԭ����");
    }

    /**
     * ��ʾinsert���ܵ���ʾ��
     */
    public static void insertHint() {

        System.out.println("����������������, �س��ָ���");
    }

    public static void end() {
        System.out.println("*�������*");
    }
}
