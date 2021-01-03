package com.suzl.basic.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>重定向</p>
 *
 * @author suzailong
 * @date 2021/1/3 6:32 下午
 */
@WebServlet(urlPatterns = "/hi")
public class RedirectTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 构造重定向的路径:
        String name = req.getParameter("name");
        String redirectToUrl = "/hello" + (name == null ? "" : "?name=" + name);
        // * redirect
        resp.sendRedirect(redirectToUrl);
        // * forward
        /// req.getRequestDispatcher("/hi").forward(req, resp);

    }
}
