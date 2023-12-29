package db;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/DatabaseServlet")
public class controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 数据库连接信息
            String url = "jdbc:mysql://localhost:3306/apple";
            String username = "root";
            String password = "220610";

            // 注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立连接
            conn = DriverManager.getConnection(url, username, password);

            // 执行查询
            stmt = conn.createStatement();
            String sql = "SELECT * FROM warehouse";
            rs = stmt.executeQuery(sql);

            // 处理结果集
            out.println("<html><body><h2>User List:</h2><ul>");
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                out.println("<li>Name: " + name + ", Age: " + age + "</li>");
            }
            out.println("</ul></body></html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
