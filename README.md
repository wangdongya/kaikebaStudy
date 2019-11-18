# 开课吧JavaEE架构师第一期学习记录

## 第一课
- 第一课总览的mybatis的源码，详情参见MainTest1的注释

## 第二课
- 自定义类型转换器：typeHandler
- 自定义对象工厂：DefaultObjectFactory
- 拦截器：org.apache.ibatis.plugin.Interceptor，其中plugin()方法中我们可以决定是否要进行拦截进而决定要返回一个什么样的目标对象；intercept()方法就是要进行拦截的时候要执行的方法。
- org.apache.ibatis.plugin.Plugin
- mybatis自定义拦截器，可以拦截接口只有四种：Executor.class，StatementHandler.class，ParameterHandler.class，ResultSetHandler.class

## 第三课
- 代理模式
> 代理模式的作用：将主要业务与次要业务进行松耦合组装
本质：监控行为特征
JDK代理模式实现：
    1.接口角色：定义所有需要被监听的行为
    2.接口实现类
    3.通知类：1).次要业务进行具体实现；2).通知JVM，当前被拦截的主要业务方法与次要业务方法应该如何绑定执行
    4.监控对象（代理对象）：1)。被监控的实例对象；2）.需要被监控的监控行为；3）.具体通知类实例对象

- 设计表的时候尽量不要放null值，尽量放一个默认的解释型的数据

- AOP模式：为了方便的实现代理模式
> 代理模式的实现步骤：1.声明接口：注册需要被监听的行为名称；2.接口实现类：扮演被监控的类，负责被监听方法的实现细节；3.InvocationHandler接口实现类：包含次要业务，以及次要业务
和主要业务的执行顺序，将次要业务与被监听方法绑定执行；4.代理监控对象：创建代理对象需要的参数是备件空格类的内存地址，被监控类实现的接口，InvocationHandler实现类的实例对象
Spring AOP:简化代理模式的实现步骤：1.声明接口：注册需要被监听的行为名称；2.接口实现类：扮演被监控的类，负责被监听方法实现细节；3.次要业务/主要业务
>> 切面：实际上指次要业务；目标对象：即当前需要监听的实例对象；织入：指的是手动实现代理模式的invoke中的行为，是将主要业务也次要业务绑定在一起的行为；连接点：指的是声明行为的接口中的需要
被监听的方法；织入点：接口实现类中真正被切面织入的方法；
spring AOP次要业务和主要业务绑定有两种方案：
通知（Advice）：可以完成简单的织入，是将接口中所有的方法与次要业务进行绑定，不灵活，一次性对所有的方法都进行拦截并执行切面逻辑；
    Spring AOP 通知种类：设置次要业务和主要业与被监听方法绑定执行顺序问题：前置通知，后置通知，环绕通知，异常通知
顾问（Advisor）：能够将通知以更复杂的方式织入目标对象中。
    1.是一种织入方式；2.实际上是对advise的一种封装；3.可以动态的将切面指定对应切入点
    类继承关系：参见图 Advisor PointCutAdvisor(可以灵活的指定当前接口下的哪一个实现类中的哪一个方法与次要业务进行绑定) Advice(次要业务) Pointcut(目标对象和目标对象方法) ClassFilter(判断当前被拦截对象是不是当前顾问需要管理的对象) 
    MethodMatcher(当前被拦截方法是不是我们所需要拦截的主要业务方法)
----
> OOP：面向对象编程：继承、封装、多态，继承主要解决纵向代码重复的问题
AOP：面向切面编程：通过动态代理技术（反射），对代码中的横向功能进行提取或者增强；
动态代理技术：基于JDK（需要实现接口）；基于CGlib（无限制条件，实际上最终是通过继承实现代理）
spring同时使用了jdk动态代理和CGlib动态代理，具体通过被代理对象是否实现接口来判断使用哪种代理方式
spring ioc容器的创建方式主要有两种：在java application中创建和在web application中创建
spring DI是基于IOC使用的，实际上就是在Bean工厂在生成Bean对象的时候，如果Bean对象需要装配一个属性，那么就会通过DI将属性值注给对象的行为
@Autowired：一部分功能是查找实例，从spring容器中根据类型获取对应的实例，另一部分功能就赋值，是将找到的实例，装配给另一个实例的属性值
什么是Web服务器（Servlet容器）？Tomcat,Jetty，Jboss等
什么是web容器？：ServletContext（Servlet上下文），Servlet三大域对象之一（生命周期范围最大的一个）
什么是spring容器？ApplicationContext（Spring上下文，实现了BeanFactory）
在spring的核心包：context，apo，webmvc，beans等中都有一个***/config包下面都有一个xxxNamespaceHandler，用于解析特定标签的xml文件

