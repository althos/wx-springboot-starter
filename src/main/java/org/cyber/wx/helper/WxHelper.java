package org.cyber.wx.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    public JSONObject getUserInfo(String code) {

        JSONObject json = accessData(code);
        if (!"200".equals(json.getString("helper_code"))) {
            return json;
        }
        String access_token = json.getString("access_token");
        String openid = json.getString("openid");

        String userInfoUrl = getUserInfoUrl(access_token, openid);
        String res = restTemplate.getForObject(userInfoUrl, String.class);  //用户信息
        JSONObject userData = JSON.parseObject(res);

        if (ObjectUtils.isEmpty(userData) || ObjectUtils.isEmpty(userData.get("sex"))) {

            userData = ObjectUtils.isEmpty(userData) ? new JSONObject() : userData;
            userData.clear();
            userData.put("helper_code", "400");
            userData.put("helper_msg", "获取用户信息失败");
        } else {

            userData.put("helper_code", "200");
            userData.put("helper_msg", "获取用户信息成功");

        }
        return userData;
    }

    /**
     * 获取access_token
     *
     * @param code
     * @return
     */
    private JSONObject accessData(String code) {
        String acessUrl = getWpaAccessUrl(code);
        String res = restTemplate.getForObject(acessUrl, String.class);  //获取openid
        JSONObject accessData = JSON.parseObject(res);

        if (ObjectUtils.isEmpty(accessData) || ObjectUtils.isEmpty(accessData.get("openid"))) {

            accessData = ObjectUtils.isEmpty(accessData) ? new JSONObject() : accessData;
            accessData.clear();
            accessData.put("helper_code", "400");
        } else {
            accessData.put("helper_code", "200");
        }
        return accessData;
    }

    private String getWpaAccessUrl(String code) {
        return String.format(WxConstant.WPA_TOKEN_ACCESS_USRL.getUrl(),
                wxProperties.getAppId(),
                wxProperties.getSecret(),
                code);
    }

    private String getUserInfoUrl(String access_token, String openid) {
        return String.format(WxConstant.USER_MESSAGE_USR.getUrl(), access_token, openid);
    }


}
