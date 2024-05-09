# 苍穹外卖

书写这个程序没有多少时间，但后续的总结确实很烦人。

通过这个项目，主要学习到业务相关的流程，通俗易懂的名字是软件开发流程。比起代码书写，更多的作用是用于熟悉`Spring Boot`和`Spring MVC`框架。各种插件，相关软件的使用，熟悉开发相关协议。布置环境出现的问题。选择通过这一份文档全部展现出来，记录这其中的技术栈，和遇到的相关问题，相关资料。



## 前言

### 明确自己的身份

一窝端的全写会糊里糊涂的。

#### 软件开发周期

在软件开发中，一共是分为以下几个周期

1. 需求分析
2. 设计
3. 编码
4. 测试
5. 上线运维

每个角色都要有自己的任务。

#### 任务

下面这是关于每个周期的详细任务。

1. 需求分析：观察市场需求，明确设计出软件需要完成的目标，给出一个大致的框架。如：系统定义、应用环境。功能规格、性能需求。通过网页展现软件的布局，按钮功能。即给出一个完整的软件框架。
2. 设计：主要是数据库设计和`UI`设计、接口设计。如数据库设计是项目涉及到什么数据库，数据库中包含的表，表中字段、表与表之间的关系。`UI`设计：页面动画之类。接口设计：通过分析原型图（即第一步给出的框架），粗粒度的说明每个页面有多少接口，细粒度：每个接口的传入参数，返回值参数，还有接口路径，请求方式。
3. 编码：分位前端编码和后端编码。前端编码在需求分析中已经完善，主要是后端编码，根据需求说明书中提到的内容分模块编写代码。同时在代码编写后续写单元测试。
4. 测试：有专门的测试环境，对项目进行功能测试，出具测试报告
5. 上线运维：即部署到生产环境中。日常维护。

尽量展示每个角色所做的任务。看这份文档能完善到什么地步吧。

### 苍穹外卖介绍

根据以上五个周期，需要明确的是需要编写哪些。

1. 前端展示页面
2. 后端软件模块
3. 小程序
4. 开发环境搭建

那么深入研究，需要编写那些模块

### 需要编写的模块

#### 管理端功能

内部员工使用

员工的登陆退出：员工必须登陆后，才能访问系统管理后台

员工信息管理：管理员可以在系统后台进行管理，crud

分类管理：菜品分类和套餐分类进行 crud

菜品管理：分类下的菜品信息，包含crud，启用停售

套餐管理：crud,起售停售

菜品口味管理

订单管理：在移动端的订单信息。查询，取消，派送完成、订单报表下载等功能

数据统计：主要对，营业额，用户数量，订单进行统计

来单提醒

#### 用户端功能

消费者使用

微信登陆：用户通过微信授权后登陆使用小程序

点餐-菜单：展示菜品分类/套餐分类。根据当前选择的分类加载其中的菜品信息，供用户查询选择

点餐-购物车：crud,清空购物车功能

订单支付：个人页面展示当前信息，管理地址，查询订单

收件人地址管理

用户历史订单查询

### 技术栈

前端（用户层）：使用 `vue` 框架、`H5`、`ElementUI`、`apache echarts`。微信小程序

网关层：使用`nginx`，主要是作为`http`服务器。部署静态资源。主要是反向代理和负载均衡

后端（应用层）：`springboot`:快速构建`spring`项目，约定优先配置、`spingMVC`开发模块思想。`spring task`定时任务框架。`httpclient`发送`http`请求。`spring cache`数据缓存框架 。`jwt`用于对用户进行身份验证的标记。`阿里云oss`对象存储服务。`swagger`自动为开发人员生成接口文档，并且还有测试。`poi`对`excel`的常用操作。`websocket`通信网络协议，客户端和服务器端数据交换，主要在来单，催单、

数据库：`MYSQL`关系型数据库，持久化存储、`Redis`内存数据库，缓存、`Mybatis`，持久层、`pageheloer`分也插件、`spring data redis`简化`redis`的操作

### 工具

1. git：版本控制工具
2. maven：项目构建工具
3. junit：单元测试工具
4. postman：借口测工具

书写的思路就是从需求，实现思路，使用的技术，技术实现，前后端测试。这样的暂定的整理思路

## 软件开发流程

### 需求分析

完善需求规格说明书。产品原型编写。

> 需求规格说明书：描述当前项目的各个组成部分，如，系统定义、应用环境、功能规格、性能需求

> 产品原型：通过网页形式展现产品的页面布局，功能，语气实现

### 设计

`UI`设计、数据库设计、接口设计

`UI`设计，不考虑去学。

> 数据库设计：设计当前项目中要涉及到的数据库，数据库中的都有什么表，这些表的结构关系。通过 `power designer` 进行设计

> 接口设计：通过分析，第一步分析每个页面有多少个接口。第二部分析每个接口的传入参数，返回值参数，明确借口路径和请求方式。通过 `yapipro`进行设计 

### 编码

编写项目代码，完成单元测试

> 项目代码编写：对项目模块功能分析，进行编码实现

> 单元测试：要养成一个良好的测试习惯。完成一个阶段后，要及时测试相关功能是否完整实现，控制台输出是否正确。

### 测试

交给测试人员。进行整体式测试，模拟生产环境，并写测试报告

###  上线运维

对生产环境的配置，并记录运行日志

### 开发环境

主要是前端环境和后端环境

