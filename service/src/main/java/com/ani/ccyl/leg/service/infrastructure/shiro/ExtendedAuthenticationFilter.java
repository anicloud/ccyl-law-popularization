package com.ani.ccyl.leg.service.infrastructure.shiro;


import com.ani.ccyl.leg.commons.enums.HttpMessageEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lihui on 17-12-1.
 */
public class ExtendedAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.sendRedirect(request.getContextPath()+"/home/index?op="+ HttpMessageEnum.ACCESS_DENIED.name());
        return false;
    }


}
