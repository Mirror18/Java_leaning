## Jackson使用详解

### 介绍

> **Spring MVC 默认采用Jackson解析Json，尽管还有一些其它同样优秀的json解析工具，例如Fast Json、GSON，但是出于最小依赖的考虑，也许Json解析第一选择就应该是Jackson。**

**简介**

Jackson 是当前用的比较广泛的，用来序列化和反序列化 json 的 Java 的开源框架。Jackson 社区相对比较活跃，更新速度也比较快， 从 Github 中的统计来看，Jackson 是最流行的 json 解析器之一 。 Spring MVC 的默认 json 解析器便是 Jackson。 Jackson 优点很多。 Jackson 所依赖的 jar 包较少 ，简单易用。与其他 Java 的 json 的框架 Gson 等相比， Jackson 解析大的 json 文件速度比较快；Jackson 运行时占用内存比较低，性能比较好；Jackson 有灵活的 API，可以很容易进行扩展和定制。

Jackson 的 1.x 版本的包名是 org.codehaus.jackson ，当升级到 2.x 版本时，包名变为 com.fasterxml.jackson。

Jackson 的核心模块由三部分组成。

- **jackson-core**，核心包，提供基于"流模式"解析的相关 API，它包括 JsonPaser 和 JsonGenerator。 Jackson 内部实现正是通过高性能的流模式 API 的 JsonGenerator 和 JsonParser 来生成和解析 json。
- **jackson-annotations**，注解包，提供标准注解功能；
- **jackson-databind** ，数据绑定包， 提供基于"对象绑定" 解析的相关 API （ ObjectMapper ） 和"树模型" 解析的相关 API （JsonNode）；基于"对象绑定" 解析的 API 和"树模型"解析的 API 依赖基于"流模式"解析的 API。

