# `mysql`的使用

关于`mysql`有太多的教程，对于苍穹外卖来说，`sql`可以说是没有参与进去，当然也是要看懂基础语法。

那么对于面试而言，要掌握到什么地步。

最最基本的，要学会使用。

第二步，要懂得原理。或者说是概念。才有机会去面试。

那在这份文档中，先完成基础的使用吧。

因为是使用`docker`，所以不用考虑关闭和开启。

不过进入`bash`命令还是需要费点操作。

```shell
docker exec -it mysql mysql -uroot -proot
```

```
-i 表示标准输入
-t 表示开启终端
所以这俩组合使用
第一个MySQL 表示的是名字
第二个是表示的是使用这个服务，标准登陆是 mysql -u root -p root
```

故同理进入到`mysql`容器中是要使用`docker exec -it mysql bash `

在登陆之后，要试着增添 `mysql`用户。

对比与普通的登陆，用户数据是要放到一个表中，所以，就是使用的是`mysql`这个数据库中的`user`这张表中。也就是执行一个插入操作就行。

是不是忘了最主要的配置。关于运行之后，最重要的就是更改配置

好像也没啥要改的，解释下参数吧。

```my.cnf
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
# 跳过域名解析
skip-name-resolve
```

## 数据库

主要有以下操作

### 创建数据库

```sql
create database if not exists 'sky_take_out';
```

### 展示所有数据库

```sql
show databases;
```

### 使用数据库

```sql
use sky_take_out;
```

### 删除数据库

```sql
drop database if exists sky_take_out;
```

## 数据表

在选定数据库中之后，需要对表进行操作

### 展示当前数据库下所有表

```sql
show tables;
show table status from sky_take_out ;
```

### 展示数据表的信息

```sql
show columns from employee;
show index from employee;
show table status from sky_take_out like 'employee%';
show table status from sky_take_out like 'employee%'/G;#表示按列打印
```

### 创建数据表

```sql
create table users (
	id  INT auto_increment primary key,
    username varchar(50) not null,
    email varchar(100) not null,
    birthdate date,
    is_active boolean default true
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

解释

```text
id: 用户 id，整数类型，自增长，作为主键。AUTO_INCREMENT 定义列为自增的属性,PRIMARY KEY 关键字用于定义列为主键。 
username: 用户名，变长字符串，不允许为空。
email: 用户邮箱，变长字符串，不允许为空。
birthdate: 用户的生日，日期类型。
is_active: 用户是否已经激活，布尔类型，默认值为 true。
ENGINE 设置存储引擎，CHARSET 设置编码。
```

### 删除数据表

```sql
drop table if exists employee;
```

### 插入数据

```sql
insert into employee(username,email,birthdate,is_active)
values('test','test@mirror.xyz','2000-01-01','true');
```

或者

```sql
insert into employee
values(null,'test','test@mirror.xyz','2000-01-01','true');
```

**NULL** 是用于自增长列的占位符，表示系统将为 **id** 列生成一个唯一的值。

插入多行数据

```sql
INSERT INTO employee (username, email, birthdate, is_active)
VALUES
    ('test1', 'test1@runoob.com', '1985-07-10', true),
    ('test2', 'test2@runoob.com', '1988-11-25', false),
    ('test3', 'test3@runoob.com', '1993-05-03', true);
