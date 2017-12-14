package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.AccessTokenDto;
import com.ani.ccyl.leg.persistence.po.AccessTokenPO;

/**
 * Created by lihui on 17-12-13.
 */
public class TokenAdapter {
    public static AccessTokenPO fromDto(AccessTokenDto accessTokenDto) {
        if(accessTokenDto != null)
            return new AccessTokenPO(
                    null,
                    accessTokenDto.getAccessToken(),
                    accessTokenDto.getTokenExpiresIn(),
                    accessTokenDto.getTicketExpiresIn(),
                    accessTokenDto.getJsapiTicket(),
                    accessTokenDto.getTokenCreateTime(),
                    accessTokenDto.getTicketCreateTime(),
                    false
            );
        return null;
    }

    public static AccessTokenDto fromPO(AccessTokenPO accessTokenPO) {
        if(accessTokenPO != null)
            return new AccessTokenDto(
                    accessTokenPO.getId(),
                    accessTokenPO.getAccessToken(),
                    accessTokenPO.getTokenExpiresIn(),
                    accessTokenPO.getTicketExpiresIn(),
                    accessTokenPO.getJsapiTicket(),
                    accessTokenPO.getTokenCreateTime(),
                    accessTokenPO.getTicketCreateTime()
            );
        return null;
    }
}
