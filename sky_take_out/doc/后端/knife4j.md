# SpringBoot整合knife4j

参考链接([SpringBoot整合knife4j（快速入门超详细版）-CSDN博客](https://blog.csdn.net/weixin_47316183/article/details/132016946))

## 什么是[Knife4j](https://so.csdn.net/so/search?q=Knife4j&spm=1001.2101.3001.7020)

在日常开发中，写接口文档是我们必不可少的，而Knife4j就是一个接口文档工具，可以看作是Swagger的升级版，但是界面比Swagger更好看，功能更丰富。

是`Swagger2`和`OpenAPI3`为一体的增强解决方案。

> 早期，swagger-boostrap-ui是1.x版本，如今swagger-bootsrap-ui到2.x，同时也更改名字Knife4j，适用于单体和微服务项目。

Knife4j官方网站：https://doc.xiaominfo.com/

## SpringBoor整合Knife4j

### Knife4j配置

1、创建一个SpringBoot项目
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404262356976.png)

2、引入Knife4j相关依赖（这里顺带引入了Lombok依赖）

```bash
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.8</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

3、创建Knife4J配置类

```java
package com.eric.springbootknife4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置信息
 * 这里分了两组显示
 * 第一组是api，当作用户端接口
 * 第二组是admin，当作后台管理接口
 * 也可以根据实际情况来减少或者增加组
 *
 * @author Eric
 * @date 2023-07-30 22:17
 */

@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("Eric-SpringBoot整合Knife4j-API文档")
                .description("本文档描述了SpringBoot如何整合Knife4j")
                .version("1.0")
                .contact(new Contact("Eric", "https://blog.csdn.net/weixin_47316183?type=blog", "ericsyn@foxmail.com"))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("Eric-SpringBoot整合Knife4j-API文档")
                .description("本文档描述了SpringBoot如何整合Knife4j")
                .version("1.0")
                .contact(new Contact("Eric", "https://blog.csdn.net/weixin_47316183?type=blog", "ericsyn@foxmail.com"))
                .build();
    }

    /**
     * 第一组：api
     * @return
     */
    @Bean
    public Docket webApiConfig() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("userId")
                .description("用户token")
                //.defaultValue(JwtHelper.createToken(1L, "admin"))
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        Docket webApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                     //在这里使用了
                .apiInfo(webApiInfo())
                .select()
                //只显示api路径下的页面
                .apis(RequestHandlerSelectors.basePackage("com.eric.springbootknife4j"))
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .globalOperationParameters(pars);
        return webApi;
    }

    /**
     * 第二组：admin
     * @return
     */
    @Bean
    public Docket adminApiConfig() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("adminId")
                .description("用户token")
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        Docket adminApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台接口")
                .apiInfo(adminApiInfo())
                .select()
                //只显示admin路径下的页面
                .apis(RequestHandlerSelectors.basePackage("com.eric.springbootknife4j"))
                .paths(PathSelectors.regex("/admin/.*"))
                .build()
                .globalOperationParameters(pars);
        return adminApi;
    }

}
```

### 使用Knife4j

1、创建一个实体类

```java
package com.eric.springbootknife4j.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Eric
 * @date 2023-07-31 9:55
 */
@ApiModel("用户实体类")
@Data
@Builder
public class SwaggerUser {

    @ApiModelProperty("用户Id")
    private Long id;

    @ApiModelProperty("用户名称")
    private String name;

}
```

2、对应接口，这里我为了大家看的方便，也是分了两个控制器，也是根据api和admin来分的
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404262356722.png)

**admin**

```java
package com.eric.springbootknife4j.admin;

import com.eric.springbootknife4j.entity.SwaggerUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric
 * @date 2023-07-31 9:50
 */
@Api(tags = "用户端控制器")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation(value = "获取数据")
    @GetMapping("/test")
    public List<SwaggerUser> test(@ApiParam(name = "id",value = "用户Id") Long id,
                                  @ApiParam(name = "name",value = "用户名称") String name){

        List<SwaggerUser> list = new ArrayList<>();
        list.add(SwaggerUser.builder().id(id).name(name).build());
        return list;
    }
}

```

**api**

```java
package com.eric.springbootknife4j.api;

import com.eric.springbootknife4j.entity.SwaggerUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric
 * @date 2023-07-31 9:50
 */
@Api(tags = "用户端控制器")
@RestController
@RequestMapping("/api")
public class ApiController {

    @ApiOperation(value = "获取数据")
    @GetMapping("/test")
    public List<SwaggerUser> test(@ApiParam(name = "id",value = "用户Id") Long id,
                                  @ApiParam(name = "name",value = "用户名称") String name){

        List<SwaggerUser> list = new ArrayList<>();
        list.add(SwaggerUser.builder().id(id).name(name).build());
        return list;
    }
}

