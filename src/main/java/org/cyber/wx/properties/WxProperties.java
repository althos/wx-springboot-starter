package org.cyber.wx.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wx")
@Getter
@Setter
public class WxProperties {
  /*  private String codeUrl;              //获取授权码的请求地址
    private String webAccessTokenUrl;    //开发者平台
    private String accessTokenUrl;       //用户网页授权access_token 获取接口,需要授权码
    private String userMessageUrl;       //拉取用户信息的请求地址，获取code的时候scope 参数传snsapi_userinfo来换取的access_token
    */
    private String appId;
    private String secret;
    private String directUrl;
    private String scope;
   /* private String jsapiTicketUrl;       //获取加签票据的url*/
    private boolean enabled;
  /*  private String baseAccessTokenUrl;    //基本获取accesstoken的url */
}
