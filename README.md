# AIGC 智能可视化分析平台

## 概述

结合 ChatGPT 智能可视化分析的系统，支持本地导入 excel 文件分析。

## 项目特点

* **ChatGPT 生成代码:** 利用自定义 Prompt 和用户输入数据，智能生成可视化图表数据。
* **队列化任务:** 使用 RabbitMQ 管理长耗时任务，实现消费者与生产者之间的解耦。
* **限流执行:** Redis + Redisson 实现分布式限流，防止用户恶意调用。
* **接口文档自动生成:** 利用 Knife4j 智能生成接口文档，显著减少接口文档维护时间。
* **分表存储:** 将 Chart 表大字段分表存储，提升数据库 I/O 读写性能。

## 技术栈

* SpringBoot 4
* Mybatis-Plus
* MySQL
* Redis + Redisson
* Knife4j
* RabbitMQ
* 腾讯云 COS
  Docker

## 一键部署

```bash
docker-compose up -d

#前端登陆面板：
#http://127.0.0.1:8080

# 默认用户: admin
# 默认密码: admin
```