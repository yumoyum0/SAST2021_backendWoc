# SAST.2021 后端 & Java WoC

第二阶段已完成

基于spring security的鉴权

### 基本功能：

1. 超管创建管理员以及提升普通用户权限

2. 超管删库跑路

3. 管理删除用户

4. 注册与登录

   

### 已修复漏洞

![image-20220215204454061](C:\Users\yumo\AppData\Roaming\Typora\typora-user-images\image-20220215204454061.png)

解决方案：修改pom.xml中mysql-connector-java部分的依赖,版本应在8.0.28及以上

```xml
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>
```

