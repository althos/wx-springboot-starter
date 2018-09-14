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
     * 获取用户信息的链接
     */
    USER_MESSAGE_USR("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN"),
    /**
     * 公众号平台的链接
     */
    WPA_TOKEN_ACCESS_USRL("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code");

    private final String url;

    private WxConstant(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
