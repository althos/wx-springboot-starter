package org.cyber.wx.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wx")
@Getter
@Setter
public class WxProperties {
    /*private String codeUrl;
    private String webAccessTokenUrl;   //开发者平台
    private String accessTokenUrl;      //公众号平台
    private String userMessageUrl;*/
    private String appId;
    private String secret;
    private String directUrl;
    private String scope;
    private boolean enabled;

}
