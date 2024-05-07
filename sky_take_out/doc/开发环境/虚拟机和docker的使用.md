# 虚拟机的搭建

虽然只是要一个`linux`环境，可以通过`wsl`或者Windows下运行`docker`或者是用虚拟机。

这些方法我都使用过，为了以后可长久考虑，或者说单纯想适用生产环境。所以选择了用虚拟机上搭建`docker`的操作，既有`docker`的便捷，还可以复习`linux`的命令

## 安装虚拟机

市面上有`vmer`和`oracle VM virtualBox`，只是简单使用，所以选择了`virtualBox`，因为只是调用个环境，开源的就行。电脑还能带的动。

选择这个下载，然后选择要使用的镜像，我这里选择的是`ubuntu`，其实哪个都一个样。除了下载的时候要调用不同版本不同的下载命令之外，其他使用的，就是通用命令。

## 开始部署`docker`

参考文档：

> https://yeasy.gitbook.io/docker_practice/install/ubuntu
>
> 

### 先查看环境中是否有残存的`docker`软件。

```shell
sudo apt-get remove docker docker-engine docker.io containerd runc
```

添加docker 仓库，虽然自己编译也成，除了有概率出篓子。

### 这里是先下载一些必备的工具

```shell
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
```

### 然后添加`docker`的`gpg`密钥

```shell
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
```

### 查看是否有密钥

```shell
sudo apt-key fingerprint 0EBFCD88
```

### 添加`docker`的稳定版仓库

别问为什么这么复杂，问就是倒霉催的`ubuntu`的安全软件。

```shell
sudo add-apt-repository \
   "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/ \
  $(lsb_release -cs) \
  stable"
```

### 安装`docker `

更新 `apt`包索引

```shell
sudo apt-get update
```

这里如果出现问题，从` 2.3`重新开始。

---

### 安装`docker engine-community`和`containerd`

```shell
sudo apt-get install docker-ce docker-ce-cli containerd.io
```

### 启动`docker`

开机自启动

```shell
sudo systemctl enable docker
```

启动

```shell
udo systemctl start docker
```



### 验证 `docker`是否安装

```shell
sudo docker run hello-world
```

### 建立`docker`用户组

```shell
sudo groupadd docker
```

将当前用户加入到`docker`用户组

```shelle
sudo usermod -aG docker $USER
```

### 验证是否可以不用`root`权限

```shell
docker run --rm hello-world
```



---

以上就是`docker`的部署，会有不少问题，复制错误往网上查找就行。

## 使用`docker`

关于`docker`的介绍，可以理解为一个容器，一台什么都没装的电脑硬件。虽然是`linux`内核吧。不过当做一个电脑就行。然后就是镜像，可以理解为是装操作系统的时候用的`ISO`，可以快速搭建出软件环境。

由此可以做出很多的操作，例如下载镜像，运行容器，制作镜像上传至仓库。

但对于当前来说，需要明白的就是下载镜像，运行容器，开设端口，搭载数据卷。

前两个是将软件运行起来，后两个是外部可以访问到这个软件。

这里以`mysql`的搭建为例子。

### 下载镜像

下载镜像

```shell
docker pull mysql:5.7
```

列出镜像

```shell
docker images
```

![image-20240406020905341](https://raw.githubusercontent.com/Mirror18/imgage/img/note/202404060209408.png)

移除镜像

```shell
docker image rm hello-world
```



![image-20240406021015644](https://raw.githubusercontent.com/Mirror18/imgage/img/note/202404060210702.png)

只是举个例子，不可能真把`mysql`搭载好的镜像给移除

### 运行容器

```shell
docker run -p 3306:3306 --name mysql \
-v /home/mirror/mysql/log:/var/log/mysql \
-v /home/mirror/mysql/data:/var/lib/mysql \
-v /home/mirror/mysql/conf:/etc/mysql.d \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7
```

这里就是搭建好了`mysql`，而这里就是运行命令，映射端口，指定容器名字，挂载数据卷，设置密码，以后台的方式运行

```shell
-d：以后台的方式运行；
--name mysql：指定容器的名称为 mysql;
-p 3306:3306 将容器的 3306 端口挂载到宿主机的 3306 端口上；
-e MYSQL_ROOT_PASSWORD=root：指定 root 的密码为 root
-v /home/mirror/mysql/log:/var/log/mysql 挂载日志，注意这里不能直接复制，因为我用的是我的Linux用户名，前面是宿主机的路径，后面是docker内部的路径
-v /home/mirror/mysql/data:/var/lib/mysql \挂载数据
-v /home/mirror/mysql/conf:/etc/mysql.d \挂载设置
```

自此，可以拓展关于容器的操作

```shell
docker ps 展示运行中的容器目录
docker ps -a 展示所有的容器，不管是运行还是没运行
docker ps -ls 展示所有容器
docker stop mysql 停止MySQL的运行
docke rm mysql 删除mysql的容器
docker restart mysql 重启MySQL容器
docker start mysql 启动
```

但是运行总会是有可能出错的，那么就需要查看日志。

这里选择查看`docker`的日志

```
docker logs mysql
```

还有更复杂的，只不过当需要复杂的时候，也用不到我这份文档了。

好了，自此`docker`的内容配置完毕。

关于各个软件的详细配置会有单独的文档进行处理。



