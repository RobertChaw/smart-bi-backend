CREATE TABLE user
(
    userId      BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100)                        NOT NULL COMMENT '用户名',
    userAccount VARCHAR(50)                         NOT NULL COMMENT '用户登录名',
    password    VARCHAR(255)                        NOT NULL COMMENT '密码',
    avatar      VARCHAR(255) COMMENT '头像',
    role        ENUM('admin', 'user') NOT NULL COMMENT '角色',
    createTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '用户数据表';

CREATE TABLE chatHistory
(
    historyId  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255)                        NOT NULL COMMENT '名称',
    userId     BIGINT                              NOT NULL COMMENT '用户ID',
    createTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '会话历史数据表';

CREATE TABLE chart
(
    chartId     BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId      BIGINT                              NOT NULL COMMENT '用户ID',
    description VARCHAR(255)                        NOT NULL COMMENT '描述',
    datasource  VARCHAR(100)                        NOT NULL COMMENT '数据源',
    chatHistory TEXT                                NOT NULL COMMENT '会话记录',
    target      TEXT                                NOT NULL COMMENT '目标',
    sqlText     TEXT                                NOT NULL COMMENT '查询语句字符串',
    chartText   TEXT                                NOT NULL COMMENT '图表配置代码',
    historyId   BIGINT,                             NOT NULL COMMENT '聊天历史 ID',
    createTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '图表数据表';

SELECT ch.*, c.chartId, c.description AS chart_description
FROM chatHistory ch
         JOIN chart c ON ch.historyId = c.historyId
WHERE ch.userId = <your_user_id>
ORDER BY ch.createTime DESC, c.createTime DESC;