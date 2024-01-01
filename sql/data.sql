-- 添加用户记录
INSERT INTO user (username, userAccount, password, avatar, role, createTime, updateTime, isDelete)
VALUES ('John Doe', 'john_doe', 'password123', 'avatar1.jpg', 'user', NOW(), NOW(), 0),
       ('Admin User', 'admin_user', 'adminPass', 'admin_avatar.jpg', 'admin', NOW(), NOW(), 0),
       ('Alice Smith', 'alice_smith', 'alicePass', 'avatar2.jpg', 'user', NOW(), NOW(), 0),
       ('Bob Johnson', 'bob_johnson', 'bobPass', 'avatar3.jpg', 'user', NOW(), NOW(), 0),
       ('Eva Williams', 'eva_williams', 'evaPass', 'avatar4.jpg', 'user', NOW(), NOW(), 0);

-- 添加聊天记录
INSERT INTO chat (name, userId, createTime, updateTime, isDelete)
VALUES ('Team Chat', 1, NOW(), NOW(), 0),
       ('Personal Chat', 1, NOW(), NOW(), 0),
       ('Team Collaboration', 1, NOW(), NOW(), 0),
       ('Project Discussion', 2, NOW(), NOW(), 0),
       ('General Chat', 3, NOW(), NOW(), 0),
       ('Support Chat', 4, NOW(), NOW(), 0),
       ('Tech Talk', 5, NOW(), NOW(), 0);


-- 添加图表记录
INSERT INTO chart (userId, description, datasourceId, chatHistory, goal, sqlText, chartText, chatId, status, createTime,
                   updateTime, isDelete)
VALUES
    -- Chart记录属于用户John Doe的第一个Chat
    (1, 'Sales Analysis', 1, 'Sales chat history...', 'Analyze monthly sales data', 'SELECT * FROM sales',
     'Chart configuration...', 1, 'succeeded', NOW(), NOW(), 0),
    (1, 'User Engagement', 2, 'Engagement chat history...', 'Analyze user engagement', 'SELECT * FROM user_engagement',
     'Chart configuration...', 1, 'failed', NOW(), NOW(), 0),
    (1, 'Project Progress', 3, 'Progress chat history...', 'Monitor project progress', 'SELECT * FROM project_progress',
     'Chart configuration...', 1, 'running', NOW(), NOW(), 0),
    (1, 'Support Tickets', 4, 'Tickets chat history...', 'Track support tickets', 'SELECT * FROM support_tickets',
     'Chart configuration...', 1, 'waiting', NOW(), NOW(), 0),
    (1, 'Tech Trends', 5, 'Trends chat history...', 'Explore tech trends', 'SELECT * FROM tech_trends',
     'Chart configuration...', 1, 'waiting', NOW(), NOW(), 0),

    -- Chart记录属于用户John Doe的第二个Chat
    (1, 'Personal Interests', 2, 'Personal chat history...', 'Explore personal interests',
     'SELECT * FROM personal_interests', 'Chart configuration...', 2, 'succeeded', NOW(), NOW(), 0),
    (1, 'Team Projects', 3, 'Team chat history...', 'Collaborate on team projects', 'SELECT * FROM team_projects',
     'Chart configuration...', 2, 'running', NOW(), NOW(), 0),
    (1, 'Feedback Analysis', 4, 'Feedback chat history...', 'Analyze user feedback', 'SELECT * FROM feedback_analysis',
     'Chart configuration...', 2, 'waiting', NOW(), NOW(), 0);

-- 添加数据源记录
INSERT INTO dataSource (name, dbUrl, username, password, datasourceType, userId, createTime, updateTime, isDelete)
VALUES ('Sales Database', 'jdbc:mysql://localhost:3306/sales_db', 'sales_user', 'salesPass', 'MySQL', 1, NOW(), NOW(),
        0),
       ('Engagement DB', 'jdbc:mysql://localhost:3306/engagement_db', 'engagement_user', 'engagementPass', 'MySQL', 1,
        NOW(), NOW(), 0),
       ('Project DB', 'jdbc:mysql://localhost:3306/project_db', 'project_user', 'projectPass', 'MySQL', 1, NOW(), NOW(),
        0),
       ('Support DB', 'jdbc:mysql://localhost:3306/support_db', 'support_user', 'supportPass', 'MySQL', 1, NOW(), NOW(),
        0),
       ('Tech Trends DB', 'jdbc:mysql://localhost:3306/tech_trends_db', 'tech_user', 'techPass', 'MySQL', 1, NOW(),
        NOW(), 0);
