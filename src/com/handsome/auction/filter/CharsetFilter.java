package com.handsome.auction.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * by wangrongjun on 2017/7/13.
 */
public class CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
    }

    @Override
    public void destroy() {

    }
}