#### 前端环境

哪个操作系统上都一样。但更合适的是放到`linux`上进行开发测试。别问，问就是安装有点麻烦

所以这是部署在`ubuntu`的`docker`容器上，并搭建`nginx`.

关于搭建方法，

1. 搭建虚拟机
2. 安装`docker`
3. 拉取`nginx`，并运行。
4. 设置`nginx.conf`
5. 开放端口

#### 后端环境

主要就是安装一个`idea`。就可以开发了

#### 数据库

采用`MYSQL`，并部署与`docker`上，打开端口，关闭防火墙

导入`sql`文件，创建数据库。当然这个也只是`mysql`的命令。

#### 前后端联调

这里配置的难点是在于，后端开设的是`8080`端口，前端访问的是`80`端口，也就是在网关处需要做反向代理，在网关处把端口访问给建设好。

至于负载均衡，也是在网关处，目的是通过多个服务器，减少访问压力。



#### 前后端分离

依靠反向代理，前端处理`80`端口，后端`8080`端口，关于`url`的接口路径，前后端同时开发

这里是使用`yapo.pro`进行设计接口

后端的模拟请求测试是通过`Swagger`框架进行可视化处理。

这里是使用`spring-swagger`模块，现在是`springfox`。但用的时候是`knife4j`是`MVC`继承`swagger`生成`api`稳当的增强解决方案。

那么这俩有啥区别呢

1. `ypi`是设计阶段使用的工具，管理和维护借口
2. `swagger`是开发阶段使用的框架，帮助后端开发人员做后端的接口测试

## 前端编写

通过恶补前端知识过后。先空白，等到找到前端源码再说，这是一个打包过后的。太难认了。

## 后端编写

首先已经规划好了需要写点什么，并且用的技术栈也都知道。那么就可以开始第一步的编写了。

### 初始化

#### 创建工程

1. 创建父工程，即`sky-take-out`文件夹，其中包含`pom.xml`，还有子模块
2. 创建子模块
3. 子模块分位`sky-common`和`sky-pojo`和`sky-server`

#### 父工程

`sky-take-out`即主文件夹，里面仅有三个子模块和一个`pom.xml`。

正是这个`pom.xml`导入了所有基本模块。那么就来看看里面有什么吧。

![image-20240426001836936](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404260018165.png)

这里我是要给进行汇总，所以放到了一起。`.gitignore`这个文件中写的是`git`上传规则。`websocket.html`是为了测试长链接的文件。`README.md`不用多说，就是这个项目的综述。`DirectoryV3.xml`是装的插件，为了给文件夹添加描述信息。其他的王文建加是包括前端，小程序，文档，`idea`是这个控制信息。可以忽略。

来看`pom.xml`，并逐步分析。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- 上述是使用什么样的解码器，版本，还有声明，不用修改。 -->
    <modelVersion>4.0.0</modelVersion>
    <!-- 这个是关键，预设好的导入 springBoot模块，可以省略一些版本号的写入-->
    <parent>
        <artifactId>spring-boot-starter-parent</artifactId>
        <groupId>org.springframework.boot</groupId>
        <version>2.7.3</version>
    </parent>
    <!-- 这里是项目信息。项目全球唯一标识符，构建生成的路径也是依靠这个-->
    <groupId>com.sky</groupId>
    <!-- 构件标识符-->
    <artifactId>sky-take-out</artifactId>
    <!-- 项目产生的构建类型。不单单是有这些，还有用插件生成的构建类型-->
    <packaging>pom</packaging>
    <!-- 项目当前版本，格式为，住版本，次版本，增量版本，限定版本号-->
    <version>1.0-SNAPSHOT</version>
    <!-- 模块。构建成项目的一部分，列出每个木块元素是指向该模块的目录的相对路径-->
    <modules>
        <module>sky-common</module>
        <module>sky-pojo</module>
        <module>sky-server</module>
    </modules>
    <!-- 以值替代名称，-->
    <properties>
        <mybatis.spring>2.2.0</mybatis.spring>
        <lombok>1.18.30</lombok>
        <fastjson>1.2.76</fastjson>
        <commons.lang>2.6</commons.lang>
        <druid>1.2.1</druid>
        <pagehelper>1.3.0</pagehelper>
        <aliyun.sdk.oss>3.10.2</aliyun.sdk.oss>
        <knife4j>3.0.2</knife4j>
        <aspectj>1.9.4</aspectj>
        <jjwt>0.9.1</jjwt>
        <jaxb-api>2.3.1</jaxb-api>
        <poi>3.16</poi>
    </properties>
    <!-- 继承自该项目的所有子项目的默认以来信息。这里不是立马解析，而是当子项目声明一个依赖的时候，通过定位来定位到这里。group id 和 artifact id-->
    <dependencyManagement>
        <!--该元素描述项目的所有依赖，这些依赖组成了项目构建中的一个个环节，自动从项目定义的仓库中下载 -->
        <dependencies>
<!-- 这里是所有要以来的东西，这个是热加载的依赖，但貌似有点问题，虽然不影响使用，也算是一个完整的依赖书写了。-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-devtools</artifactId>
                <version>3.2.4</version>
            </dependency>
<!-- 这个是mybatis的依赖。是JDBC，支持SQL的查询，存储和高级映射-->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring}</version>
            </dependency>