```

### 效果

此时运行项目，访问 IP+端口/doc.html
例如：http://127.0.0.1:8080/doc.html


这就是首页
![在这里插入图片描述](https://img-blog.csdnimg.cn/daea0806e95b45e39dd14eff5d08f388.png)
包括也是按照我们的配置文件来分组显示了，这里可根据自己项目的实际情况，按照微服务来分、按照功能目录来分都是可以的
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404262356084.png)

如下就是我们的实体类，发现我们写的注释也都自动生成了
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404262356080.png)

下面就是我们的接口调试界面了
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404262356181.png)
自带路径、接口说明、参数说明，只要我们写了解释，这里都会显示

此时我们也可以点击调试，然后点击发送，就能看到我们的返回信息了，并且如果我们的返回实体类上有注解注释的，这里也是会显示的
![在这里插入图片描述](https://img-blog.csdnimg.cn/a51737046a024d86abd9652ebc86879c.png)

## 苍穹外卖的使用

第一步引入在父工程中的`pom.xml`中写的很清楚。

### 创建配置类

首先是在这个路径下。

![image-20240427041825670](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404270418799.png)

代码

```java
package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.interceptor.JwtTokenUserInterceptor;
import com.sky.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
//配置Bean用的
@Configuration
//这一步跟前面不一样，但是是一个效果的
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
//这俩是拦截器使用的参数
    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

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
//这里是knife4j的配置类书写，或者说叫swagger的书写
    /**
     * 通过knife4j生成接口文档
     * @return
     */
    //单例的注入
    @Bean
    public Docket docket() {
        //
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("苍穹外卖项目接口文档")
                .version("2.0")
                .description("苍穹外卖项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
               //只显示这个路径下的页面
            .apis(RequestHandlerSelectors.basePackage("com.sky.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射，否则接口文档页面无法访问
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 扩展Spring MVC框架的消息转化器
     * @param converters
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对象转换器可以将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转化器加入容器中
        converters.add(0,converter);
    }
//下方两个是进行分组显示
    @Bean
    public Docket docket1(){
        log.info("准备生成接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("苍穹外卖项目接口文档")
                .version("2.0")
                .description("苍穹外卖项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.sky.controller.admin"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    @Bean
    public Docket docket2(){
        log.info("准备生成接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("苍穹外卖项目接口文档")
                .version("2.0")
                .description("苍穹外卖项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.sky.controller.user"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }
}

```

### 使用 Knife4j

#### 创建实体类

这里以雇员的实体类为例子。这个文件夹下的其他文件同理。

![image-20240427071039207](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404270710297.png)

这里注意路径。

然后这里是使用了`lombok`。这个会在其他文档中详细解释。

```java
package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="数据库字段")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;
    //这个是在参数那边添加个注释
    @ApiModelProperty("姓名")
    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

}

```

首先在这里解释什么叫做实体类。

> 实体类是**用于对必须存储的信息和相关行为建模的类**。 实体对象（实体类的实例）用于保存和更新一些现象的有关信息，例如：事件、人员或者一些现实生活中的对象。 实体类通常都是永久性的，它们所具有的属性和关系是长期需要的，有时甚至在系统的整个生存期都需要。

当然这里可以的实际作用就是跟数据库存储的字段信息所用的类。

就是跟数据库中的数据表相对应的字段。

![image-20240427072131436](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404270721481.png)

就会发现这是跟上方内容一一对应。

### 对应的接口

即这里创建两个控制器。

![image-20240427072347923](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404270723010.png)

注意路径，这里也仅仅展示管理端的接口调试信息。

| **注解**          | **说明**                                               |
| ----------------- | ------------------------------------------------------ |
| @Api              | 用在类上，例如Controller，表示对类的说明               |
| @ApiModel         | 用在类上，例如entity、DTO、VO                          |
| @ApiModelProperty | 用在属性上，描述属性信息                               |
| @ApiOperation     | 用在方法上，例如Controller的方法，说明方法的用途、作用 |

分析代码

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
//快速创建个控制器=controller + responsebody
@RestController
//这里是匹配URL路径
@RequestMapping("/admin/employee")
//开启swagger
@Slf4j
//这里是对类的说明，相当于添加一个分组名字
@Api(tags = "员工相关接口")
public class EmployeeController {
//自动注入，先不管
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
    //这里牵扯到web服务的事。登陆后生成令牌
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

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     * @return
     */
    //这里才是对接口的说明。用在方法上，说明方法的用途，作用就是在swagger添加个接口
    @PostMapping
    @ApiOperation("新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询，参数为：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账户
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账户")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用禁用员工账户：{}，{}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据iD查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据iD查询用户信息")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("根据iD查询用户信息：{}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 编辑员工信息
     *
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("编辑员工信息：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

//    @PutMapping("/editPassword")
//    @ApiOperation("修改密码")
//    public Result editPassword(@RequestBody EmployeeDTO employeeDTO){
//        log.info("修改密码");
//        employeeService.editPassword(employeeDTO);//todo:实现密码修改
//        return Result.success();
//    }
}

```

### 这里是实际运行效果

![image-20240427161648019](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404271616055.png)

![image-20240427161610056](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404271616255.png)

![image-20240427161623805](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404271616204.png)

### 作用

使用这个就是为了可以调试。例如![image-20240427161807371](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404271618556.png)

可以验证写的接口是否正确。
