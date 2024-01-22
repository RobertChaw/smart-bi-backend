CREATE DATABASE IF NOT EXISTS bi;

USE bi;

CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100) COMMENT '用户名',
    userAccount VARCHAR(50)                                                      NOT NULL COMMENT '用户登录名',
    password    VARCHAR(255)                                                     NOT NULL COMMENT '密码',
    avatar      VARCHAR(255) COMMENT '头像',
    role        ENUM ('admin', 'user')                                           NOT NULL COMMENT '角色',
    createTime  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1) DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '用户数据表';

CREATE TABLE chart
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId      BIGINT                                                                                                   NOT NULL COMMENT '用户ID',
    goal        TEXT                                                                                                     NOT NULL COMMENT '生成目标',
    chartOption TEXT COMMENT '图表配置代码',
    data        TEXT COMMENT '图表原始数据',
    summary     TEXT COMMENT '摘要',
    status      ENUM ('waiting', 'running', 'succeeded', 'failed') DEFAULT 'waiting'                                     NOT NULL COMMENT '状态',
    reason      TEXT COMMENT '失败原因',
    createTime  TIMESTAMP                                          DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP                                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1)                                         DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '图表数据表';