- 基于JDK和CGLIB的动态代理技术和基于aspectJ的静态代理技术
- spring主要做了几件事情：1.创建了一个Bean工厂，生产并管理bean；2.通过AOP技术，对Bean工厂中的Bean实例进行横切，功能增强；3.提供了很多AOP的具体实现，事务增强；4.内部提供了
很多企业应用的javaBean：jdbcTemplate，JDBCDaoSupport
- 需根据第十课笔记阅读spring源码
- 持久层一般只做跟数据库增删改查有关操作，不体现业务动作
- 阅读spring mvc源码，了解mvc(处理器，视图，前端控制器)和spring mvc三个组件（处理器映射器、处理器适配器、视图解析器）
- @requestMapping中consumes指定处理请求的提交内容类型（content-type）,例如application/json,text/html；
produces指定返回的内容类型，仅当request请求头中的（Accept）类型中包含该指定类型才返回
- 异常分为运行时异常和编译时异常
- spring mvc接参类型转换器：继承Convert并通过配置，spring boot自行研究下
- 异常处理器
- MIME：多用途互联网邮件扩展类型，也叫媒体类型，是设定某种扩展名的文件用一种应用程序来打开的方式类型，当该扩展名文件被访问的时候，
浏览器会自动使用指定应用程序来打开。格式：大类型/小类型，如text/html，image/jpg。
- 跨域：由于浏览器对于JavaScript的同源策略的限制，导致A网站不能通JS去访问B网站的数据，出现类跨域问题。
- 跨域指的是域名、端口、协议的组合不同就是跨域。
- 解决跨域的方式有多种，比如基于JavaScript的解决方式、基于jquery的jsonp方式，以及基于CORS的方式。JSONP和CORS的区别之一，就是
JSONP只能解决get方式提交，CORS支持GET和POST。
- CORS是一个W3C标准，全称是跨域资源共享，它允许浏览器向跨资源服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
CORS需要浏览器和服务器同时支持，目前，所有浏览器都支持该功能，IE浏览器不能低于IE10。
- CORS的原理：只需要向响应头header中注入Access-Control-Allow-Origin，这样浏览器检测到header中的Access-Control-Allow-Origin，就可以跨域操作了。
- Content-Type:MediaType，即Internet Media Type，互联网媒体类型，也叫做MIME类型，在http协议消息头中，使用Content-Type来表示具体请求中的媒体类型信息。
> 类型格式：type/subtype(;parameter)?type
>主类型，任意的字符串，如text，如果*号代表所有
>subtype：子类型，任意的字符串，如html，*代表所有
>parameter：可选，一些参数，如Accept请求头的q参数，Content-Type的charset参数，如Content-Type:text/html;charset-utf-8;
 常见的媒体格式类型如下：
    text/html ： HTML格式
    text/plain ：纯文本格式      
    text/xml ：  XML格式
    image/gif ：gif图片格式    
    image/jpeg ：jpg图片格式 
    image/png：png图片格式
   以application开头的媒体格式类型：
   application/xhtml+xml ：XHTML格式
   application/xml     ： XML数据格式
   application/atom+xml  ：Atom XML聚合格式    
   application/json    ： JSON数据格式
   application/pdf       ：pdf格式  
   application/msword  ： Word文档格式
   application/octet-stream ： 二进制流数据（如常见的文件下载）
   application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
   另外一种常见的媒体格式是上传文件之时使用的：
    multipart/form-data ： 需要在表单中进行文件上传时，就需要使用该格式
     以上就是我们在日常的开发中，经常会用到的若干content-type的内容格式。


