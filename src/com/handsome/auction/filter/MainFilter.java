package com.handsome.auction.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * by wangrongjun on 2017/7/14.
 */
public class MainFilter implements Filter {

    private Map<String, String> map = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        map.put("/index_manager.jsp", "/searchAuctions.action");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String newPath = map.get(request.getServletPath());
        if (newPath != null) {
            request.getRequestDispatcher(newPath).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