```

### 更新数据表

```sql
update employee
set status = 0 ,sex = 1
where id = 11;
```

或者使用表达式

```sql
UPDATE products
SET price = price * 1.1
WHERE category = 'Electronics';
```

或者子查询

```sql
SET total_purchases = (
    SELECT SUM(amount)
    FROM orders
    WHERE orders.customer_id = customers.customer_id
)
WHERE customer_type = 'Premium';
```

### 删除数据

```sql
delete from employee
where status = 0;
```

或者使用子查询

```sql
DELETE FROM customers
WHERE customer_id IN (
    SELECT customer_id
    FROM orders
    WHERE order_date < '2023-01-01'
);
```

### 插入或者替换数据

```sql
REPLACE INTO students (id, class_id, name, gender, score) VALUES (1, 1, '小明', 'F', 99);
```





### 修改表名和数据表字段

```sql
alter table employee 
rename to  employees
```

还有 `modify` 和 `change`、`drop`、`add`

## 查询数据

### 常规查询

可以说是重中之重了。

关于这个的书写，尽量以一个例子去表明。

```sql
select * from employee; # 查询所有列的数据
select username, sex from employee order by create_time desc limit 0,10;# 或者 limit 10 offet 0
select * from employee where username like 'm%' and sex= 1 or statu != 1;
select * from employee where create_time between '2024-03-24' and '2024-03-27';
select * from employee where sex in (0,1);

```

### 聚合和分组查询

聚合查询

| 函数 | 说明                                   |
| :--- | :------------------------------------- |
| SUM  | 计算某一列的合计值，该列必须为数值类型 |
| AVG  | 计算某一列的平均值，该列必须为数值类型 |
| MAX  | 计算某一列的最大值                     |
| MIN  | 计算某一列的最小值                     |

分组查询 `group by`

```sql
select sex ,count(0) num from employee group by sex;
```

至于`count(0)`可以理解为`count(*)`

聚合查询没什么可补充的，一般也出不了什么事，但分组查询不同，如果要展示的数据列不能被分组展示，就会报错。举个例子就是

```sql
select id , sex, count(0) as num from employee group by sex;
```

就会出现异常，因为不能同时分组，并且还展示 `id`。

### 多表查询

就是同时展示两张表的内容

```sql
SELECT
    s.id sid,
    s.name,
    s.gender,
    s.score,
    c.id cid,
    c.name cname
FROM students s, classes c;

```

如果不做限制的话，会显示所有的表，数据量是以乘积的形式展现的。也就是包含了一堆用不到的数据

```sql
SELECT
    s.id sid,
    s.name,
    s.gender,
    s.score,
    c.id cid,
    c.name cname
FROM students s, classes c
WHERE s.gender = 'M' AND c.id = 1;
```

所以这个也就是展示出正确的数据。

### join 和 union

join是两张表根据条件相同的部分合并生成一个记录集

内链接

```sql
SELECT s.id, s.name, s.class_id, c.name class_name, s.gender, s.score
FROM students s
INNER JOIN classes c
ON s.class_id = c.id;
```

就是把`classes`与`students`两张表通过相同的`id`生成一个新的表，最最重要的就是这个`id`相同，有了这个条件，才能说两张表有联系。

内联系的意思就是以第一张表的`id`数量，数值为主。

外联系就是以第二张表的数量，数值为主。





INNER JOIN只返回同时存在于两张表的行数据，由于`students`表的`class_id`包含1，2，3，`classes`表的`id`包含1，2，3，4，所以，INNER JOIN根据条件`s.class_id = c.id`返回的结果集仅包含1，2，3。

RIGHT OUTER JOIN返回右表都存在的行。如果某一行仅在右表存在，那么结果集就会以`NULL`填充剩下的字段。

LEFT OUTER JOIN则返回左表都存在的行。如果我们给students表增加一行，并添加class_id=5，由于classes表并不存在id=5的行，所以，LEFT OUTER JOIN的结果会增加一行，对应的`class_name`是`NULL`：



好了，所有的使用已经结束，至于事务的处理，还有索引，都是无关紧要的，先足够掌握这些就足够使用了。

## 结尾

然后就是关于`mysql`的使用，实话说用不到上面的命令，因为现在都是可视化软件展示，用软件展示。

这里的软件推荐`navicat premium`，至于数据表的设计`pdshell`。连接上之后就可以可视化完成上面的一系列操作。但作为经常使用数据表的，还是说要掌握上面的命令，因为上述只是把这些命令给简化了，实质上还是调用命令。

参考文档

1. https://www.runoob.com/mysql/mysql-index.html
2. https://www.liaoxuefeng.com/wiki/1177760294764384/1179611198786848

