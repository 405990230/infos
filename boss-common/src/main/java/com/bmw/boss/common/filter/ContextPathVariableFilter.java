package com.bmw.boss.common.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class ContextPathVariableFilter implements Filter {

	@Override
	public void destroy() {
		/**
		 * Servlet容器在销毁过滤器实例前调用该方法，在该方法中释放Servlet过滤器占用的资源。
		 */
		System.out.println("过滤路径销毁");	
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		/**
		 * 该方法完成实际的过滤操作，当客户端请求方法与过滤器设置匹配的URL时，Servlet容器将先调用过滤器的doFilter方法。
		 * FilterChain用户访问后续过滤器
		 */
		
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String ctx = request.getContextPath();
        request.setAttribute("ctx", ctx);
        filterChain.doFilter(servletRequest, servletResponse);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		/**
		 * web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，
		 * 读取web.xml配置，完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作
		 * （filter对象只会创建一次，init方法也只会执行一次）。
		 * 开发人员通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。	
		 */
		System.out.println("infos过滤路径初始化");
	}

}
