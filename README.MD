# 一个简单的OAuth2实现Sso单点登录的示例

        OAuth2的sso简单实现，配置了认证服务和资源服务，注册了3个客户端，都是自动授权，不需要用户授权

        登录用户名：user
        登录密码：123456

## client1
        client1是客户端1，访问*URL* [client1](http://localhost:8081/client1 "client1")
### 页面会显示
        result :
            access protected resource!
## client2
        client2是客户端2，访问*URL* [client2](http://localhost:8082/client2 "client2")
### 页面会显示
        result :
            access protected resource!
## client3
        client3是客户端3，访问*URL* [client3](http://localhost:8083/client3/simple "client3")
        页面显示一个table展示用户信息


## 使用的技术
        *spring boot*，*oauth2*，*thymeleaf*，*jQuery*，*bootstrap* 等


