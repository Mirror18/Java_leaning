# 定义
`POM` ：project object model;项目对象模型，是基本工作单元，xml  

POM 中可以指定以下配置：

1. 项目依赖   
2. 插件
3. 执行目标
4. 项目构建 profile
5. 项目版本
6. 项目开发者列表
7. 相关邮件列表信息


---


节点	描述


---



project	: 工程的根标签。  

---

modelVersion	:模型版本需要设置为 4.0。  


---
groupId	: 这是工程组的标识。它在一个组织或者项目中通常是唯一的。例如，一个银行组织 com.companyname.project-group 拥有所有的和银行相关的项目。


---
artifactId: 	这是工程的标识。它通常是工程的名称。例如，消费者银行。groupId 和 artifactId 一起定义了 artifact 在仓库中的位置。


---
version	: 这是工程的版本号。在 artifact 的仓库中，它用来区分不同的版本。例如：com.company.bank:consumer-banking:1.0 
com.company.bank:consumer-banking:1.1


使用以下命令来查看 Super POM 默认配置：
``` shell
mvn help:effective-pom
```
Maven 使用 `effective pom`（Super pom 加上工程自己的配置）来执行相关的目标，



# Maven 有以下三个标准的生命周期：

1. Clean 生命周期：  
   1. clean：删除目标目录中的编译输出文件。这通常是在构建之前执行的，以确保项目从一个干净的状态开始。
2. Default 生命周期（也称为 Build 生命周期）：

    1. validate：验证项目的正确性，例如检查项目的版本是否正确。
    2. compile：编译项目的源代码。
    3. test：运行项目的单元测试。
    4. package：将编译后的代码打包成可分发的格式，例如 JAR 或 WAR。
    5. verify：对项目进行额外的检查以确保质量。
    6. install：将项目的构建结果安装到本地 Maven 仓库中，以供其他项目使用。
    7. deploy：将项目的构建结果复制到远程仓库，以供其他开发人员或团队使用。
3. Site 生命周期：

   1. site：生成项目文档和站点信息。
   2. deploy-site：将生成的站点信息发布到远程服务器，以便共享项目文档。


# 构建配置文件
1. env.properties	：如果未指定配置文件时默认使用的配置。
2. env.test.properties	：当测试配置文件使用时的测试配置。
3. env.prod.properties	：当生产配置文件使用时的生产配置。

# Maven 构建 Java 项目
使用maven 创建 Java应用
```shell
mvn archetype:generate "-DgroupId=com.companyname.bank" "-DartifactId=consumerBanking" "-DarchetypeArtifactId=maven-archetype-quickstart" "-DinteractiveMode=false"
```
- DgroupId: 组织名，公司网址的反写 + 项目名称
- DartifactId: 项目名-模块名
- DarchetypeArtifactId: 指定 ArchetypeId，maven-archetype-quickstart，创建一个简单的 Java 应用
- DinteractiveMode: 是否使用交互模式


`mvn clean package`构建项目



# 自动化构建

```xml
<plugin>
      <artifactId>maven-invoker-plugin</artifactId>
      <version>1.6</version>
      <configuration>
         <debug>true</debug>
         <pomIncludes>
            <pomInclude>app-web-ui/pom.xml</pomInclude>
            <pomInclude>app-desktop-ui/pom.xml</pomInclude> 
         </pomIncludes>
      </configuration>
      <executions>
         <execution>
            <id>build</id>
            <goals>
               <goal>run</goal>
            </goals>
         </execution>
      </executions>
   </plugin>
```

``mvn clean package -U``



# 自动部署

```xml
<-->配置svn的路径</-->
<scm>
      <url>http://www.svn.com</url>
      <connection>scm:svn:http://localhost:8080/svn/jrepo/trunk/
      Framework</connection>
      <developerConnection>scm:svn:${username}/${password}@localhost:8080:
      common_core_api:1101:code</developerConnection>
   </scm>

   <distributionManagement>
       <->打包成的存储位置</->
      <repository>
         <id>Core-API-Java-Release</id>
         <name>Release repository</name>
         <url>http://localhost:8081/nexus/content/repositories/
         Core-Api-Release</url>
      </repository>
   </distributionManagement>
   <build>
      <plugins>
          <->这个插件干活</->
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-release-plugin</artifactId>
            <version>2.0-beta-9</version>
            <configuration>
               <useReleaseProfile>false</useReleaseProfile>
               <goals>deploy</goals>
               <scmCommentPrefix>[bus-core-api-release-checkin]-<
               /scmCommentPrefix>
            </configuration>
         </plugin>
      </plugins>
   </build>
```

1. `mvn release:clean`
2. `mvn release:rollback`
3. `mvn release:prepare`



# maven web 应用

```shell
mvn archetype:generate -DgroupId=com.companyname.automobile -DartifactId=trucks -DarchetypeArtifactId=maven-archetype-webapp  -DinteractiveMode=false
```



