package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.AccessTokenDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lihui on 17-12-4.
 */
public interface WechatService {
    String processRequest(HttpServletRequest request, HttpServletResponse response);
    AccessTokenDto updateToken();
}
