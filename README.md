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