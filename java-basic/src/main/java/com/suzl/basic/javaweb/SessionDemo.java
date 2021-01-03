package com.suzl.basic.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * <p>JavaEE session demo</p>
 *
 * @author suzailong
 * @date 2021/1/3 6:51 下午
 */
@WebServlet(urlPatterns = "/sign")
public class SessionDemo extends HttpServlet {
    // 模拟一个数据库
    private final Map<String, String> userList = Map.of("alice", "alice123", "tom", "tomcat");

    // POST 请求时候处理用户登陆
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");
        if (username != null && passwd != null) {
            // * session: set in to session
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
        }
        // 从HttpSession 获取当前用户名:
        // String user = (String) req.getSession().getAttribute("user");
    }
}