**源码地址**：[FasterXML/jackson](https://link.zhihu.com/?target=https%3A//link.juejin.cn/%3Ftarget%3Dhttps%3A%2F%2Fgithub.com%2FFasterXML%2Fjackson.git)

### 使用

**导入依赖**

两种方式：一种是直接引入**spring-boot-starter-web**内嵌了jackson依赖，一种是引入**jackson**依赖

①

```java
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <version>2.7.0</version>
    </dependency>
```

②

```java
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>2.9.6</version>
</dependency>

<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>2.9.6</version>
</dependency>

<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.9.6</version>
</dependency>
```

**解释**

jackson-databind 依赖 jackson-core 和 jackson-annotations，所以可以只显示地添加jackson-databind依赖，jackson-core 和 jackson-annotations 也随之添加到 Java 项目工程中。

### ObjectMapper 对象映射器

**ObjectMapper** 是 Jackson 库中最常用的一个类，使用它可以进行 Java 对象和 JSON 字符串之间快速转换。如果你用过 FastJson，那么 Jackson 中的 `ObjectMapper`就如同 FastJson 中的 JSON 类。

这个类中有一些常用的方法：

- `readValue()` 方法可以进行 JSON 的反序列化操作，比如可以将字符串、文件流、字节流、字节数组等将常见的内容转换成 Java 对象。
- `writeValue()` 方法可以进行 JSON 的序列化操作，可以将 Java 对象转换成 JSON 字符串。

大多数情况下，`ObjectMapper` 的工作原理是通过 Java Bean 对象的 Get/Set 方法进行转换时映射的，所以正确编写 Java 对象的 Get/Set 方法尤为重要，不过 `ObjectMapper` 也提供了诸多配置，比如可以通过配置或者注解的形式对 Java 对象和 JSON 字符串之间的转换过程进行自定义。这些在下面部分都会介绍到。

### Jackson JSON Api操作

**ObjectMapper如何匹配JSON对象的字段和Java对象的属性**

默认情况下，Jackson通过将JSON字段的名称与Java对象中的getter和setter方法进行匹配，将JSON对象的字段映射到Java对象中的属性。 Jackson删除了getter和setter方法名称的“ get”和“ set”部分，并将其余名称的第一个字符转换为小写。

例如，名为brand的JSON字段与名为getBrand()和setBrand()的Java getter和setter方法匹配。 名为engineNumber的JSON字段将与名为getEngineNumber()和setEngineNumber()的getter和setter匹配。

如果需要以其他方式将JSON对象字段与Java对象字段匹配，则需要使用自定义序列化器和反序列化器，或者使用一些Jackson注解。

### 从JSON中获取Java对象

### JSON 字符输入流-->Java对象

**重点使用api：objectMapper.readValue(json, Person.class);**

```java
//java对象测试
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private int age;
    private List<String> skillsList;
}
//java对象转换为json格式
public class JsonToJava {

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]}";
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);
        //Person(name=swx, age=18, skillsList=[java, python, php])
        System.out.println(person);
    }
}
```

还可以从通过**Reader**实例加载的JSON中读取对象。示例如下：

```java
//java对象转换为json格式
public class JsonToJava {

    public static void main(String[] args) throws IOException {
        String json = "{\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]}";
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(json);
        Person person = objectMapper.readValue(reader, Person.class);
        //Person(name=swx, age=18, skillsList=[java, python, php])
        System.out.println(person);
    }
}
```

### JSON文件-->Java对象

在src下新建一个test.json文件

```json
{
  "name" : "swx",
  "age" : 19,
  "skillsList" : [
    "java",
    "c++"
  ]
}
//JSON文件-->Java对象
public class JsonToJava2 {

    public static void main(String[] args) throws IOException {
        File file = new File("src/test.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(file, Person.class);
        //Person(name=swx, age=19, skillsList=[java, c++])
        System.out.println(person);
    }
}
```

### JSON via URL--->Java对象

```java
ObjectMapper objectMapper = new ObjectMapper();

URL url = new URL("file:data/car.json");

Car car = objectMapper.readValue(url, Car.class);
```

示例使用文件URL，也可以使用HTTP URL（类似于[http://jenkov.com/some-data.json](https://link.zhihu.com/?target=http%3A//jenkov.com/some-data.json)）。

### JSON字节输入流-->Java对象

也可以使用ObjectMapper通过InputStream从JSON读取对象。 这是一个从InputStream读取JSON的示例：

json文件

```json
{
  "name" : "zxy",
  "age" : 66,
  "skillsList" : [
    "java",
    "python"
  ]
}
//JSON字节输入流-->Java对象
public class JsonToJava3 {

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("src/test2.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(in, Person.class);
        //Person(name=zxy, age=66, skillsList=[java, python])
        System.out.println(person);
    }
}
```

### JSON二进制数组-->Java对象

```java
//JSON二进制数组-->Java对象
public class JsonToJava4 {

    public static void main(String[] args) throws IOException {
        String json = "{\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]}";
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(bytes, Person.class);
        //Person(name=swx, age=18, skillsList=[java, python, php])
        System.out.println(person);
    }
}
```

### 4.1.6、JSON数组字符串-->Java对象数组

```java
//JSON数组字符串-->Java对象数组
public class JsonToJava5 {

    public static void main(String[] args) throws IOException {
        String json = "[{\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]},{\"name\":\"博尔特\",\"age\":55,\"skillsList\":[\"短跑\",\"两百米跑\"]}]";
        ObjectMapper objectMapper = new ObjectMapper();
        Person[] person = objectMapper.readValue(json, Person[].class);
        //Person(name=swx, age=18, skillsList=[java, python, php])
        //Person(name=博尔特, age=55, skillsList=[短跑, 两百米跑])
        for (Person person1 : person) {
            System.out.println(person1);
        }
    }
}
```

### JSON数组字符串-->List

**Jackson ObjectMapper**还可以从JSON数组字符串读取对象的**Java List**。 这是从JSON数组字符串读取对象列表的示例：

```java
//JSON数组字符串-->List
public class JsonToJava6 {

    public static void main(String[] args) throws IOException {
        String json = "[{\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]}," +
                "{\"name\":\"博尔特\",\"age\":55,\"skillsList\":[\"短跑\",\"两百米跑\"]}," +
                "{\"name\":\"迪迦\",\"age\":1500,\"skillsList\":[\"打怪兽\",\"射击\"]}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> personList = objectMapper.readValue(json, new TypeReference<List<Person>>() {
        });
        //Person(name=swx, age=18, skillsList=[java, python, php])
        //Person(name=博尔特, age=55, skillsList=[短跑, 两百米跑])
        //Person(name=迪迦, age=1500, skillsList=[打怪兽, 射击])
        personList.forEach(item -> {
            System.out.println(item);
        });
    }
}
```

### JSON字符串-->Map

**Jackson ObjectMapper**还可以从JSON字符串读取**Java Map**。 如果事先不知道将要解析的确切JSON结构，这种方法是很有用的。 通常，会将JSON对象读入Java Map。 JSON对象中的每个字段都将成为Java Map中的键，值对。

这是一个使用Jackson ObjectMapper从JSON字符串读取Java Map的示例：

```java
//JSON字符串-->Map
public class JsonToJava7 {

    public static void main(String[] args) throws IOException {
        String json = "{\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> objectMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
        //{name=swx, age=18, skillsList=[java, python, php]}
        System.out.println(objectMap);
    }
}
```

### Java对象-->JSON

**Jackson ObjectMapper**也可以用于从对象生成JSON。 可以使用以下方法之一进行操作：

- writeValue()
- writeValueAsString()
- writeValueAsBytes()

**重点使用api：objectMapper.writeValueAsString(person);**

```java
//java对象转换为json格式
public class JavaToJson {
    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person();
        person.setName("swx");
        person.setAge(18);
        person.setSkillsList(Arrays.asList("java","python","php"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        //{"name":"swx","age":18,"skillsList":["java","python","php"]}
        System.out.println(json);
    }

}
```

### Jackson 忽略字段

如果在进行 JSON 转 Java 对象时，JSON 中出现了 Java 类中不存在的属性，那么在转换时会遇到 `com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException` 异常。

使用 `objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)` 可以忽略不存在的属性。

```java
//Jackson 忽略字段
public class JsonToJava8 {

    public static void main(String[] args) throws IOException {
        String json = "{\"yyy\":\"xxx\",\"name\":\"swx\",\"age\":18,\"skillsList\":[\"java\",\"python\",\"php\"]}";
        ObjectMapper objectMapper = new ObjectMapper();
        //忽略字段设置
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Person person = objectMapper.readValue(json, Person.class);
        //{name=swx, age=18, skillsList=[java, python, php]}
        System.out.println(person);
    }
}
```

### Jackson 日期格式化

通过在字段上使用注解 `@JsonFormat` 来自定义时间格式。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒",timezone = "Asia/Shanghai")
    private Date date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private LocalDateTime localDateTime;
}
```

**提示**

如果运行后报错：

```java
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
            Java 8 date/time type `java.time.LocalDateTime` not supported by default: 
                add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" 
          to enable handling (through reference chain: com.wdbyte.jackson.Order["updateTime"])
```

这里我们需要添加相应的数据绑定支持包。

添加依赖：

```java
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.13.3</version>
</dependency>
```

然后在定义 ObjectMapper 时通过 `findAndRegisterModules()` 方法来注册依赖。

```java
//Jackson 日期格式化
public class JsonToJava9 {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        Order order = new Order();
        order.setId(1);
        order.setDate(new Date());
        order.setLocalDateTime(LocalDateTime.now());
        //将java转换成json字符串
        String json = objectMapper.writeValueAsString(order);
        //{"id":1,"date":"2023年07月28日 15时14分01秒","localDateTime":"2023-07-28 15:14:01"}
        System.out.println(json);
        //将json字符串转换成java对象
        Order orderObject = objectMapper.readValue(json, Order.class);
        //Order(id=1, date=Fri Jul 28 15:15:59 CST 2023, localDateTime=2023-07-28T15:15:59)
        System.out.println(orderObject);
    }
}
```

### Jackson 常用注解

### @JsonIgnore

使用 `@JsonIgnore`可以忽略某个 Java 对象中的属性，它将不参与 JSON 的序列化与反序列化。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private String name;
    @JsonIgnore
    private Integer age;
}
// @JsonIgnore
public class JsonToJava10 {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cat tom = new Cat("tom", 18);
        String json = objectMapper.writeValueAsString(tom);
        //因为对象属性age设置了被忽略：{"name":"tom"}
        System.out.println(json);
    }
}
```

