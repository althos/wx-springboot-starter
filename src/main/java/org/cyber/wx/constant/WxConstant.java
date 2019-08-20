package org.cyber.wx.constant;

public enum WxConstant {

    /**
     * 获取code码的链接
     */
    CODE_URL("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect"),
    /**
     * 开发平台获取公众号的链接
     */
    DEV_TOKEN_ACCESS_URL("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code"),
    /**
     * 拉取用户信息的请求地址，获取code的时候scope 参数传snsapi_userinfo来换取的access_token
     */
    USER_MESSAGE_USR("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN"),
    /**
     * 授权链接，需要获取用户的code码
     */
    WPA_TOKEN_ACCESS_USRL("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code"),

    /**
     * 基础获取微信token的url
     */
    BASE_ACCESS_TOKEN_URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),
    /**
     * 微信票据的获取
     */
    JS_API_TICKET_URL("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi");


    private final String url;

    private WxConstant(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
