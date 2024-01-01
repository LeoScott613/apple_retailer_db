package interf;

public class userInterface {
    public static void login() {
        System.out.println("*********请登录*********");
    }
    public static void welcome() {
        String tableList = "warehouse admin raw\nproduct supplier account log";

        System.out.println("------------欢迎------------");
        System.out.println("数据库中有的数据表");
        System.out.println(tableList);
        System.out.println("\n支持的操作：select, insert");
        System.out.println("---------------------------");
        System.out.println("请先输入操作");
    }

    public static void tableNameHint() {
        /**
         * 显示输入表名的提示语
         */
        System.out.println("输入你要操作的表，支持中英文(但不要有多余空格)：");
    }
    public static void selectHint() {
        /**
         * 显示select功能的提示语
         */
        System.out.println("输入你想要查询的表名，表的中文名或者表的原名：");
    }

    public static void insertHint() {
        /**
         * 显示insert功能的提示语
         */
        System.out.println("输入你想插入的数据：");
    }

    public static void end() {
        System.out.println("*程序结束*");
    }
}
