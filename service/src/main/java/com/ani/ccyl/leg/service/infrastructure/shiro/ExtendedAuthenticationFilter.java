package com.ani.ccyl.leg.service.infrastructure.shiro;


import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by lihui on 17-12-1.
 */
public class ExtendedAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }

}
