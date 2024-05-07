# jwt/jjwt快速入门

## 介绍文档

JWTs是JSON对象的编码表示。JSON对象由零或多个名称/值对组成，其中名称为字符串，值为任意JSON值。

jwt的组成

Header: 标题包含了令牌的元数据，并且在最小包含签名和/或加密算法的类型

Claims: Claims包含您想要签署的任何信息

JSON Web Signature (JWS): 在header中指定的使用该算法的数字签名和声明

jwt的框架：JJWT

JJWT是一个提供端到端的JWT创建和验证的Java库。永远免费和开源(Apache)，JJWT很容易使用和理解。它被设计成一个以建筑为中心的流畅界面，隐藏了它的大部分复杂性。

JJWT的目标是最容易使用和理解用于在JVM上创建和验证JSON Web令牌(JWTs)的库。

JJWT是基于JWT、JWS、JWE、JWK和JWA RFC规范的Java实现。

JJWT还添加了一些不属于规范的便利扩展，比如JWT压缩和索赔强制。

```xml

<dependency>
<groupId>io.jsonwebtoken</groupId>
<artifactId>jjwt</artifactId>
<version>0.9.1</version>
</dependency>
```

控制器

```java
@RestController
@RequestMapping("jwt")
public class JwtController {

String token;

@RequestMapping("token1")
public String token(){

JwtBuilder jwtBuilder = Jwts.builder()
.setId("888")//唯一ID
.setSubject("Rose")//接受用户
.setIssuedAt(new Date())//签发时间
.signWith(SignatureAlgorithm.HS512, "1234");//签名算法、和秘钥
// secret key byte array cannot be null or empty.
// key不能太短 最短四个字符！！！

String token = jwtBuilder.compact();
System.out.println(token);

String[] arr = token.split(".");
System.out.println(Base64Codec.BASE64.decodeToString(arr[0]));//base64编码
System.out.println(Base64Codec.BASE64.decodeToString(arr[1]));//base64解码
System.out.println(Base64Codec.BASE64.decodeToString(arr[2]));//加密、解密不了

return "生成令牌：" + token;
}

@RequestMapping("token2")
public String token2(){

String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjQ5Mzg1NDQzfQ.8O7c9gzQfMEJqGNK-b--L1lcyOYP_WhNCocfYVHO34cBA9ebTgZy2_-hkh_u40-xxrtaSs4bIKcX29_1YK0pVg";
// 解析token、获得laims、jwt中荷载中申明的对象
Claims claims = (Claims)Jwts.parser()
.setSigningKey("1234")
.parse(token)
.getBody();

System.out.println(claims.getId());
System.out.println(claims.getSubject());
System.out.println(claims.getIssuedAt());

return "解析令牌：" + claims;
}

}
```

## 苍穹外卖

这个应用很分散。

首先是用户登陆。就是在浏览器中第一件事登陆

![image-20240430001319092](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404300013398.png)这里是实现的代码。

```java
package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }
}
```

上述代码跟前面的文档对比，发现这里是创建`token`并且返回前端视图。所以从这里也可以明白所谓登陆和退出，就是返回的前端视图是否为空。虽然这里又调用service，但说白了也是相当于一个检验。

分析代码，发现这里调用了`JwtUtil.createJWT`,跳转过去，就会发现实际的调用方法。

```java
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置过期时间
                .setExpiration(exp);

        return builder.compact();
    }
```

---

设置了自然是让用

而这个使用是单独`api`调用的时候需要加的头。

![image-20240430002136238](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404300021270.png)

也就是在请求头部中。

那么程序是怎么自动运行的呢。

在这

![image-20240430002230141](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404300022275.png)这里有个拦截器。

那么自然这里会有解码器。

详细代码

```java
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：", empId);

//            将用户id存储到ThreadLocal
            BaseContext.setCurrentId(empId);

            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
```

首先解释这个是怎么用的，是拦截请求，从请求中获取`token`,得到一个键值对，把键值对的值放到上下文中，ps，这里就理解为传递给当前线程信息。

那么自然这里面也是调用了`JwtUtil`，跳转过去

```java
  /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

```

跟上述文档中的进行对比理解。虽然我也不知道这玩意儿到底是在干啥，反正也是调用的接口，无所谓了，只需要知道怎么用就行。



---

看上面的拦截器啥的，有没有好奇这是怎么用的，毕竟应用流程也就是浏览器请求服务器资源，服务器进行响应。

那这一个就是在请求的时候进行校验，问题又来了，这个框架是啥，`SpringMVC`，所以也就是需要在请求的时候添加一个拦截器

```java
 /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");

        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");

        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login")
                .excludePathPatterns("/user/shop/status");
    }

```

也就是这玩意儿。

再仔细看，发现这玩意儿也就是被定义为配置(configuration)/原件（component)

所以也就是在最开始的时候就给注册进去了



