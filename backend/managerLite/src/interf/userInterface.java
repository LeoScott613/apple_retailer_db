package interf;

public class userInterface {
    public static void login() {
        System.out.println("*********请登录*********");
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
        System.out.println("------------欢迎------------");
        System.out.println("数据库中有的数据表");
        System.out.println(tableList);
        System.out.println("\n支持的操作：select, insert, update, delete");
        System.out.println("----------------------------");
    }

    public static void operationHint() {
        System.out.println("请输入操作\n(查询select，插入insert，更新update，删除delete)");
    }

    /**
     * 显示输入表名的提示语
     */
    public static void tableNameHint() {

        System.out.println("输入你要操作的表，支持中英文(但不要有多余空格)：");
    }

    /**
     * 显示select功能的提示语
     */
    public static void selectHint() {

        System.out.println("输入你想要查询的表名，表的中文名或者表的原名：");
    }

    /**
     * 显示insert功能的提示语
     */
    public static void insertHint() {

        System.out.println("输入你想插入的数据, 回车分隔：");
    }

    public static void end() {
        System.out.println("*程序结束*");
    }
}