<!-- 导入lombok，注意这玩意儿是配合着lombok插件使用的，就IDE的插件。作用就是消除Java的荣昌代码，尤其是简单的Java对象。在文档中都有详细的解释-->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok}</version>
            </dependency>
<!-- fastjson，是阿里写的一个序列化和反序列化的包-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>${fastjson}</version>
            </dependency>
<!-- 因为标准的Java库无法提供用于操纵契合心累的足够方法，所以这个是提供的额外的方法工具-->
            <dependency>
                <groupId>commons-lang</groupId>
                <artifactId>commons-lang</artifactId>
                <version>${commons.lang}</version>
            </dependency>
<!-- 数据库链接吃，有监控统计，防止SQL诸如，高性能，丰富的配置选项-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>${druid}</version>
            </dependency>
<!-- 分页插件，基于mybatis的分也插件，支持常见数据库-->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>${pagehelper}</version>
            </dependency>
<!-- 接口文档工具，swagger的升级版本。-->
            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-spring-boot-starter</artifactId>
                <version>${knife4j}</version>
            </dependency>
<!-- aop相关组件，下面这俩是一起的-->
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjrt</artifactId>
                <version>${aspectj}</version>
            </dependency>

            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>${aspectj}</version>
            </dependency>
<!-- 登陆验证用的，是jwt的框架。主要是生成令牌-->
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <version>${jjwt}</version>
            </dependency>
<!-- 这是用于云存储，看看就好-->
            <dependency>
                <groupId>com.aliyun.oss</groupId>
                <artifactId>aliyun-sdk-oss</artifactId>
                <version>${aliyun.sdk.oss}</version>
            </dependency>
<!-- 将Java对象转换为XML稳当的过程。-->
            <dependency>
                <groupId>javax.xml.bind</groupId>
                <artifactId>jaxb-api</artifactId>
                <version>${jaxb-api}</version>
            </dependency>
<!-- 处理Excel 的组件-->
            <!-- poi -->
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi</artifactId>
                <version>${poi}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi-ooxml</artifactId>
                <version>${poi}</version>
            </dependency>



            <!--微信支付-->
            <dependency>
                <groupId>com.github.wechatpay-apiv3</groupId>
                <artifactId>wechatpay-apache-httpclient</artifactId>
                <version>0.4.8</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

```

以上就是父工程中所有的内容。那么就正式开始子模块的初始化。

### 编写代码

这样打算，从管理端开始书写。

首先是`employee`类。

#### 管理端

##### employee

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

分析代码

从注释讲解

`@RestController`,是一个组合注解，包含了`@Controller`（生成一个请求处理的控制器）和`responseBody`（请求内容转换为JSON格式）两个注解功能。表示这是个控制器bean，并且是将函数的返回值直接填入HTTP响应体中。是REST风格的控制器。注释到类上表示这个类的所有方法只返回数据，而不进行视图跳转。

`@RequestMapping`请求映射的地址，存爱几个子注解实现REST风格

`@Slf4j`主要是少些`private static final Logger logger = LoggerFactory.getLogger(this.xxxx.class)`，即可以使用log日志功能，即可以使用logger.info和logger.debug，logger.error。

`@Api`这个是用于swagger,即接口设计，可以通过前后端联调，主要使用在类上，例如controoler,表示对类的说明

---

实际执行代码

@Autowired，自动导入对象到类中，被注入的类要被spring容器管理，即注入的类上方要有@Service注解。

当然这里用到了spring框架的管理。所以这里说明如何应用。

```java
//自动装配的bean
@Component//通用注解，标注任意类
@Repository//对应持久层即Dao层，主要是数据库相关
@Service//对应服务层，主要设计复杂逻辑，需要用到Dao层
@Controller//对应SpringMVC控制层，主要接收用户请求并调用Service层返回数据给前端
@RestController//是@Controller和@ResponseBody的合计，

//自动注入的
@Autowired//默认按类型查找，没查找到按名字查找
@Resource//刚好与上面的反过来
@Bean//一般用于方法上，当然不常用就是了，因为需要配置xml表
```

所以一直到注入完毕，就创建个组件，匹配URL，准备控制台打印，swagger的tag标识，实例化两个接口。

下面就是各个功能控制，登陆登出等。

针对登陆，就是调用登陆接口。通过前后端联调的功能，可以很清楚的发现传递进来的是json格式，传递出去的也是Json形式。

那么自然是需要简单的pojo类进行接收这些和发送这些。通过前面的 @ResponseBody就知道要返回的是json。

所以在函数的入参中的@RequestBody就知道要接受的类型是EmployDTO,下方是实现的代码，因为各种类型太多，各种功能所需要的数据不同，这里以登陆为举例。

```java
//employDTO
package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "员工登录时传递的数据模型")
public class EmployeeLoginDTO implements Serializable {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}

```

通过lombok的Data注解知道这是一个快速的bean。所需要的参数也只是在前后端联调中加了个注释。

然后再看返回的类型。

```java
package com.sky.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
```

跟上述一样，只不过这里多了一个implements Serializable 没有任何功能，只是做个标记，表明是串式返回数据。

这个代码很简单，就是封装了一个结果。转换为json的那种。所以发现并没有什么可怕的。

找到controller层所有的共同的，除了log.info,表明是在控制台输出

基本的书写规则已经搭建完毕

那就开始梳理怎么实现功能。

###### 登陆

```java
//在上方有一个@Service注解，用于表明Bean装配。
//类内部有一个注入employeeMapper，用于调用mysql的数据库操作
public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
```

可以看到功能书写非常简单，如果中间没有差错的话，就是把前端的数值进行传递，然后对比与数据库中的数据是否正确。都是简单的数值是否正确。至于传递出是实体类，因为在后续代码中还有相关操作。

至于getByUsername的操作，则是封装的mybatis接口

```xml
 <!--    根据用户名查询员工-->
    <select id="getByUsername" resultType="com.sky.entity.Employee">
        select * from employee where username = #{username}
    </select>
