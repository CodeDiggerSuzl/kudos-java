package com.suzl.basic.javaweb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>Servlet test</p>
 *
 * @author suzailong
 * @date 2021/1/3 6:02 下午
 */
@WebServlet
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置响应类型
        resp.setContentType("text/html");
        //  获取输出流
        PrintWriter writer = resp.getWriter();
        writer.flush();
    }
}
