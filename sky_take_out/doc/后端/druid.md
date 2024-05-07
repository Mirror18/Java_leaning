# 概述

原文地址[Spring Boot 整合 Druid 指南-阿里云开发者社区 (aliyun.com)](https://developer.aliyun.com/article/1157595)

## 简介

Java 程序很大一部分要操作数据库，为了提高性能操作数据库的时候，又不得不使用数据库连接池。

- Druid 是阿里巴巴开源平台上一个数据库连接池实现，结合了 C3P0、DBCP 等 DB 池的优点，同时加入了日志监控。
- Druid 可以很好的监控 DB 池连接和 SQL 的执行情况，天生就是针对监控而生的 DB 连接池。
- Druid 已经在阿里巴巴部署了超过600个应用，经过一年多生产环境大规模部署的严苛考验。
- Spring Boot 2.0 以上默认使用 Hikari 数据源，Hikari 与 Driud 都是当前 Java Web 上最优秀的数据源。
- Druid 的 [Github地址](https://github.com/alibaba/druid/)



## Druid 基本配置参数介绍

com.alibaba.druid.pool.DruidDataSource 基本配置参数

- name ：数据源名称

  如果存在多个数据源，监控的时候可以通过名字来区分开来

  如果没有配置，将会生成一个名字，格式是"DataSource-"+System.identityHashCode(this)

- jdbcUrl ：连接数据库的 url，不同数据库不一样

- username ：连接数据库的用户名

- password ：连接数据库的密码

- driverClassName ：数据库驱动类

  可配可不配，如果不配置 druid 会根据 url 自动识别 dbType，然后选择相应的 driverClassName（建议配置下）

- initialSize ：初始化时建立物理连接的个数，初始化发生在显示调用 init 方法，或者第一次 getConnection 时

- maxActive ：最大连接池数量

- maxIdle ：已经不再使用，配置了也没效果

- minIdle ：最小连接池数量

- maxWait ：获取连接时最大等待时间，单位毫秒

  配置了 maxWait 之后，缺省启用公平锁，并发效率会有所下降（可以通过配置 useUnfairLock=true 使用非公平锁）

- poolPreparedStatements ：是否缓存 preparedStatement，即 PsCache

  PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，而 mysql 则建议关闭

- maxOpenPreparedStatements ：要启用 PSCache，必须配置大于0

  当大于 0 时，poolPreparedStatements 自动触发修改为 true

  在 Druid 中，不会存在 Oracle 下 PSCache 占用内存过多的问题，可以把这个数值配置大一点，比如 100

- validationQuery ：用来检测连接是否有效的 sql，要求是一个查询语句

  如果 validationQuery 为null，testOnBorrow、testOnReturn 、testWhileIdle 都不会起作用

- testOnBorrow ：申请连接时执行 validationQuery 检测连接是否有效，做了这个配置会降低性能

- testOnReturn ：归还连接时执行 validationQuery 检测连接是否有效，做了这个配置会降低性能

- testWhileIdle ：建议配置为 true，不影响性能，并且保证安全性

  申请连接的时候检测，如果空闲时间大于 timeBetweenEvictionRunMills，执行 validationQuery 检测连接是否有效

- timeBetweenEvictionRunMillis ：间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒

  - Destory 线程会检测连接的间隔时间
  - testWhileIdle 的判断依据（详见 testWhileIdele 属性的说明）

- numTestsPerEvictionRun ：废弃，一个 DruidDataSource 只支持一个 EvicationRun

- minEvictableIdleTimeMillis ：一个连接在池中最小生存的时间，单位是毫秒

- connectionInitSqls ：物理连接初始化的时候执行 sql

- exceptionSorter ：当数据库抛出一些不可恢复的异常时，抛弃连接

- filters ：通过别名的方式配置扩展插件，属性类型是字符串

  常用的插件有：监控统计用的 filter（stat：监控统计，log：4:日志记录，wall：防御sql注入）

- proxyFilters ：类型是 List<com.alibaba.druid,filter.Filter>，如果同时配置 filter 和 proxyFilters，是组合关系（并非）



# Druid 集成方式（含依赖）



## Spring Boot 方式（推荐）

**依赖**

```
        <!--引入druid数据源-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.6</version>
        </dependency>
```

**yml 配置文件切换数据源**

- 方式1：

  ```
  spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/springboot_test?characterEncoding=utf8&serverTimezone=UTC
      username: root
      password: root
      # druid-spring-boot-starter 依赖自动生效 druid，可以不配置 type 属性，但建议配置
      type: com.alibaba.druid.pool.DruidDataSource
  ```

- 方式2：druid 专用配置（需要 druid-spring-boot-starter 依赖）

  ```
  spring:
    datasource:
      druid:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/springboot_test?characterEncoding=utf8&serverTimezone=UTC
        username: root
        password: root
  ```



## Spring 方式

**依赖**

```
        <!--引入druid数据源-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.6</version>
        </dependency>
```

**yml 配置文件切换数据源**

Spring Boot 2.0 以上默认使用 com.zaxxer.hikari.HikariDataSource 数据源，但可以通过 spring.datasource.type 指定数据源。

```
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://192.168.10.132:3306/testdb
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    
    # druid 数据源专有配置
      # 不是druid-spring-boot-starter依赖，SpringBoot默认是不注入druid数据源专有属性值的，需要自己绑定
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
    # 配置监控统计拦截的filters，去掉后监控界面sql无法统计。stat:监控统计 log4:日志记录 wall:防御sql注入
      # 如果运行时报错：ClassNotFoundException:orgapache.log4j.Priority，则导入log4j依赖即可
    filters: stat,wall,log4j
    # 自动往数据库建表
    #schema:
      #- classpath:department.sql
```

**配置类**：添加 DruidDataSource 组件到容器中，并绑定属性

```
@Configuration
public class DruidDataSourceConfig {

    /**
     * 添加 DruidDataSource 组件到容器中，并绑定属性
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
    public DataSource druid(){
        return  new DruidDataSource();
    }
}
```



# Druid 密码回调

现在很多项目都是把数据库的密码明文放在配置文件中，这样其实是不安全的，应该将密码加密后再放到配置中，这样可以一定程度的保护数据库密码的安全。

Druid 可以通过配置参数 passwordCallBack 来指定一个密码接口回调类进行密文密码解密操作。

实现步骤：

1. **自定义一个密码接口回调类**

   需要实现 DruidPasswordCallback 接口并重写接口方法 setProperties()

   ```
   import com.alibaba.druid.util.DruidPasswordCallback;
   import lombok.extern.slf4j.Slf4j;
   import org.apache.commons.lang3.StringUtils;
   import java.util.Properties;
   
   @Slf4j
   public class DruidCustomPasswordCallback extends DruidPasswordCallback {
   
       @Override
       public void setProperties(Properties properties) {
           super.setProperties(properties);
           // 获取配置文件中的已经加密的密码（spring.datasource.druid.connect-properties.password）
           String pwd = (String)properties.get("password");
           if (StringUtils.isNotEmpty(pwd)) {
               try {
                   // 这里的代码是将密码进行解密，并设置
                   String password = "解密后的明文密码";
                   setPassword(password.toCharArray());
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
   }
   ```

2. **yml 配置文件中配置密码接口回调类**

   注：

   - 可以通过配置文件中的 password-callback-class-name 或 password-callback 属性来配置密码接口回调类

     推荐使用 spring.datasource.druid.**password-callback-class-name**

     若是使用 spring.datasource.druid.password-callback 属性，则需要搭配相应的类型转换器才能用，不然报错：

     ```
     No converter found capable of converting from type [java.lang.String] to type [javax.security.auth.callback.PasswordCallback]
     ```

   - 根据密码接口回调类中的密文密码解密的逻辑，必须配置 spring.datasource.druid.connect-properties.password 属性才会进行密文密码解密操作并重置密码为解密后的明文密码

     若不配置 connect-properties.password ，则默认使用的 spring.datasource（.druid）.password 的属性值

   - 若是 Spring 集成 Druid，则可以在初始化 DruidDatabaseSource 时，手动配置其 passwordCallBack 属性值

   ```
   spring:
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/leyou?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
       username: root
       password: root11
   #    type: com.alibaba.druid.pool.DruidDataSource
       druid:
         # 配置密码接口回调类的全限定类名
         password-callback-class-name: com.duran.ssmtest.config.DruidCustomPasswordCallback
         # 配置密码接口回调类中方法入参Properties的值（自定义Map<String, String>）
         connect-properties:
           # 配置密文密码（传参到配置密码接口回调类中解密）
           password: aaa
   ```



# Druid 数据源监控

Druid 数据源具有监控的功能，并提供了一个 web 界面方便用户查看。

- 设置 Druid 的后台管理页面，比如 登录账号、密码 等；配置后台管理

  ```
  import com.alibaba.druid.pool.DruidDataSource;
  import com.alibaba.druid.support.http.StatViewServlet;
  import com.alibaba.druid.support.http.WebStatFilter;
  import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
  import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
  import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
  import org.springframework.boot.context.properties.ConfigurationProperties;
  import org.springframework.boot.web.servlet.FilterRegistrationBean;
  import org.springframework.boot.web.servlet.ServletRegistrationBean;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import javax.sql.DataSource;
  import java.util.Arrays;
  import java.util.HashMap;
  import java.util.Map;
  
  @Configuration
  public class DruidDataSourceConfig {
  
      /**
       * 添加 DruidDataSource 组件到容器中，并绑定属性
       */
      @Bean
      @ConfigurationProperties(prefix = "spring.datasource")
      @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
      public DataSource druid(){
          return new DruidDataSource();
      }
  
      /**
       * 配置 Druid 监控管理后台的Servlet；
       * 内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
       */
      @Bean
      @ConditionalOnClass(DruidDataSource.class)
      public ServletRegistrationBean statViewServlet(){
          // 这些参数可以在 http.StatViewServlet 的父类 ResourceServlet 中找到
          Map<String,String> initParams = new HashMap<>();
          initParams.put("loginUsername","admin");
          initParams.put("loginPassword","123456");
          // allow：Druid 后台允许谁可以访问。默认就是允许所有访问。
          initParams.put("allow",""); // 后面参数为空则所有人都能访问，一般会写一个具体的ip或ip段
          // deny：Druid 后台禁止谁能访问
          // initParams.put("deny","192.168.10.132");
  
          // 注册一个servlet，同时表明/druid/* 这个请求会走到这个servlet，而druid内置了这个请求的接收
          ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
          bean.setInitParameters(initParams);
          return bean;
      }
  
      /**
       * 配置一个web监控的filter
       */
      @Bean
      @ConditionalOnClass(DruidDataSource.class)
      public FilterRegistrationBean webStatFilter(){
          Map<String,String> initParams = new HashMap<>();
          // 这些不进行统计
          initParams.put("exclusions","*.js,*.css,/druid/*");
  
          FilterRegistrationBean bean = new FilterRegistrationBean();
          bean.setFilter(new WebStatFilter());
          bean.setInitParameters(initParams);
          bean.setUrlPatterns(Arrays.asList("/*"));
          return  bean;
      }
  }
  ```

- 运行应用，浏览器访问 ：[http://localhost](http://localhost/):8080/druid/login.html

## 苍穹外卖

没有以上的密码

使用的话只有在配置的时候组装了数据库。

```xml
spring:
  profiles:
    active: dev
  main:
    allow-circular-references: true
  datasource:
    druid:
      driver-class-name: ${sky.datasource.driver-class-name}
      url: jdbc:mysql://${sky.datasource.host}:${sky.datasource.port}/${sky.datasource.database}?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: ${sky.datasource.username}
      password: ${sky.datasource.password}
```

好了，完事了。

如果说不使用这个包会怎么样呢。

在mybatis的文章中有详细介绍。虽然是关于`spring`框架的。