```

这里需要书写下查询语句，ID是要与方法名对应，返回类型需要指定。写全称。

然后这里有一些运行时的错误进行封装。当然都是调用runtimeException的类。只不过是给改了名字，因为具体看实现类的时候，就会发现就是调用了super，所以，也就这样。

另外就是账号锁定之类的，调用的是常量类，直接写常量也成。

###### 退出

很好理解，直接返回空即可，注意到这两个都是postmapping，所以是往前端发送创建信息。

###### 新增员工

调用save，这里出现了一个特殊的注解，@RequestBody，表明从发送过来的数据作为参数。

save的实现参数很有意思

```java
   @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        //设置账号的状态，默认正常状态 1表示正常 0表示锁定
        employee.setStatus(StatusConstant.ENABLE);

        //设置密码，默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置当前记录的创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

//        通过ThreadLocal获取用户信息
        Long currentId = BaseContext.getCurrentId();

        //设置当前记录创建人id和修改人id
        employee.setCreateUser(currentId);
        employee.setUpdateUser(currentId);

        employeeMapper.insert(employee);
    }

```

首先属性拷贝，还有设置状态什么的，就是这里的设置密码没整。也很简单，这边通过接收设置改为md5加密下就成。

然后就是通过函数设置当前时间。

再然后就是创建人的信息，虽然这里用Long接收，不过也无所谓了，主要看这个类。

```java
package com.sky.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
```

这里很简单，就是调用了ThreadLocal，用来传递类之间的信息。

最后把数据汇总给进行插入即可。

###### 分页查询

代码没什么新奇的，就是调用了mybatis的pagehelp插件，返回类型也就是一个字段加个列表。

```java
  @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
//        开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult(total, records);
    }
