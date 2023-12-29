package com.apple;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan
@EnableWebMvc
public class AppConfig {
    public static void main(String[] args) throws Exception {
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(Integer.getInteger("port", 8080));
//        tomcat.getConnector();
//        Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
//        WebResourceRoot resources = new StandardRoot(ctx);
//        resources.addPreResources(
//                new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
//        ctx.setResources(resources);
//        tomcat.start();
//        tomcat.getServer().await();
    }
}