### @JsonGetter

使用 `@JsonGetter`可以在对 Java 对象进行 JSON 序列化时自定义属性名称。

```java
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author https://www.wdbyte.com
 */
@Data
public class Cat {

    private String name;

    private Integer age;

    @JsonGetter(value = "catName")
    public String getName() {
        return name;
    }
}
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author https://www.wdbyte.com
 */
class CatTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testPojoToJson2() throws JsonProcessingException {
        Cat cat = new Cat();
        cat.setName("Tom");
        cat.setAge(2);
        String json = objectMapper.writeValueAsString(cat);
        System.out.println(json);
        Assertions.assertEquals(json, "{\"age\":2,\"catName\":\"Tom\"}");
    }
}
```

输出结果，`name` 已经设置成了 `catName`：

```json
{"age":2,"catName":"Tom"}
```

### @JsonSetter

使用 `@JsonSetter`可以在对 JSON 进行反序列化时设置 JSON 中的 key 与 Java 属性的映射关系。

```java
package com.wdbyte.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author https://www.wdbyte.com
 * @date 2022/07/17
 */
@Data
public class Cat {
    @JsonSetter(value = "catName")
    private String name;

    private Integer age;

    @JsonGetter(value = "catName")
    public String getName() {
        return name;
    }
}
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author https://www.wdbyte.com
 */
class CatTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testPojoToJson2() throws JsonProcessingException {
        String json = "{\"age\":2,\"catName\":\"Tom\"}";
        Cat cat = objectMapper.readValue(json, Cat.class);
        System.out.println(cat.toString());
        Assertions.assertEquals(cat.getName(), "Tom");
    }
}
Cat(name=Tom, age=2)
```