```

先启动，然后封装数据。至于里面用到的封装函数。就那样吧，自行查看源码，只需要知道得到的结果就行。

###### 启动员工账户

看源码就是更新在数据库中的信息。那这个在哪用呢，没错就是登陆的时候，有一个异常抛出，指的就是这个数值为0的时候。



###### 查询用户

嗯，没什么可说的，就是调用返回数据。但是这里为了安全，所以把密码给应藏了。

###### 更新员工信息

跟上述的更改员工账户的在涵盖一样。但是在这里多修改了更新时间和更新人。

---

通过以上的流程，对大致开发已经有所明晰，同时也可以看出get ，put，post的区别。当然还有删除员工，delectable。只不过是多一条对应的mysql的语句，当然还有根据删除人的username什么之类先查询是否存在之类的验证操作。

##### category分类管理

基本的实现逻辑跟employee的逻辑相同，不同的是在删除分类上。除了需要增添的条件外，果然如预期的一样。

##### dish相关接口

因为这里需要用到redis进行快速调用dao持久化，其实和普通的一样，新增菜品，菜品分类查询，菜品批量删除。查询菜品，修改菜品。

根据分类查询菜品，菜品的起售停售。

一切都很正常，但是这里多了一个清理缓存数据。为什么呢，

因为这里借用了redis进行优化查询，但因为实在是太无聊，因为就这点并发量实在是用不上，不过这里也给出优化方案。

```java
@GetMapping("/list")
@ApiOperation("根据分类id查询菜品")
public Result<List<DishVO>> list(Long categoryId){
    String key = "dish_"+ categortId;
    
    List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
    if(list != null && list.size() > 0){
        return Ressult.success(list);
    }
    Dish dish = new Dish();
    dish.setCategoryId(categoryId);
    dish.setStatus(StatusConstant.ENABLE);
    list = dishService.listWithFlavor(dish);
    redisTemplate.opsForValue().set(key,list);
    return Result.success(list);
}
```

这是没优化之前的代码

```java
@GetMapping("/list")
@ApiOperation("根据分类ID查询菜品")
public Result<List<Dish>> list(Long categoryId){
    List<DIsh> list = dishService.list(categoryId);
    return Result.success(list);
}
```

可以看出这其中优化只是在前方添加一个redis的查询，如果命中则就直接返回。

没有命中的话则是通过查询MySQL语句，并添加到缓存中继续返回。

那这里自然会好奇这调用的redis是怎么回事。

而这个就与管理店铺状态息息相关。

##### shop控制

嗯，跟用MySQL没啥区别，逻辑上来说。只不过内在上，就是直接操作redisTemlate操作即可。注入和取出。

主要的难点，就是如何使用redis,这里是使用的redis_SpringBoot_start。所以这里只是需要配好类直接操作redistemplate即可。

而这个编写也就是在config文件夹下有这个配置。doc中有详细说明。

##### order控制

订单管理接口

表面上看是没啥区别，但如果看实现内容，就会发现这里面牵扯了太多的内容，微信支付，查找资源，地址噗，长连接。

这里贴详细代码快

```java
package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.*;
import com.sky.entity.*;
import com.sky.exception.AddressBookBusinessException;
import com.sky.exception.OrderBusinessException;
import com.sky.exception.ShoppingCartBusinessException;
import com.sky.mapper.*;
import com.sky.result.PageResult;
import com.sky.service.OrderService;
import com.sky.utils.HttpClientUtil;
import com.sky.utils.WeChatPayUtil;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import com.sky.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatPayUtil weChatPayUtil;

    @Value("${sky.shop.address}")
    private String shopAddress;

    @Value("${sky.baidu.ak}")
    private String ak;

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @Override
    public OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO) {
//        异常情况的处理（收货地址为空、超出配送氛围、购物车为空）
        AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
        if (addressBook == null) {
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

//        检查用户的收货地址是否超出配送范围
        checkOutOfRange(addressBook.getCityName() + addressBook.getDistrictName() + addressBook.getDetail());

        Long currentId = BaseContext.getCurrentId();

        ShoppingCart shoppingCart = new ShoppingCart();
//        只能查询当前用户数据
        shoppingCart.setUserId(currentId);

//        查询当前用户的购物车数据
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
        if (shoppingCartList == null || shoppingCartList.size() == 0) {
            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        //构造订单数据
        Orders order = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO,order);
        order.setPhone(addressBook.getPhone());
        order.setAddress(addressBook.getDetail());
        order.setConsignee(addressBook.getConsignee());
        order.setNumber(String.valueOf(System.currentTimeMillis()));
        order.setUserId(currentId);
        order.setStatus(Orders.PENDING_PAYMENT);
        order.setPayStatus(Orders.UN_PAID);
        order.setOrderTime(LocalDateTime.now());
        orderMapper.insert(order);

//        订单明细数据
        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();
        shoppingCartList.forEach(cart->{
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setOrderId(order.getId());
            orderDetailList.add(orderDetail);
        });

//        向明细表中查询n条数据
        orderDetailMapper.insertBatch(orderDetailList);

//        清理购物车中的数据
        shoppingCartMapper.deleteByUserId(currentId);

//        封装返回结果
        OrderSubmitVO submitVO = OrderSubmitVO.builder()
                .id(order.getId())
                .orderNumber(order.getNumber())
                .orderAmount(order.getAmount())
                .orderTime(order.getOrderTime())
                .build();

        return submitVO;
    }

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     * @throws Exception
     */
    @Override
    public OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception {
//        当前登录用户id
        Long userId = BaseContext.getCurrentId();
        User user = userMapper.getById(String.valueOf(userId));

        String orderNumber = ordersPaymentDTO.getOrderNumber();
        Orders orders = orderMapper.getByNumberAndUserId(orderNumber, userId);

//        调用微信支付接口，生成预支付交易单
        JSONObject jsonObject = weChatPayUtil.pay(
                ordersPaymentDTO.getOrderNumber(),
                orders.getAmount(),
                "苍穹外卖订单" + orders.getId(),
                user.getOpenid()
        );

        if (jsonObject.getString("code") != null && jsonObject.getString("code").equals("ORDERPAID")) {
            throw new OrderBusinessException("该订单已支付");
        }

        OrderPaymentVO vo = jsonObject.toJavaObject(OrderPaymentVO.class);
        vo.setPackageStr(jsonObject.getString("package"));

        return vo;
    }

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    @Override
    public void paySuccess(String outTradeNo) {
//        当前登录用户id
        Long userId = BaseContext.getCurrentId();

//        根据订单号查询当前用户的订单
        Orders orderDB = orderMapper.getByNumberAndUserId(outTradeNo, userId);

//        根据订单id更新订单的状态、支付方式、支付状态、结账时间
        Orders orders = Orders.builder()
                .id(orderDB.getId())
                .status(Orders.TO_BE_CONFIRMED)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();
        orderMapper.update(orders);

        HashMap map = new HashMap();
        map.put("type", 1);
        map.put("orderId", orders.getId());
        map.put("content", "订单号：" + outTradeNo);

//        通过WebSocket实现来电提醒，向客户端浏览器推送消息
        webSocketServer.sendToAllClient(JSON.toJSONString(map));

    }

    /**
     * 历史订单查询
     *
     * @param pageNum
     * @param pageSize
     * @param status   订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     * @return
     */
    @Override
    public PageResult pageQueryForUser(int pageNum, int pageSize, Integer status) {
//        设置分页
        PageHelper.startPage(pageNum, pageSize);

        OrdersPageQueryDTO ordersPageQueryDTO = new OrdersPageQueryDTO();
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        ordersPageQueryDTO.setStatus(status);

//        分页条件查询
        Page<Orders> page = orderMapper.pageQuery(ordersPageQueryDTO);

        ArrayList<OrderVO> list = new ArrayList<>();

//        查询出订单明细，并封装入OrderVo进行响应
        if (page != null && page.getTotal() > 0) {
            page.forEach(orders -> {
                Long ordersId = orders.getId();

//                查询订单明细
                List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(ordersId);

                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders, orderVO);
                orderVO.setOrderDetailList(orderDetailList);
                list.add(orderVO);
            });
        }

        return new PageResult(page.getTotal(), list);
    }

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @Override
    public OrderVO details(Long id) {
//        根据id查询订单
        Orders orders = orderMapper.getById(id);

//        查询该订单对应的菜品/套餐明细
        List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(id);

//        将订单及其详情封装到OrderVo并返回
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        orderVO.setOrderDetailList(orderDetailList);

        return orderVO;
    }

    /**
     * 用户取消订单
     * @param id
     */
    @Override
    public void userCancelById(Long id) throws Exception {
//        根据id查询订单
        Orders orderDB = orderMapper.getById(id);

//        校验订单是否存在
        if (orderDB == null) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }

