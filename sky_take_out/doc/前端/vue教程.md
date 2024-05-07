# Vue教程

对于前端框架的使用。首先是要安装`node.js`用这个进行包管理。

## 创建项目

### 官方教程 

```shell 
# 查看版本
npm -v
2.3.0

#升级 npm
cnpm install npm -g


# 升级或安装 cnpm
npm install cnpm -g
# 最新稳定版
npm init vue@latest
```

这个是标准创建流程

安装并执行`create-vue`。

安装以来并启动开发服务器

```shell
 cd project-name
 npm install
 npm run dev
```

就可以在浏览器访问到了

发布到生产环境

```shell
npm run build
```

或者说用 `Vite`

这是一个`web`开发构建工具，可以实现闪电班的冷服务器启动。

```shell
npm init vit-app project-name
cd project-name
cnpm install
cnpm run dev
```



### 命令行创建

使用`vue-cli`，这是个官方命令行工具。用于快速搭建大型单页应用

```shell
# 全局安装 vue-cli
$ cnpm install --global vue-cli
# 创建一个基于 webpack 模板的新项目
$ vue init webpack my-project
# 这里需要进行一些配置，默认回车即可
This will install Vue 2.x version of the template.

For Vue 1.x use: vue init webpack#1.0 my-project

? Project name my-project
? Project description A Vue.js project
? Author runoob <test@runoob.com>
? Vue build standalone
? Use ESLint to lint your code? Yes
? Pick an ESLint preset Standard
? Setup unit tests with Karma + Mocha? Yes
? Setup e2e tests with Nightwatch? Yes

   vue-cli · Generated "my-project".

   To get started:
   
     cd my-project
     npm install
     npm run dev
   
   Documentation can be found at https://vuejs-templates.github.io/webpack
```

或者直接创建

```shell
vue create vue-project-name
cd vue-project-name
npm run serve
```

- **-p, --preset <presetName>**： 忽略提示符并使用已保存的或远程的预设选项
- **-d, --default**： 忽略提示符并使用默认预设选项
- **-i, --inlinePreset <json>**： 忽略提示符并使用内联的 JSON 字符串预设选项
- **-m, --packageManager <command>**： 在安装依赖时使用指定的 npm 客户端
- **-r, --registry <url>**： 在安装依赖时使用指定的 npm registry
- **-g, --git [message]**： 强制 / 跳过 git 初始化，并可选的指定初始化提交信息
- **-n, --no-git**： 跳过 git 初始化
- **-f, --force**： 覆写目标目录可能存在的配置
- **-c, --clone**： 使用 git clone 获取远程预设选项
- **-x, --proxy**： 使用指定的代理创建项目
- **-b, --bare**： 创建项目时省略默认组件中的新手指导信息
- **-h, --help**： 输出使用帮助信息

或者使用`UI`界面创建

```shell
vue ui
```

至于使用什么样的模块根据自己选择。

上述只是官网脚手架，快速创建一个`vue`项目