-- 2019年6月18日 22:21:31 第三课50分钟 JDK动态代理
-- 2019年6月19日 07:30:35 第三课1小时04分钟 JDK动态代理模式
-- 2019年6月20日 07:28:28 第三课1小时26分钟 mybatis动态代理的实现
-- 2019年6月21日 22:52:43 第三课结束，mybatis进入尾声
-- 2019年6月22日 22:30:41 第四课08分钟左右
-- 2019年7月6日 22:42:39 第五课第一部分44分钟
-- 2019年7月8日 21:45:02 第五课第一部分结束
-- 2019年7月9日 21:34:20 第五课23分钟左右
-- 2019年7月10日 22:16:27 第五课一小时18分所有
-- 2019年7月11日 22:45:38 第五课结束
-- 2019年7月14日 15:42:27 第六课结束
-- 2019年7月14日 17:45:32 第七课结束
-- 2019年7月17日 12:30:06 第九课 第一段30分钟
-- 2019年7月18日 12:30:30 第九课第二段结束
-- 2019年7月19日 12:25:49 第九课第三段40分钟左右
-- 2019年7月22日 12:33:59 第九课第四段结束
-- 2019年7月22日 21:29:37 第十一课第一段45分钟左右
-- 2019年7月23日 12:29:33 第十一课第二段35分钟左右
-- 2019年7月23日 19:23:39 第十一课第三段16分钟左右
-- 2019年7月24日 12:37:19 第十二课第一段结束
-- 2019年7月24日 21:38:12 第十二课第三段结束
-- 2019年7月29日 21:38:45 第十三课第一段42分钟左右
-- 2019年7月30日 21:53:47 第十三课第一段结束

2019年6月18日 22:21:31 第三课50分钟 JDK动态代理
2019年6月19日 07:30:35 第三课1小时04分钟 JDK动态代理模式
2019年6月20日 07:28:28 第三课1小时26分钟 mybatis动态代理的实现
2019年6月21日 22:52:43 第三课结束，mybatis进入尾声
2019年6月22日 22:30:41 第四课08分钟左右
2019年7月6日 22:42:39 第五课第一部分44分钟
2019年7月8日 21:45:02 第五课第一部分结束
2019年7月9日 21:34:20 第五课23分钟左右
2019年7月10日 22:16:27 第五课一小时18分所有
2019年7月11日 22:45:38 第五课结束
2019年7月14日 15:42:27 第六课结束
2019年7月14日 17:45:32 第七课结束
2019年7月17日 12:30:06 第九课 第一段30分钟
2019年7月18日 12:30:30 第九课第二段结束
2019年7月19日 12:25:49 第九课第三段40分钟左右
2019年7月22日 12:33:59 第九课第四段结束
2019年7月22日 21:29:37 第十一课第一段45分钟左右
2019年7月23日 12:29:33 第十一课第二段35分钟左右
2019年7月23日 19:23:39 第十一课第三段16分钟左右
2019年7月24日 12:37:19 第十二课第一段结束
2019年7月24日 21:38:12 第十二课第三段结束
2019年7月29日 21:38:45 第十三课第一段42分钟左右
2019年7月30日 21:53:47 第十三课第一段结束


-----------------------------------------------------------------2019年10月8日 18:53:43-----------------------------------------------------------------------------------------------
# 孙玄百万年薪架构师
- 单体架构设计->水平分层架构设计->面向服务架构设计->微服务架构设计->服务网格化架构设计
- 不知道自己不知道->知道自己不知道->知道自己知道->不知道自己知道
- 针对具体的业务场景考虑架构如何选择
- 为什么要这样设计
> CAP模型：AP原则又称CAP定理，指的是在一个分布式系统中， Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性），三者不可得兼。
一致性（C）：在分布式系统中的所有数据备份，在同一时刻是否同样的值。（等同于所有节点访问同一份最新的数据副本）
可用性（A）：在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据更新具备高可用性）
分区容忍性（P）：以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择。
- 1.业务功能越来越多，越来越复杂；2.万物互联数据量越来越大；3.请求量越来越大、更高的用户体验要求；4.业务快速迭代持续交付的能力
- 单体架构->水平分层架构->面向服务架构（SOA）->微服务架构->服务网格架构
- 单体架构缺点：系统耦合度高、技术选型单一、开发效率越来越低下
- 数据库垂直维度分库，水平纬度分表；架构垂直方向拆分（业务纬度），水平方向拆分（功能纬度）
- rpc over tcp
- 水平分层：展示服务--网关服务--逻辑服务--数据服务四层分离
- protoBuf
- NIO：IO多路复用；epoll；AIO；
>网关层功能：1.请求鉴权；2.数据完整性检查；3.协议转换；4.路由转发；5.服务治理 
>业务逻辑层功能：1.业务逻辑判断；
>数据访问层功能：1.CRUD；2.ORM；3.Sharding(分库分表)；4.屏蔽底层存储差异；--升级到newSQL
>mysql默认只有主从复制的功能，如果需要实现读写分离，则需要借助其他方式
>数据库E-R分片：将有关系的记录都存储到一个库中


    



