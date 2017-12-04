package com.ani.ccyl.leg.service.service.facade;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihui on 17-12-4.
 */
public interface WechatService {
    String processRequest(String xml) throws Exception;
}
