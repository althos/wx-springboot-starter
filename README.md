# wx-springboot-starter
>   最近做微信公众号的开发，微信的功能用springboot打了个starter包,目前仅支持获取用户信息,后期迭代新功能.....



### 去我的github下载[wx-springboot-starter](https://github.com/althos/wx-springboot-starter/blob/master/jar/wx-springboot-starter-1.0.0-SNAPSHOT.jar),源码参见我的[Github](https://github.com/althos/wx-springboot-starter)

 创建一个新的springboot项目 parent版本用2.0.0.realse

> ```
> <parent>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-parent</artifactId>
>     <version>2.0.0.RELEASE</version>
> </parent>
> 备注：wx-springboot-starter是基于2.0.0的版本开发的
> <dependencies>
>         <dependency>
>             <groupId>org.cyber.founder</groupId>
>             <artifactId>wx-springboot-starter</artifactId>
>             <version>1.0.0-SNAPSHOT</version>
>         </dependency>
>   </dependencies>
>   （我有打自己的私服）
> ```



### 配置yml

>```
>wx:
>  appId: 
>  #密钥
>  secret: 
>  #跳转链接
>  directUrl: http://c58eeb.natappfree.cc/test/wx_login（微信异步回调,此处的域名是内网穿透）
>  scope: snsapi_userinfo
>  enabled: true
>```



### 编写测试，写一个contoller测试

> ```
> @RestController
> @RequestMapping("/test")
> public class TestContoller {
>     @Autowired
>     private WxHelper wxHelper;// starter实例化的工具
> 
>     @RequestMapping("/url")
>     public R getCodeUrl(){
>         //获取跳转链接的接口
>         return R.ok().put("codeUrl",wxHelper.getCodeUrl());
>     }
>         //获取用户信息
>     @RequestMapping("/wx_login")
>     public R getLogin(String code) {
> 
>         JSONObject object = wxHelper.getUserInfo(code);
>         return R.ok().put("userinfo",object);
> 
>     }
> ```

###### 获取授权链接返回值：

>```
>{"msg":"success","codeUrl":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5a64ae8cc99d0b04&redirect_uri=http://c58eeb.natappfree.cc/test/wx_login&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect","code":0}
>
>codeUrl即是微信浏览器的链接
>```



###### 获取用户数据如banner图展示