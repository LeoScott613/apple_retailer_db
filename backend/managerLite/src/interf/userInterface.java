package interf;

public class userInterface {
    public static final String tableList = "warehouse admin raw\nproduct supplier log";
    public static final String viewList = "product_warehouse raw_supplier \nraw_warehouse warehouse_admin";
    public static void login() {
        System.out.println("*********请登录*********");
    }

    public static void loginFailed() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Login Failed");
    }
    public static void welcome() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("--------------------欢迎@卢弘翔---------------------");
        System.out.println("数据库中有的数据表");
        System.out.println(tableList);
        System.out.println("数据库中有的视图");
        System.out.println(viewList);
        System.out.println("\n支持的操作：select, insert, update, delete\n");
        System.out.println("快捷操作");
        System.out.println("1. 查看仓库储量");
        System.out.println("2. 查看管理员列表");
        System.out.println("3. 查看原料列表");
        System.out.println("4. 查看成品列表");
        System.out.println("5. 查看某个成品的库存");
        System.out.println("6. 查看某个原料的库存");
        System.out.println("7. 查看某个仓库容量");
        System.out.println("----------------------------------------------------");
    }

    public static void operationHint() {
        System.out.println("请输入操作\n(查询select，插入insert，更新update，删除delete)");
    }

    /**
     * 显示输入表名的提示语
     */
    public static void tableNameHint() {

        System.out.println("输入你要操作的表(不要有多余空格)：");
    }

    /**
     * 显示select功能的提示语
     */
    public static void selectHint() {

        System.out.println("输入你想要查询的表名，表的原名：");
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
