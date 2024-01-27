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
#     data        TEXT COMMENT '图表原始数据',
    summary     TEXT COMMENT '摘要',
    status      ENUM ('waiting', 'running', 'succeeded', 'failed') DEFAULT 'waiting'                                     NOT NULL COMMENT '状态',
    reason      TEXT COMMENT '失败原因',
    createTime  TIMESTAMP                                          DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    updateTime  TIMESTAMP                                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    isDelete    TINYINT(1)                                         DEFAULT 0                                             NOT NULL COMMENT '逻辑删除标志（0表示未删除，1表示已删除）'
) COMMENT '图表数据表';

INSERT INTO user (username, userAccount, password, avatar, role)
VALUES ('Admin', 'admin', '423e2457ac72c165edba4a4ef2e9959a', 'admin_avatar.jpg', 'admin');
# password 123456

INSERT INTO chart (userId, goal, chartOption, summary, status)
VALUES (1, 'Sales Report',
        '{"title": {"text": "Monthly Sales Report"},"xAxis": {"type": "category","data": ["Jan", "Feb", "Mar", "Apr", "May", "Jun"]},"yAxis": {"type": "value"},"series": [{"data": [150, 200, 180, 250, 300, 280],"type": "bar"}]}',
        'Monthly sales report', 'waiting'),
       (1, 'User Activity',
        '{"title": {"text": "User Activity Analysis"},"xAxis": {"type": "category","data": ["Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6"]},"yAxis": {"type": "value"},"series": [{"data": [50, 80, 120, 90, 150, 110],"type": "line"}]}',
        'User activity analysis', 'running'),
       (1, 'Expense Analysis',
        '{"title": {"text": "Expense Breakdown"},"legend": {"orient": "vertical","left": "left"},"series": [{"name": "Expenses","type": "pie","radius": "50%","data": [{"value": 335, "name": "Food"},{"value": 310, "name": "Clothing"},{"value": 234, "name": "Housing"},{"value": 135, "name": "Transportation"},{"value": 1548, "name": "Other"}]}]}',
        'Expense breakdown', 'waiting');
