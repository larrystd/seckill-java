## 基于SpringBoot的简易秒杀系统

项目是SpringBoot工程，服务启动方法

1. 导seckill.sql文件到你的MySQL数据库

2. 配置seckill-dao/src/main/resources/application.properties文件，修改为自己的数据库配置

3. mvn clean package最外层的父工程

4. 命令行执行java -jar seckill-web/target/seckill-web-1.0.0-SNAPSHOT.jar

项目旨在学习，参考：https://github.com/qqxx6661/miaosha