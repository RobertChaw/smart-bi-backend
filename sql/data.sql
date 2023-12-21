USE bi;

-- user 表
INSERT INTO user (username, userAccount, PASSWORD, avatar, role, createTime, updateTime, isDelete)
VALUES
    ('John Doe', 'john_doe123', 'password123', 'avatar1.jpg', 'user', '2023-01-01 12:00:00', '2023-01-01 12:00:00', 0),
    ('Jane Smith', 'jane_smith456', 'password456', 'avatar2.jpg', 'admin', '2023-01-02 14:30:00', '2023-01-02 14:30:00', 0),
    ('Bob Johnson', 'bob_johnson789', 'password789', 'avatar3.jpg', 'user', '2023-01-03 10:45:00', '2023-01-03 10:45:00', 0),
    ('Alice Lee', 'alice_lee101', 'password101', 'avatar4.jpg', 'admin', '2023-01-04 08:15:00', '2023-01-04 08:15:00', 0),
    ('Eva White', 'eva_white202', 'password202', 'avatar5.jpg', 'user', '2023-01-05 16:20:00', '2023-01-05 16:20:00', 0);

-- chat 表
INSERT INTO chat (name, userId, createTime, updateTime, isDelete)
VALUES
    ('Team Chat', 1, '2023-01-01 12:30:00', '2023-01-01 12:30:00', 0),
    ('Project Discussion', 2, '2023-01-02 15:00:00', '2023-01-02 15:00:00', 0),
    ('General Chat', 3, '2023-01-03 11:00:00', '2023-01-03 11:00:00', 0),
    ('Management', 4, '2023-01-04 08:30:00', '2023-01-04 08:30:00', 0),
    ('Tech Talk', 5, '2023-01-05 16:45:00', '2023-01-05 16:45:00', 0);

-- chart 表
INSERT INTO chart (userId, description, datasourceId, chatHistory, goal, sqlText, chartText, chatId, createTime, updateTime, isDelete)
VALUES
    (1, 'Sales Overview', 1, '...', 'Increase sales by 10%', 'SELECT * FROM sales_data', '{chart_config}', 1, '2023-01-01 13:00:00', '2023-01-01 13:00:00', 0),
    (2, 'User Activity', 2, '...', 'Analyze user engagement', 'SELECT * FROM user_activity', '{chart_config}', 2, '2023-01-02 15:30:00', '2023-01-02 15:30:00', 0),
    (3, 'Product Metrics', 3, '...', 'Improve product performance', 'SELECT * FROM product_metrics', '{chart_config}', 3, '2023-01-03 11:30:00', '2023-01-03 11:30:00', 0),
    (4, 'Financial Trends', 4, '...', 'Monitor financial trends', 'SELECT * FROM financial_data', '{chart_config}', 4, '2023-01-04 09:00:00', '2023-01-04 09:00:00', 0),
    (5, 'Tech Stack Usage', 5, '...', 'Optimize technology usage', 'SELECT * FROM tech_stack_data', '{chart_config}', 5, '2023-01-05 17:00:00', '2023-01-05 17:00:00', 0);

-- dataSource 表
INSERT INTO dataSource (name, dbUrl, username, PASSWORD, datasourceType, userId, createTime, updateTime, isDelete)
VALUES
    ('Sales Database', 'jdbc:mysql://localhost:3306/sales_db', 'sales_user', 'sales_password', 'MySQL', 1, '2023-01-01 13:30:00', '2023-01-01 13:30:00', 0),
    ('User Analytics', 'jdbc:mysql://localhost:3306/analytics_db', 'analytics_user', 'analytics_password', 'MySQL', 2, '2023-01-02 16:00:00', '2023-01-02 16:00:00', 0),
    ('Product Database', 'jdbc:mysql://localhost:3306/product_db', 'product_user', 'product_password', 'MySQL', 3, '2023-01-03 12:00:00', '2023-01-03 12:00:00', 0),
    ('Financial Data', 'jdbc:mysql://localhost:3306/financial_db', 'financial_user', 'financial_password', 'MySQL', 4, '2023-01-04 09:30:00', '2023-01-04 09:30:00', 0),
    ('Tech Stack Metrics', 'jdbc:mysql://localhost:3306/tech_stack_db', 'tech_stack_user', 'tech_stack_password', 'MySQL', 5, '2023-01-05 17:30:00', '2023-01-05 17:30:00', 0);

-- user 表新增记录
INSERT INTO user (username, userAccount, PASSWORD, avatar, role, createTime, updateTime, isDelete)
VALUES
    ('John Doe', 'john_doe123', 'password123', 'avatar1.jpg', 'user', '2023-01-01 12:00:00', '2023-01-01 12:00:00', 0);

-- chat 表新增记录
INSERT INTO chat (name, userId, createTime, updateTime, isDelete)
VALUES
    ('John Doe Chat', 1, '2023-01-06 10:00:00', '2023-01-06 10:00:00', 0);

-- chart 表新增记录
INSERT INTO chart (userId, description, datasourceId, chatHistory, goal, sqlText, chartText, chatId, createTime, updateTime, isDelete)
VALUES
    (1, 'Sales Overview', 1, '...', 'Increase sales by 10%', 'SELECT * FROM sales_data', '{chart_config}', 6, '2023-01-06 10:30:00', '2023-01-06 10:30:00', 0),
    (1, 'User Activity', 2, '...', 'Analyze user engagement', 'SELECT * FROM user_activity', '{chart_config}', 6, '2023-01-06 11:00:00', '2023-01-06 11:00:00', 0),
    (1, 'Product Metrics', 3, '...', 'Improve product performance', 'SELECT * FROM product_metrics', '{chart_config}', 6, '2023-01-06 11:30:00', '2023-01-06 11:30:00', 0);