//      订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
        if (orderDB.getStatus() > 2) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(orderDB.getId());

//        订单处于待接单的状态下取消，需要进行退款
        if (orderDB.getStatus().equals(Orders.TO_BE_CONFIRMED)) {
//            调用微信支付退款接口
            weChatPayUtil.refund(
                    orderDB.getNumber(),
                    orderDB.getNumber(),
                    orders.getAmount(),
                    orders.getAmount()
            );

//            支付状态修改为 退款
            orders.setPayStatus(Orders.REFUND);
        }

//        更新订单状态，取消原因、时间
        orders.setStatus(Orders.CANCELLED);
        orders.setCancelReason("用户取消");
        orders.setCancelTime(LocalDateTime.now());
        orderMapper.update(orders);
    }

    /**
     * 再来一单
     * @param id
     */
    @Override
    public void repetition(Long id) {
//        查询当前用户id
        Long userId = BaseContext.getCurrentId();

//        根据订单id查询当前订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(id);

//        将订单详情对象转为购物车对象
        List<ShoppingCart> shoppingCartList = orderDetailList.stream().map(orderDetail -> {
            ShoppingCart shoppingCart = new ShoppingCart();

//            将原订单详情里面的菜品信息重新复制到购物车对象中
            BeanUtils.copyProperties(orderDetail, shoppingCart, "id");
            shoppingCart.setUserId(userId);
            shoppingCart.setCreateTime(LocalDateTime.now());

            return shoppingCart;
        }).collect(Collectors.toList());

//        将购物车对象批量添加到购物车
        shoppingCartMapper.insertBatch(shoppingCartList);
    }

    /**
     * 订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    @Override
    public PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());

        Page<Orders> pageQuery = orderMapper.pageQuery(ordersPageQueryDTO);

//        部分订单状态，需要额外返回订单菜品信息，将orders转化为orderVo
        List<OrderVO> orderVoList = getOrderVoList(pageQuery);
        return new PageResult(pageQuery.getTotal(), orderVoList);
    }

    /**
     * 各个状态的订单数量统计
     * @return
     */
    @Override
    public OrderStatisticsVO statistics() {
//        根据状态，分别查询出接待单，待派送、派送中的订单数量
        Integer toBeConfirmed = orderMapper.countStatus(Orders.TO_BE_CONFIRMED);
        Integer confirmed = orderMapper.countStatus(Orders.CONFIRMED);
        Integer deliveryInProgress = orderMapper.countStatus(Orders.DELIVERY_IN_PROGRESS);

//        将查询出的数据封装
        OrderStatisticsVO orderStatisticsVO = new OrderStatisticsVO();
        orderStatisticsVO.setToBeConfirmed(toBeConfirmed);
        orderStatisticsVO.setConfirmed(confirmed);
        orderStatisticsVO.setDeliveryInProgress(deliveryInProgress);

        return orderStatisticsVO;
    }

    /**
     * 接单
     * @param ordersCancelDTO
     */
    @Override
    public void confirm(OrdersCancelDTO ordersCancelDTO) {
        Orders orders = Orders.builder()
                .id(ordersCancelDTO.getId())
                .status(Orders.CONFIRMED)
                .build();
        orderMapper.update(orders);
    }

    /**
     * 拒单
     * @param ordersRejectionDTO
     */
    @Override
    public void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception {
//        根据id查询订单
        Orders ordersDB = orderMapper.getById(ordersRejectionDTO.getId());

//        订单只有存在且状态为2（待接单）才可以拒单
        if (ordersDB == null || !ordersDB.getStatus().equals(Orders.TO_BE_CONFIRMED)) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

//        支付状态
        Integer payStatus = ordersDB.getPayStatus();
        if (payStatus == Orders.PAID) {
//            用户已支付，需要退款
            String refund = weChatPayUtil.refund(
                    ordersDB.getNumber(),
                    ordersDB.getNumber(),
                    ordersDB.getAmount(),
                    ordersDB.getAmount()
            );
            log.info("申请退款：{}", refund);
        }

//        拒单需要退款，根据订单id更新订单状态，拒单原因，取消时间
        Orders orders = new Orders();
        orders.setId(ordersDB.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setRejectionReason(ordersRejectionDTO.getRejectionReason());
        orders.setCancelTime(LocalDateTime.now());

        orderMapper.update(orders);
    }

    /**
     * 取消订单
     * @param ordersCancelDTO
     */
    @Override
    public void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception {
//        根据id查询订单
        Orders orderDB = orderMapper.getById(ordersCancelDTO.getId());

//        支付状态
        Integer payStatus = orderDB.getPayStatus();
        if (payStatus == 1) {
//            用于已支付，需要退款
            String refund = weChatPayUtil.refund(
                    orderDB.getNumber(),
                    orderDB.getNumber(),
                    orderDB.getAmount(),
                    orderDB.getAmount()
            );
            log.info("申请退款：{}", refund);
        }

//      管理端取消订单需要退款，根据订单id更新订单状态、取消原因、取消时间
        Orders orders = new Orders();
        orders.setId(ordersCancelDTO.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setCancelReason(ordersCancelDTO.getCancelReason());
        orders.setCancelTime(LocalDateTime.now());
        orderMapper.update(orders);
    }

    /**
     * 派送订单
     * @param id
     */
    @Override
    public void delivery(Long id) {
//        根据id查询订单
        Orders orderDB = orderMapper.getById(id);

//        校验订单是否存在，并且状态为3
        if (orderDB == null || !orderDB.getStatus().equals(Orders.CONFIRMED)) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(orderDB.getId());

//        更新订单状态，状态转为派送中
        orders.setStatus(Orders.DELIVERY_IN_PROGRESS);

        orderMapper.update(orders);
    }

    /**
     * 完成订单
     * @param id
     */
    @Override
    public void complete(Long id) {
//        根据id查询订单
        Orders orderDB = orderMapper.getById(id);

//        校验订单是否存在，并且状态为4
        if (orderDB == null || !orderDB.getStatus().equals(Orders.DELIVERY_IN_PROGRESS)) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(orders.getId());

//        更新订单状态，状态转为完成
        orders.setStatus(Orders.CONFIRMED);
        orders.setDeliveryTime(LocalDateTime.now());

        orderMapper.update(orders);
    }

    /**
     * 用户催单
     * @param id
     */
    @Override
    public void reminder(Long id) {
//        查询订单是否存在
        Orders orders = orderMapper.getById(id);
        if (orders == null) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }

//        基于WebSocket实现催单
        HashMap map = new HashMap();
        map.put("type", 2);
        map.put("orderId", id);
        map.put("content", "订单号：" + orders.getNumber());
        webSocketServer.sendToAllClient(JSON.toJSONString(map));
    }

    /**
     * 部分订单状态，需要额外返回订单菜品信息，将orders转化为orderVo
     * @param page
     * @return
     */
    private List<OrderVO> getOrderVoList(Page<Orders> page) {
//        需要返回订单菜品信息，自定义OrderVo响应结果
        ArrayList<OrderVO> orderVOArrayList = new ArrayList<>();

        List<Orders> ordersList = page.getResult();
        if (!CollectionUtils.isEmpty(ordersList)) {
            ordersList.forEach(orders -> {
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders, orderVO);

                String orderDishStr = getOrderDishStr(orders);
                orderVO.setOrderDishes(orderDishStr);
                orderVOArrayList.add(orderVO);
            });
        }
        return orderVOArrayList;
    }

    /**
     * 根据订单id获取菜品信息字符串
     * @param orders
     * @return
     */
    private String getOrderDishStr(Orders orders) {
//        查询订单菜品详情信息（订单中的菜品和数量）
        List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(orders.getId());

//        将每一条订单菜品信息拼接为字符串（格式：宫保鸡丁*3；）
        List<String> ordewrDishList = orderDetailList.stream().map(orderDetail -> orderDetail.getName() + "*" + orderDetail.getNumber() + ";").collect(Collectors.toList());

//        将该订单对应的所有菜品信息拼接在一起
        return String.join("", ordewrDishList);
    }

    /**
     * 检查客户的收货地址是否超出配送范围
     * @param address
     */
    private void checkOutOfRange(String address) {
        HashMap map = new HashMap();
        map.put("address", shopAddress);
        map.put("output", "json");
        map.put("ak", ak);

//        获取店铺的经纬度坐标
        String shopCoordinate = HttpClientUtil.doGet("https://api.map.baidu.com/geocoding/v3", map);

        JSONObject jsonObject = JSON.parseObject(shopCoordinate);
        if (!jsonObject.getString("status").equals("0")) {
            throw new OrderBusinessException("店铺地址解析失败");
        }

//        数据解析
        JSONObject location = jsonObject.getJSONObject("result").getJSONObject("location");
        String lat = location.getString("lat");
        String lng = location.getString("lng");

//        店铺经纬度坐标
        String shopLngLat = lat + "," + lng;

        map.put("address", address);

//        获取用户地址的经纬度坐标
        String userCoordinate = HttpClientUtil.doGet("https://api.map.baidu.com/geocoding/v3", map);

//        数据解析
        location = JSON.parseObject("result").getJSONObject("location");
        lat = location.getString("lat");
        lng = location.getString("lng");

//        用户收货地址经纬度坐标
        String userLngLat = lat + "," + lng;

        map.put("orgin", shopLngLat);
        map.put("destination", userLngLat);
        map.put("steps_info", "0");

        //路线规划
        String json = HttpClientUtil.doGet("https://api.map.baidu.com/directionlite/v1/driving", map);

        jsonObject = JSON.parseObject(json);
        if (!jsonObject.getString("status").equals("0")) {
            throw new OrderBusinessException("配送线路规划失败");
        }

//        数据解析
        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray jsonArray = (JSONArray) result.get("routes");
        Integer distance = (Integer) ((JSONObject) jsonArray.get(0)).get("distance");

        if(distance > 5000){
            //配送距离超过5000米
            throw new OrderBusinessException("超出配送范围");
        }
    }
}

