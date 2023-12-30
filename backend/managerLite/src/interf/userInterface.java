package interf;

public class userInterface {
    public static void welcome() {
        String tableList = "warehouse admin raw\nproduct supplier account log";

        System.out.println("------------欢迎------------");
        System.out.println("数据库中有的数据表");
        System.out.println(tableList);
        System.out.println("\n支持的操作：select, insert");
        System.out.println("---------------------------");
        System.out.println("请先输入操作");
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
        System.out.println("输入你想插入的表名，表的中文名或者表的原名：");
    }

    public static void end() {
        System.out.println("*程序结束*");
    }
}
