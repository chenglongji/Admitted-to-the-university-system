# Admitted-to-the-university-system
### 1. 架构

数据库：SQL_SERVICE

中间件：JDBC

开发语音：JAVA

设计：三层架构。第一层是UI界面，增删改查等操作。第二层是DAO层，第一层的操作统一由DAO层接收处理，对接数据库。第三层就是数据库层，存储数据使用


### 2. 缺点

改进点： 

1. 缓存中间件：redis

2. 负载均衡中间件：nginx

3. UI框架：vue
