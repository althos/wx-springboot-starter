package org.cyber.wx.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:junru.pan
 * Email: junrupan@sf-express.com
 */

@Data
public class WxUserInfo  implements Serializable {

    private static final long serialVersionUID = 1L;
    //头像链接
    private String avatarUrl;
    //所在城市
    private String city;
    //新别
    private Integer gender;
    //昵称
    private String nickName;
    //省
    private String province;

}