-- 2019年10月8日 19:30:01 3-1 01课27分钟 
-- 2019年10月11日 22:04:17 3-1 01课1小时 
-- 2019年10月12日 22:51:35 3-1 01课1小时36分钟

# java EE高级架构师
## redis
- String类型、hash类型、list类型、set类型、SortedSetl类型
- incr key:使用redis的自增来生成分布式系统的唯一主键，因为redis中该操作是原子的
- setnx key value: 实现分布式锁，如果不存在赋值成功，如果存在赋值失败
- hash主要用于存储对象数据，尤其是对象的属性经常发生增删改操作的对象。
- list:双向链表格式，有序重复
- redis的key的设计一般使用前缀:id来拼接表示，显示出该值的意义
- set：无序不重复集合
- SortedSet被称为zSet：每个元素都关联一个分数，来实现排序
- redis事务是通过一组命令集合组成的，redis一个命令是原子性的，关于事务的命令：MUTI,EXEC,DISCARD,WATCH
- redis事务不支持回滚
- redis通过watch实现乐观锁，即
> 正常的redis事务执行：
>multi
>set k1 v1
>set k2 v3
>exec
>实现乐观锁
>watch s1
>muti
>set k1 v1
>set k2 v3
>exec
>解释：redis实现事务是在执行事务开启命令muti之后，将后面的所有命令放到一个queue中，当执行exec命令时，原子性执行queue中的所有命令
>乐观锁是在开启事务之前watch s1，也就是在开启事务时到事务执行时如果s1的值发生了变化（监控s1），则会执行queue中的命令失败，否则执行成功，实现锁的效果
- 分布式锁的实现方式：1.基于数据库的乐观锁；2.基于zookeeper的分布式锁；3.基于redis的分布式锁
- 互斥性、同一性、避免死锁
-- 双重检查锁
-- SSE
-- 对restful的理解

-- sharding 分片；partition 分片算法

# zookeeper
## zookeeper概述
- zookeeper简介
>zookeeper是一个开源的分布式应用程序协调服务器，其为分布式系统提供一致性服务。其一致性是通过基于Paxos算法的ZAB协议完成的。其主要功能包括：配置维护、域名服务、分布式同步、集群管理等。
- 功能简介


## zookeeper的安装与集群搭建
## leader的选举机制


-- 2019年10月9日 21:04:34 第18课 02结束
-- 2019年10月11日 12:19:07 第19课 
-- 2019年10月14日 13:13:53 第十九课01 27分钟
-- 2019年10月15日 07:52:44 第十九课01结束

-- 2019年10月19日 21:52:34 第十九课02结束
-- 2019年10月20日 22:06:15 第21课结束
-- 2019年10月22日 19:28:55 第22课第二课14分钟
-- 2019年11月4日 07:57:41 第22课结束
-- 2019年11月4日 12:30:25 第23课第一段34分钟
-- 2019年11月4日21:15:58 第23课第一段结束
-- 2019年11月5日08:03:11 第23课第二课59分钟
-- 2019年11月5日12:28:19 第23课第三课32分钟
-- 2019年11月5日20:51:32 第24课第二课15分钟
-- 2019年11月7日08:17:06 第26课第一课1小时11分钟
-- 2019年11月7日12:27:51 第26课第一课2小时07分钟
-- 2019年11月7日 21:23:21 第26课第二课32分钟
-- 2019年11月8日 08:03:52 第26课第二课2小时21分钟
-- 2019年11月8日 12:37:49 第26课结束
-- 2019年11月10日 11:01:12 第27课结束
-- 2019年11月10日 17:00:19 第29课结束
-- 2019年11月10日 22:48:19 第30课第2课58分钟
-- 2019年11月11日 22:30:53 第30课第3课结束

-- 2019年11月14日 08:12:28 第31课第一段结束
-- 2019年11月18日 20:51:40 第32课24.4结束
