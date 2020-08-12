# YKYBlog


#### 项目链接：[www.youkaiyang.cn](http://39.99.245.234/)

#### 关于本地开发
可直接导入该项目于本地，修改配置文件中的数据库连接信息，导入附带数据库结构的SQL文件可直接生成所有表，如果有需要还需要开通阿里云相关服务

**当你克隆项目到本地后可使用手机号：18773672707，密码：111111进行登录，也可自行注册并将其修改为最高管理权限。**

如果发布项目后需要开启Https请求，配置配置文件后开启HttpToHttpsConfig类的@Confaguration注解即可

#### 项目介绍  
- 本项目采用[MyBlog](http://www.zhyocean.cn)的前端，特此感谢！
- 关于项目，对于学习Springboot是个挺不错的练手项目
- 开发前的一些准备工作，以及思考项目整体结构与思路
- 记录开发过程中遇到的一些难题以及bug
- 更新较慢的部分采用redis缓存增加访问速度
- 部分接口遵循restFul规范


## 页面展示

##### 首页展示

![](https://images.gitee.com/uploads/images/2020/0808/165611_4bd54a0e_6578938.png "front.png")
<br>
##### 文章编辑
![](https://github.com/SwimKY/YKYBlog/blob/master/image/article.png)
<br>
##### 后台管理
![](https://github.com/SwimKY/YKYBlog/blob/master/image/houtai.png)
<br>
##### 用户个人中心
![](https://images.gitee.com/uploads/images/2020/0808/165651_273e5b39_6578938.png "geren.png")

## 项目需求
#### 项目背景
对于初学Springboot的朋友来说，最好的一个学习方式就是那一个功能俱全的项目来练练手，通过练习来不断熟练技术发现自己的不足，并且也能很好的在编码过程中总结和发现问题、解决问题。使用Springboot开发的博客系统，简单并且实用，适合做练手项目。

#### 功能需求
###### 主页
- 博客汇总，以列表形式展示文章，并附上文章作者、发布日期、分类情况以及文章简要
- 能够以分类形式查看文章
- 能够以时间列表方式归档文章
- 可实现通过标签查找所有相关文章
- 个人介绍、联系方式
- 博客网站更新记录
- 友链链接

###### 后台管理
- 网站仪表盘，记录网站访客量情况
- 文章管理
1.分页展示文章信息
2.可对文章进行再编辑以及删除文章
- 发布文章
1.使用markdown编辑器，支持插入代码，插入图片等功能
2.文章可选择分类和标签，以及转载文章支持链接原作者文章
- 分类管理，支持增加、删除、修改分类
- 友情链接
1.支持增加友情链接
2.支持删除友情链接
- 反馈信息管理，可查看用户反馈信息

#### 安装部署需求
- 可以使用docker方式部署，也可支持-jar方式
- 使用springboot自带方式打包

#### 非功能需求
##### 性能需求
- 首页响应时间不超过2秒钟
- 文章页响应时间不超过3秒钟

## 项目设计

#### 总体设计
- **本项目用到的技术和框架**<br>
1.项目构建：Maven<br>
2.web框架：Springboot<br>
3.数据库ORM：Mybatis<br>
4.数据库连接池： Druid<br>
5.分页插件：PageHelper<br>
6.数据库：MySql<br>
7.缓存：Redis<br>
8.前端模板：Thymeleaf<br>
9.文章展示：Editor.md<br>

- **本项目中的关键点**<br>
1.采用Springboot开发，数据库使用连接池加orm框架的模式，对于系统的关键业务使用Redis缓存，加快相应速度。<br>
2.整体系统采用门户网站+后台管理+用户个人中心的方式搭建，门户网站展示博客内容以及博主介绍，后台管理用于编辑文章，查看反馈，管理评论留言。<br>

- **环境**

|  工具 | 名称 
| ------------ | ------------
| 开发工具     | IDEA 
|  语言        | JDK11、HTML、css、js 
| 数据库      | Mysql8.0
| 项目框架     |spring boot
| ORM       | Mybatis 
| 安全框架     | SpringSecurity 
| 缓存     | Redis 
| 项目构建    | Maven 
| 运行环境    | 阿里云Centos7 

#### 结构设计
![](https://images.gitee.com/uploads/images/2020/0808/174754_3fd808f4_6578938.png "结构.png")

对于熟悉Spring开发的朋友来说，相信对此结构也不会陌生。平时的开发过程中，结构设计是重要的环节，特别是协作开发的时候，明细的分包，模块化，可减少代码提交时的冲突。并且明确的结构有助于我们快速的寻找所对应的类。

## 业务设计
#### 发布文章流程

![](https://github.com/SwimKY/YKYBlog/blob/master/image/boke(01).png)
![](https://github.com/SwimKY/YKYBlog/blob/master/image/boke(02).png)
![](https://github.com/SwimKY/YKYBlog/blob/master/image/boke(03).png)

#### 登录流程
![](https://images.gitee.com/uploads/images/2020/0808/170449_5f436ee7_6578938.png "loogin.png")


#### 用户个人资料修改流程
![](https://images.gitee.com/uploads/images/2020/0808/170534_fddc2ea8_6578938.png "gerenxiugai.png")


## 打包、部署和运行
- 本项目采用Springboot的maven插件进行打包，打包结果：****.jar
- 部署方式：使用 nohup java -jar ******.jar >******.log 2>&1 &的方式，后台启动项目，并在该路径下生成运行日志

## 开发流程
###### 数据库CRUD
- controller层中编写前端接口，接收前端参数
- service层中编写所需业务接口，供controller层调用
- 实现service层中的接口，并注入mapper层中的sql接口
- 采用Mybatis的JavaConfig方式编写Sql语句。由于并没有使用Mybatis的逆向功能，需要自己手写所有sql语句
- 关于事务的实现，在启动类中开启事务，并在service层需要实现事务的业务接口上使用`@Transactional`注解
- 访问量的存储使用定时任务

###### 页面与展示
- 作为一名后端开发，对于前端编辑的能力有所欠缺，这里我使用了[MyBlog](www.zhyocean.cn)，极大的减少了页面的开发难度，特此感谢
- 前端页面与后端的交互主要是在controller包中，并使用Thymeleaf渲染页面。
- 自定义异常处理页面，通过重写`WebMvcConfigurerAdapter`实现自动跳转到404、403页面

###### 其他功能
- 使用lazyload插件实现页面图片懒加载
- 后台实时记录当天访客量，便于了解博客日常访问量
- 分析访问量最多的数据，主要在于文章访问部分，将文章放入redis缓存。每次编辑完文章后，更新缓存

###### 网站建设
- 服务器选用的是阿里云centos7
- 域名是阿里云上购买的.cn的域名

## 总结
#### 开发中遇到的难点
- 上传图片存储在阿里OSS中，没有返回地址，所以地址需要自己手动拼接
- 项目中最大的难点还是莫过于页面设计，但是使用了[MyBlog](www.zhyocean.cn)的前端，只需修改js请求和css就能实现自己所需要的样式

#### 博客网站优缺点
- 首先最大的一个缺点就是在前端页面设计过程中混用了一些Bootstrap，导致前端依赖难以改动，不便于后期修改
- 对于页面用户体验以及反馈功能的设计便于用户对于浏览过程中出现的问题进行反馈
- 后端部分明确的分工有利于项目的理解与维护

