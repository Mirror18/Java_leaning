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
```



