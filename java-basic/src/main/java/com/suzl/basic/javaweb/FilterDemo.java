package com.suzl.basic.javaweb;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * <p>Filter</p>
 *
 * @author suzailong
 * @date 2021/1/3 7:28 下午
 */
@WebServlet("/*") // 表示全部的路径
public class FilterDemo implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 必须调用chain.doFilter()
        chain.doFilter(request, response);
    }
}