### Jackson 总结

- Jackson 是 Java 中比较流量的 JSON 处理库之一，它是 Spring 的默认 JSON 工具。
- Jackson 主要有三个模块组成，Streaming API 、Annotations 和 Data Binding 。
- Jackson 中的 ObjectMapper 类十分强大，可以进行 JSON 相关处理，同时可以结合注释以及配置进行自定义转换逻辑。
- Jackson 扩展性很好，如 CSV、XML、YAML 格式处理都对 Jackson 有相应的适配等。

## 苍穹外卖

实话说和fastjson都是一个样子，用于序列化和反序列化。

### 和fastjson的对比

#### 序列化

fastjson

```java
字段上用
    @JSONField(name="aaa")
    private int name;
使用的话
    JSON.toJSONString()
```

输出就是`aaa:name`这样的。当然也可以用serialize=false表明不使用序列化。ordinal表明输出的字段数据。

jackson 

则是通过`ObjectMapper.writeValueAsString`等一系列给序列化对象，而不需要标注。

当然有些东西不需要序列化，则可以通过@JsonIgnore来进行标注

#### 反序列化

fastjson

则是通过JSON.parseObject来进行反序列化。

jackson

就没序列化那么多类型，只需ObjectMapper().readValue()进行反序列化即可。

### 项目中的使用

主要用于公共类中的转换类

![image-20240504133349205](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202405041333336.png)

```java
package com.sky.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * 对象映射器:基于jackson将Java对象转为json，或者将json转为Java对象
 * 将JSON解析为Java对象的过程称为 [从JSON反序列化Java对象]
 * 从Java对象生成JSON的过程称为 [序列化Java对象到JSON]
 */
public class JacksonObjectMapper extends ObjectMapper {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    //public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public JacksonObjectMapper() {
        super();
        //收到未知属性时不报异常
        this.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        //反序列化时，属性不存在的兼容处理
        this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule simpleModule = new SimpleModule()
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

        //注册功能模块 例如，可以添加自定义序列化器和反序列化器
        this.registerModule(simpleModule);
    }
}

```

就可以发现这玩意都是预设好的。

---

这里还有个插曲

就是关于名字的差别

```java
public class SerializeTest {

    @JSONField(name = "gender")
    private String sex = "男";

    @JsonProperty(value = "name")
    private String userName = "张三";
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SerializeTest() {

    }

    public static void main(String[] args) throws Exception{
        String string = new ObjectMapper().writeValueAsString(new SerializeTest());
        System.out.println(string);
        String jsonString = JSONObject.toJSONString(new SerializeTest());
        System.out.println(jsonString);
    }
}
-----------------------------------------------
-----------------------------------------------
运行结果：
JsonProperty序列化：{"sex":"男","name":"张三"}
JSONField序列化：{"gender":"男","userName":"张三"}
```

关于@JsonProperty的使用，这个是注解包里的，与这两个无关。

但是因为spring框架的@RequestBody默认使用fasterxml中的converter方法将body中的json转换为对象 所以应该使用@JsonProperty注解。

所以一般来说没有特殊引用fastjson,一般用于字段对应的话，就用，@jsonproperty.