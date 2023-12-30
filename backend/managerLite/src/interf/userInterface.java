package interf;

public class userInterface {
    public static void welcome() {
        String tableList = "warehouse admin raw product supplier account log";

        System.out.println("------------欢迎------------");
        System.out.println("直接输入名字即可查询");
        System.out.println(tableList);
        System.out.println("---------------------------");
    }
    public static void selectHint() {
        System.out.println("输入你想要查询的表名，可以是表的中文名也可以是表的原名：");
    }
}
