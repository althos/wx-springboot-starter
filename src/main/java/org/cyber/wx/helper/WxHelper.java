package org.cyber.wx.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.cyber.wx.common.HelperCode;
import org.cyber.wx.constant.WxConstant;
import org.cyber.wx.properties.WxProperties;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

public class WxHelper {

    private WxProperties wxProperties;
    private RestTemplate restTemplate;

    public WxHelper(WxProperties wxProperties, RestTemplate restTemplate) {
        this.wxProperties = wxProperties;
        this.restTemplate = restTemplate;
    }

    /**
     * @return
     */
    public String getCodeUrl() {
        return String.format(WxConstant.CODE_URL.getUrl(), wxProperties.getAppId(), wxProperties.getDirectUrl(), wxProperties.getScope());
    }

    /**
     * 得到回调code时候去转用户信息
     * @param code
     * @return
     */
    public JSONObject getUserInfo(String code) {

        JSONObject json = AuthAccessData(code);
        if (!"200".equals(json.getString("helper_code"))) {
            return json;
        }
        String access_token = json.getString("access_token");
        String openid = json.getString("openid");

        String userInfoUrl = getUserInfoUrl(access_token, openid);
        //用户信息
        String res = restTemplate.getForObject(userInfoUrl, String.class);
        JSONObject userData = JSON.parseObject(res);

        if (ObjectUtils.isEmpty(userData) || ObjectUtils.isEmpty(userData.get("sex"))) {

            userData = ObjectUtils.isEmpty(userData) ? new JSONObject() : userData;
            userData.clear();
            userData.fluentPutAll(HelperCode.error());
        } else {
            userData.fluentPutAll(HelperCode.ok());
        }
        return userData;
    }



    /**
     * 获取access_token
     * @param code
     * @return
     */
    private JSONObject AuthAccessData(String code) {

        String acessUrl = getWpaAccessUrl(code);
        //获取openid
        String res = restTemplate.getForObject(acessUrl, String.class);
        JSONObject accessData = JSON.parseObject(res);
        if (ObjectUtils.isEmpty(accessData)
                || ObjectUtils.isEmpty(accessData.get("openid"))) {

            accessData = ObjectUtils.isEmpty(accessData) ? new JSONObject() : accessData;
            accessData.clear();
            accessData.fluentPutAll(HelperCode.error());
        } else {
            accessData.fluentPutAll(HelperCode.ok());
        }
        return accessData;
    }

    /**
     * 获取公众号平台的access链接
     *
     * @param code
     * @return
     */
    private String getWpaAccessUrl(String code) {

        return String.format(WxConstant.WPA_TOKEN_ACCESS_USRL.getUrl(), wxProperties.getAppId(), wxProperties.getSecret(), code);
    }

    /**
     * 获取用户请求链接
     * @param access_token
     * @param openid
     * @return
     */
    private String getUserInfoUrl(String access_token, String openid) {
        return String.format(WxConstant.USER_MESSAGE_USR.getUrl(), access_token, openid);
    }

}
