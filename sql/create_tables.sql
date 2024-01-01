CREATE DATABASE IF NOT EXISTS bi;

USE bi;

CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100)                                                     NOT NULL COMMENT '用户名',
    userAccount VARCHAR(50)                                                      NOT NULL COMMENT '用户登录名',
    password    VARCHAR(255)                                                     NOT NULL COMMENT '密码',
    avatar      VARCHAR(255) COMMENT '头像',
    role        ENUM ('admin', 'user')                                           NOT NULL COMMENT '角色',
    createTime  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1) DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '用户数据表';

CREATE TABLE chat
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255)                                                     NOT NULL COMMENT '名称',
    userId     BIGINT                                                           NOT NULL COMMENT '用户ID',
    createTime TIMESTAMP  DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete   TINYINT(1) DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '会话历史数据表';

CREATE TABLE chart
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId       BIGINT                                                                                                   NOT NULL COMMENT '用户ID',
    description  VARCHAR(255)                                                                                             NOT NULL COMMENT '描述',
    datasourceId BIGINT                                                                                                   NOT NULL COMMENT '数据源',
    chatHistory  TEXT COMMENT '会话记录',
    goal         TEXT                                                                                                     NOT NULL COMMENT '生成目标',
    sqlText      TEXT COMMENT '查询语句字符串',
    chartText    TEXT COMMENT '图表配置代码',
    chatId       BIGINT                                                                                                   NOT NULL COMMENT '聊天 ID',
    status       ENUM ('waiting', 'running', 'succeeded', 'failed') DEFAULT 'waiting'                                     NOT NULL COMMENT '状态',
    createTime   TIMESTAMP                                          DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime   TIMESTAMP                                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete     TINYINT(1)                                         DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '图表数据表';

CREATE TABLE dataSource
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)                                                     NOT NULL COMMENT '数据源名称',
    dbUrl          TEXT                                                             NOT NULL COMMENT '数据库链接',
    username       VARCHAR(255)                                                     NOT NULL COMMENT '用户名',
    password       VARCHAR(255)                                                     NOT NULL COMMENT '密码',
    datasourceType VARCHAR(255)                                                     NOT NULL COMMENT '数据源类型',
    userId         BIGINT                                                           NOT NULL COMMENT '用户ID',
    createTime     TIMESTAMP  DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime     TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete       TINYINT(1) DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志 (0: 未删除, 1: 已删除)'
) COMMENT '记录数据源信息';


/*SELECT ch.*, c.chartId, c.description AS chart_description
FROM chatHistory ch
         JOIN chart c ON ch.historyId = c.historyId
WHERE ch.userId = < your_user_id >
ORDER BY ch.createTime DESC, c.createTime DESC;*/