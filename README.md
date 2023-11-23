# WeBlog

## 简介

一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。

![Weblog 后台仪表盘](https://img.quanxiaoha.com/quanxiaoha/168887767469647 "Weblog 后台仪表盘")

- 后端采用 Spring Boot 、Mybatis Plus 、MySQL 、Spring Sericuty、JWT、Minio、Guava 等；
- 后台管理基于 Vue 3.2 + Vite + Element Plus 纯手动搭建的管理后台，未采用任何 Admin 框架；
- 支持博客 Markdown 格式发布与编辑、文章分类、文章标签的管理；
- 支持博客基本信息的设置，以及社交主页的跳转；
- 支持仪表盘数据统计，Echarts 文章发布热图统计、PV 访问量统计；

## 相关地址

- GitHub 地址：[https://github.com/weiwosuoai/WeBlog](https://github.com/weiwosuoai/WeBlog)
- Gitee 地址：[https://gitee.com/AllenJiang/WeBlog](https://gitee.com/AllenJiang/WeBlog)
- 演示地址：http://116.62.199.48/
  - 游客账号：test
  - 游客密码：test
   > PS: 演示环境的服务器配置很低，带宽很小，若打开速度较慢，你可以部署到本地来访问哟~
  

## 演示截图

### 登录页

![Weblog 登录界面](https://img.quanxiaoha.com/quanxiaoha/168887753888612 "Weblog 登录界面")

### 仪表盘

![Weblog 后台仪表盘](https://img.quanxiaoha.com/quanxiaoha/168887767469647 "Weblog 后台仪表盘")

### 文章管理

![Weblog 文章管理](https://img.quanxiaoha.com/quanxiaoha/168888895520650 "Weblog 文章管理")

### 写博客

![写博客](https://img.quanxiaoha.com/quanxiaoha/168887786123214 "写博客")

### 前台首页

![博客首页](https://img.quanxiaoha.com/quanxiaoha/168950337932139 "博客首页")

### 博客详情

![博客详情页](https://img.quanxiaoha.com/quanxiaoha/168950345381866 "博客详情页")

### 归档页

![Weblog 归档页](https://img.quanxiaoha.com/quanxiaoha/168950349918012 "Weblog 归档页")

## 功能

### 前台

| 功能        | 是否完成 |
| ----------- | -------- |
| 首页        | ✅        |
| 分类列表    | ✅        |
| 标签标签    | ✅        |
| 博客详情    | ✅        |
| 站内搜索    | TODO     |
| 知识库 Wiki | TODO     |
| 博客评论    | TODO     |

### 后台

| 功能       | 是否完成 |
| ---------- | -------- |
| 后台登录页 | ✅        |
| 仪表盘     | ✅        |
| 文章管理   | ✅        |
| 分类管理   | ✅        |
| 标签管理   | ✅        |
| 博客设置   | ✅        |
| 评论管理   | TODO     |

## 模块介绍

![WeBlog 项目模块一览](https://img.quanxiaoha.com/quanxiaoha/169077198059341 "WeBlog 项目模块一览")

| 项目名            | 说明                                             |
| ----------------- | ------------------------------------------------ |
| weblog-springboot | 后端项目                                         |
| weblog-vue3       | 前端项目                                         |
| sql               | 数据库初始化脚本（包括表结构以及相关初始化数据） |

### 后端项目模块介绍

| 模块名               | 说明                 |
| -------------------- | -------------------- |
| weblog-module-admin  | 博客后台管理模块     |
| weblog-module-common | 通用模块             |
| weblog-module-jwt    | JWT 认证、授权模块   |
| weblog-web           | 博客前台（启动入口） |

## 技术栈

### 后端

| 框架                | 说明                     | 版本号      | 备注                                       |
| ------------------- | ------------------------ | ----------- | ------------------------------------------ |
| JDK                 | Java 开发工具包          | 1.8         | 它是目前企业项目比较主流的版本             |
| Spring Boot         | Web 应用开发框架         | 2.6.3       |                                            |
| Maven               | 项目构建工具             | 3.6.3       | 企业主流的构建工具                         |
| MySQL               | 数据库                   | 5.7         |                                            |
| Mybatis Plus        | Mybatis 增强版持久层框架 | 3.5.2       |                                            |
| HikariCP            | 数据库连接池             | 4.0.3       | Spring Boot 内置数据库连接池，号称性能最强 |
| Spring Security     | 安全框架                 | 2.6.3       |                                            |
| JWT                 | Web 应用令牌             | 0.11.2      |                                            |
| Lombok              | 消除冗余的样板式代码     | 1.8.22      |                                            |
| Jackson             | JSON 工具库              | 2.13.1      |                                            |
| Hibernate Validator | 参数校验组件             | 6.2.0.Final |                                            |
| Logback             | 日志组件                 | 1.2.10      |                                            |
| Guava               | Google 开源的工具库      | 18.0        |                                            |
| p6spy               | 动态监测框架             | 3.9.1       |                                            |
| Minio               | 对象存储                 | 8.2.1       | 用于存储博客中相关图片                     |
| flexmark            | Markdown 解析            | 0.62.2      |                                            |

### 前端

| 框架         | 说明                            | 版本号  |
| ------------ | ------------------------------- | ------- |
| Node         | JavaScript 运行时环境           | 18.15.0 |
| Vue 3        | Javascript 渐进式框架           | 3.2.47  |
| Vite         | 前端项目构建工具                | 4.3.9   |
| Element Plus | 饿了么基于 Vue 3 开源的组件框架 | 2.3.3   |
| vue-router   | Vue 路由管理器                  | 4.1.6   |
| vuex         | 状态存储组件                    | 4.0.2   |
| md-editor-v3 | Markdown 编辑器组件             | 3.0.1   |
| windicss     | CSS 工具类框架                  | 3.5.6   |
| axios        | 基于 Promise 的网络请求库       | 1.3.5   |
| Echarts      | 百度开源的数据可视化图表库      | 5.4.2   |

## 数据库初始化脚本

初始化脚本位于  `sql`  模块中，目录如下：

![](https://img.quanxiaoha.com/quanxiaoha/169077045489682)

小伙伴们在部署时，请先新建一个名为 `weblog` 的库，然后依次执行 `schema.sql` 和 `data.sql` 即可：

- `schema.sql` : 表结构脚本；
- `data.sql` : 初始化数据脚本，如登录用户信息、博客基本设置信息等；
