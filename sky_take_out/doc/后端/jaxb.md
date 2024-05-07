# jaxb

[Java](https://geek-docs.com/java/java-top-tutorials/1001100_java_index.html) JAXB 教程显示了如何使用 JAXB 库来处理 XML。 这些示例将 [Java](https://geek-docs.com/java/java-top-tutorials/1001100_java_index.html) 对象写入 XML 文件，并将 XML 数据读取到 Java 对象。

参考链接[Java操作XML(8)--JAXB使用 - 且行且码 - 博客园 (cnblogs.com)](https://www.cnblogs.com/wuyongyin/p/14317489.html)

先指明本次项目编写中没有用到此插件的读取，分析应该是架设到`SpringBoot`框架中，所以才在`pom.xml`中出现，但是没有使用。

## JAXB

用于 XML 绑定的 Java 体系结构（JAXB）是允许 Java 开发人员将 Java 类映射到 XML 表示形式的软件框架。 JAXB 支持将 Java 对象编组为 XML，然后将 XML 解组为 Java 对象。

在 Java 9 中，JAXB 已移至单独的模块`java.xml`中。 在 Java 9 和 Java 10 中，我们需要使用`--add-modules=java.xml.bind`选项。 在 Java 11 中，JAXB 已从 JDK 中删除，我们需要通过 Maven 或 Gradle 将其作为单独的库添加到项目中。

在我们的示例中，我们使用 JDK 11 和 Maven 创建我们的应用。

### JAXB相关的重要类与接口

**JAXBContext类** 应用的入口，用于管理XML/Java绑定信息。
**Marshaller接口** 将Java对象序列化为XML数据。
**Unmarshaller接口** 将XML数据反序列化为Java对象。

### JAXB注解

#### @XmlRootElement

类级别注解；将类映射为xml全局元素，也就是根元素。

**参数 name** 用于指定生成元素的名字，若不指定，默认使用类名小写作为元素名。
**参数 namespace** 用于指定生成的元素所属的命名空间。

#### @XmlType

类级别注解；常与@XMLRootElement，@XmlAccessorType一起使用。

**参数 name** 定义XML Schema中type的名称
**参数 namespace** 指定Schema中的命名空间
**参数 propOrder** 指定映射XML时的节点顺序，使用该属性时，必须列出JavaBean对象中的所有字段，否则会报错。
**参数 factoryClass** 指定UnMarshal时生成映射类实例所需的工厂类，默认为这个类本身
**参数 factoryMethod** 指定工厂类的工厂方法

#### @XmlAccessorType

类级别注解；定义这个类中的何种类型需要映射到XML。

**参数 value** 可以接受4个指定值：
XmlAccessType.FIELD：映射这个类中的所有字段到XML
XmlAccessType.PROPERTY：映射这个类中的属性（get/set方法）到XML
XmlAccessType.PUBLIC_MEMBER：将这个类中的所有public的field或property同时映射到XML（默认）
XmlAccessType.NONE：不映射

默认的容易导致"**类的两个属性具有相同名称**"的错误，一般可指定为XmlAccessType.FIELD

#### @XmlElement

字段，方法，参数级别注解；该注解可以将被注解的（非静态）字段，或者被注解的get/set方法对应的字段映射为本地元素，也就是子元素。

**参数 name** 用于指定映射时的节点名称，指定生成元素的名字，若不指定，默认使用方法名小写作为元素名。
**参数 namespace** 指定映射时的节点命名空间
**参数 required** 字段是否必须，默认为false
**参数 nillable** 是否处理空数据，默认为false
**参数 type** 定义该字段或属性的关联类型

#### @XmlAttribute

字段和方法级别的注解。该注解会将字段或get/set方法对应的字段映射成本类对应元素的属性。

**参数 name** 用于指定映射时的节点属性名称，若不指定，默认使用方法名小写作为元素名。
**参数 namespace** 指定映射时的节点属性命名空间
**参数 required** 该属性是否必须，默认为false

#### @XmlTransient

类，字段，方法级别的注解。定义某一字段或属性不需要被映射。

## JAXB 定义

编组是将 Java 对象转换为 XML 文档的过程。 解组是将 XML 文档读入 Java 对象的过程。 `JAXBContext`类提供客户端到 JAXB API 的入口点。 它提供用于编组，解组和验证的 API。

## JAXB POM 设置

以下 POM 文件包含必需的 JAXB JAR。

```xml
pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>JavaWriteXmlJaxbEx</groupId>
    <artifactId>JavaWriteXmlJaxbEx</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.2.11</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-core</artifactId>
            <version>2.2.11</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>2.2.11</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>

                    <archive>
                        <manifest>
                            <mainClass>com.zetcode.JavaWriteXmlJaxbEx</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

除了包括 JAXB 依赖项，我们还使用`maven-assembly-plugin`将所有依赖项打包到一个 JAR 中。

## JAXB 编写 XML 示例

在第一个示例中，我们将 Java 对象写入 XML 文件。

```java
package com.zetcode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")

// Defining order
@XmlType(propOrder = { "author", "name", "publisher", "isbn" })
public class Book {

    private String name;
    private String author;
    private String publisher;
    private String isbn;

    // Changing to title
    @XmlElement(name = "title")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
```



这是`Book` bean。 该 bean 将被转换为特定的 XML 标签。

```java
@XmlRootElement(name = "book")
```



使用`@XmlRootElement`注解，我们定义 XML 标签名称。

```java
@XmlType(propOrder = { "author", "name", "publisher", "isbn" })
```



通过`@XmlType`的`propOrder`属性，我们定义了子元素的顺序。

```java
@XmlElement(name = "title")
public String getName() {
    return name;
}
```

我们可以将默认元素名称更改为`title`

```java
package com.zetcode;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "Bookstore.java" is the root-element of our example
@XmlRootElement(namespace = "com.zetcode")
public class BookStore {

    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "bookList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "book")
    private ArrayList<Book> bookList;
    private String name;
    private String location;

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooksList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
```



`BookStore`是一个类，其中包含一个列表，我们在其中放置书本对象。

```java
@XmlRootElement(namespace = "com.zetcode")
public class BookStore {
```



我们用`@XmlRootElement`注释定义根元素。

```java
// XmLElementWrapper generates a wrapper element around XML representation
@XmlElementWrapper(name = "bookList")
// XmlElement sets the name of the entities
@XmlElement(name = "book")
private ArrayList<Book> bookList;
```

`@XmlElementWrapper`注释在 book 元素周围定义了包装元素。 `@XmlElement`注解定义包装器内的 XML 元素的名称。



```java
JavaWriteXmlJaxbEx.java
package com.zetcode;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class JavaWriteXmlJaxbEx {

    private static final String BOOKSTORE_XML = "src/main/resources/bookstore.xml";

    public static void main(String[] args) throws JAXBException {

        var bookList = new ArrayList<Book>();

        // create books
        var book1 = new Book();
        book1.setIsbn("978-0060554736");
        book1.setName("The Game");
        book1.setAuthor("Neil Strauss");
        book1.setPublisher("Harpercollins");
        bookList.add(book1);

        var book2 = new Book();
        book2.setIsbn("978-3832180577");
        book2.setName("Feuchtgebiete");
        book2.setAuthor("Charlotte Roche");
        book2.setPublisher("Dumont Buchverlag");
        bookList.add(book2);

        // create bookstore, assign books
        var bookstore = new BookStore();
        bookstore.setName("Fraport Bookstore");
        bookstore.setLocation("Livres belles");
        bookstore.setBookList(bookList);

        // create JAXB context and instantiate marshaller
        var context = JAXBContext.newInstance(BookStore.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(bookstore, System.out);

        // Write to File
        m.marshal(bookstore, new File(BOOKSTORE_XML));
    }
}
```



在示例中，我们创建书对象，将它们添加到书店，然后将书店转换为 XML 文件。

```java
// create books
var book1 = new Book();
book1.setIsbn("978-0060554736");
book1.setName("The Game");
book1.setAuthor("Neil Strauss");
book1.setPublisher("Harpercollins");
bookList.add(book1);

var book2 = new Book();
book2.setIsbn("978-3832180577");
book2.setName("Feuchtgebiete");
book2.setAuthor("Charlotte Roche");
book2.setPublisher("Dumont Buchverlag");
bookList.add(book2);
```



我们创建两个书本对象。

```java
// create bookstore, assign books
var bookstore = new BookStore();
bookstore.setName("Fraport Bookstore");
bookstore.setLocation("Livres belles");
bookstore.setBookList(bookList);
```

创建了一个书店并将书籍放入其中。

```java
// create JAXB context and instantiate marshaller
var context = JAXBContext.newInstance(BookStore.class);
```

我们创建一个新的`JAXBContext`。 我们传递新上下文对象必须识别的类的列表。 （在我们的例子中，这是一类。）

```java
var m = context.createMarshaller();
m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
```

从上下文中，我们得到了`createMarshaller()`的编组器。 我们设置一个属性以获取格式化输出。

```java
// Write to System.out
m.marshal(bookstore, System.out);

// Write to File
m.marshal(bookstore, new File(BOOKSTORE_XML));
```



我们将数据写入系统输出和文件中。



## JAXB 读取 XML 示例

在第二个示例中，我们将编组的数据读回到 Java 对象中。

```java
JavaReadXmlJaxbEx.java
package com.zetcode;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JavaReadXmlJaxbEx {

    private static final String BOOKSTORE_XML = "src/main/resources/bookstore.xml";

    public static void main(String[] args) throws JAXBException, 
            FileNotFoundException {

        // create JAXB context and unmarshaller
        var context = JAXBContext.newInstance(BookStore.class);
        var um = context.createUnmarshaller();

        var bookstore = (BookStore) um.unmarshal(new InputStreamReader(
                new FileInputStream(BOOKSTORE_XML), StandardCharsets.UTF_8));
        var bookList = bookstore.getBooksList();

        bookList.forEach((book) -> {
            System.out.println(book);
        });
    }
}
```



该示例从`bookstore.xml`文档中读取书籍。

```java
// create JAXB context and unmarshaller
var context = JAXBContext.newInstance(BookStore.class);
var um = context.createUnmarshaller();
```



我们创建一个 JAXB 上下文并获得一个新的解组器。

```java
var bookstore = (BookStore) um.unmarshal(new InputStreamReader(
        new FileInputStream(BOOKSTORE_XML), StandardCharsets.UTF_8));
```



使用`unmarshal()`，我们从 XML 文档中读取数据。

```java
var bookList = bookstore.getBooksList();

bookList.forEach((book) -> {
    System.out.println(book);
});
```

我们获得书籍列表并对其进行遍历。

```java
Book{name='The Game', author='Neil Strauss', publisher='Harpercollins', isbn='978-0060554736'}
Book{name='Feuchtgebiete', author='Charlotte Roche', publisher='Dumont Buchverlag', isbn='978-3832180577'}
```

这是输出。