package cn.whaifree.springdemo.utils.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/12 22:13
 * @注释
 */
@Component
@WebFilter(filterName = "SelfFilter", urlPatterns = "/*")
public class SelfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("SelfFilter doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
//        System.out.println("SelfFilter doFilter end");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
