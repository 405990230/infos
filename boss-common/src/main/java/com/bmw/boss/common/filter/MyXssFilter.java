package com.bmw.boss.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 获取参数时对参数进行XSS判断预防
 * @Author boss yan
 * @Date 2018/10/23 13:39
 */
@WebFilter(filterName="xssMyfilter",urlPatterns="/*")
public class MyXssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        XsslHttpServletRequestWrapper xssRequest = new XsslHttpServletRequestWrapper((HttpServletRequest)request);
        chain.doFilter(xssRequest , response);
    }

    @Override
    public void destroy() {

    }

}