```

具体实现也跟遗忘没有什么不同。

注解@Value（）这个是获取到配置信息中的信息。就application.yml里面的信息。

查找地址，从MySQL中查询地址。

然后调查是否超出配送范围，则是调用百度地图，转到ckeckoutofrange的方法中，发现这玩意儿发送http链接，然后进行转换为json的信息，通过解析，并且有各种的信息比对，可以检查是否在范围内，这个方法写的很清晰，其中还用到了fastjson的解析的使用，来判断是否是超过范围。

然后是购物车，通过获取到当前用户的ID查询数据库中购物车的信息

开始创作订单，什么电话地址什么的全部穿进去，然后插入数据库。然后看订单的详细信息，按照匹配放到列表中。然后就是批量插入到数据表中。这里可以查看详细代码看一看关于sql的事务如何处理的。

清理购物车的数据，之后就是返回最终的订单结果。可谓是行云流水，看的头皮发麻。

之后就是订单支付，还是一样，通过获取到用户id，来进行查询。之后就是调用微信支付的接口，这里就跳转到微信支付类工具。看着是真特么复杂，但是不用管，因为这些在网上都有写好的，会调用工具即可。还有个指的注意的就是把json转换成bean，可以说是标注你叫成了。

修改订单状态，在这里用到了websocket，主要是进行数据传递，没什么新意，都是之前玩剩下的。

历史订单，也是同样的调用了分页。

如果用来总结的话，可以说这里是最复杂的业务之一，因为需要调用网络api，还要保持长链接发送信息。但也可以说是最简单的，因为这里都是复制粘贴。或者说复制过来把这些改写成自己的类就完事了。要传递什么要发送什么都是有固定的流程。所以一切OK

##### report报表

额

跟上述的订单一样。只是有些统计什么信息之类的。代码简单易懂。只不过这里引入了导出excel表的操作。说简单也简单，说复杂也复杂。不过原理就是通过加载原本就存在的模板excel文件，然后创建个新的放到了链接中，这里是要用到长链接。所以一切OK。关于Excel表的操作，在doc文件中有详细解释。

##### setmeal套餐

跟最基础的操作一样

##### 工作台

额，就是展示下数据。

----

Ok以上就是关于管理端的数据如何书写，这其中也有很多的问题，还引入了其他的插件之类的。

当然这里还有遗漏的，例如jwt是怎么进行拦截的，我们只书写了如何生成token还有检测token。但是要服务的，在那用的呢。自然是需要提前注册

在webmvcconfiguration中。这里是设置mvc框架的基本配置信息。

在这里![image-20240509084858719](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202405090848819.png)给注册进去了，书写格式也是如同这个，而且这个名称还是不能改的。

然后跳转过去，看是怎么书写的，其实就会发现还行。附属问题，通过threadlocal获取到当前现成的变量。跳转过去看源码就会发现就那意思。还有展示给前端的json数据，时间格式上不对，可以通过fastjson来更改，但不如把消息转换器给补充下，extendmessageconverters.



第二个问题就是globalExceptionHandler，这里是捕获一些异常类，然后就会发现这种没有调用过，原因也很简单，这个是捕获全局错误，自然是不需要自行调用。

第三个问题是在于关于每次更新账户信息，分类信息，之类的关于当前创建时间，创建用户，每一个都是一样的流程，那么自然来说，有没有一次写完随处都用，自然是可以，aop就是用来解决这个问题的。

通过书写一个接口，注解接口，

![image-20240509102123686](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202405091021797.png)

就是添加了这个注解，

具体写切面在这里

```java
package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面，实现公共字段自动填充处理逻辑
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     * com.sky.mapper包下包含AutoFill注解的所有类和方法
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {

    }

    /**
     * 前置通知，在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段自动填充");

//        获取到当前拦截的方法上的数据库操作类型
//        获取方法签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

//        获取方法上的注解对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);

//        获取数据库操作类型
        OperationType operationType = autoFill.value();

//        获取到当前被拦截的方法的参数---实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];

//        转变赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

//        根据当前不同的操作类型，为对应的属性通过反射来赋值
        if (operationType == OperationType.INSERT) {
//            为4个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

//              通过反射为对象赋值
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (operationType == operationType.UPDATE) {
//            为2个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

//              通过反射为对象赋值
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
}

```

怎么书写的呢，先通过aspect表明这是一个切面

再用pointcut表明需要的切入点。

然后在前置通知中给字段赋值。至于填入的信息则是通过反射来获取信息。

然后在哪用呢mapper层使用。![image-20240509103949043](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202405091039399.png)就这里天价格注解就ok,至于aop的调用逻辑，则就通过doc文档中给解释清楚。





第四个问题，那就是关于图片资源一类的上传，一般都是有外部图床什么的，这里用的是oss，阿里云的，反正用MySQL也成存，就是存完会被打死，哈哈。这里就不细说，因为这种配置每个版本每个写法，自己会改就成。



----

按理来说应该还有用户端，还有前端的编写，还有那些类型的介绍。但是当我完全查看玩代码之后。当完全搞懂admin的逻辑之后就可以看所有的代码了。没有难点。

好了，完毕，收工。

以上都是我一个字一个字敲出来的，诚然有很多还是一笔略过，但是都在doc中写的很详细。这里只是做一个大致的介绍。

这里确实还有两个坑，一个前端的编写，一个小程序的编写。

虽然我把原理都看了一遍。但是架不住我这里获得的源码是build之后的，别说看懂了，勉勉强强搞懂逻辑，从中复现所有的代码可能要玩死我。

但也正是学习前端的经验。后端真的就提供数据就完了，剩下的交给前端编写去。

完工！！！！！！！！！！！！！！！
