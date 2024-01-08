-- 生成5个用户
INSERT INTO user (username, userAccount, password, avatar, role, createTime, updateTime, isDelete)
VALUES ('User1', 'user1_account', 'password1', 'avatar1.jpg', 'user', NOW(), NOW(), 0),
       ('User2', 'user2_account', 'password2', 'avatar2.jpg', 'user', NOW(), NOW(), 0),
       ('User3', 'user3_account', 'password3', 'avatar3.jpg', 'user', NOW(), NOW(), 0),
       ('Admin1', 'admin1_account', 'admin_password1', 'admin_avatar1.jpg', 'admin', NOW(), NOW(), 0),
       ('Admin2', 'admin2_account', 'admin_password2', 'admin_avatar2.jpg', 'admin', NOW(), NOW(), 0);

-- 生成至少30条图表数据

INSERT INTO chart (userId, goal, chartOption, data, status, summary, createTime, updateTime, isDelete)
VALUES
-- User1的图表
(1, 'Goal 1', '{"type": "bar", "color": "blue"}', 'data1', 'succeeded', 'Summary for Goal 1', NOW(), NOW(), 0),
(1, 'Goal 2', '{"type": "line", "color": "green"}', 'data2', 'running', 'Summary for Goal 2', NOW(), NOW(), 0),
(1, 'Goal 3', '{"type": "pie", "color": "red"}', 'data3', 'waiting', 'Summary for Goal 3', NOW(), NOW(), 0),
(1, 'Goal 4', '{"type": "area", "color": "orange"}', 'data4', 'failed', 'Summary for Goal 4', NOW(), NOW(), 0),
-- User2的图表
(2, 'Goal 5', '{"type": "bar", "color": "purple"}', 'data5', 'succeeded', 'Summary for Goal 5', NOW(), NOW(), 0),
(2, 'Goal 6', '{"type": "line", "color": "pink"}', 'data6', 'waiting', 'Summary for Goal 6', NOW(), NOW(), 0),
(2, 'Goal 7', '{"type": "pie", "color": "yellow"}', 'data7', 'running', 'Summary for Goal 7', NOW(), NOW(), 0),
(2, 'Goal 8', '{"type": "area", "color": "brown"}', 'data8', 'failed', 'Summary for Goal 8', NOW(), NOW(), 0),
-- User3的图表
(3, 'Goal 9', '{"type": "bar", "color": "cyan"}', 'data9', 'succeeded', 'Summary for Goal 9', NOW(), NOW(), 0),
(3, 'Goal 10', '{"type": "line", "color": "gray"}', 'data10', 'waiting', 'Summary for Goal 10', NOW(), NOW(), 0),
(3, 'Goal 11', '{"type": "pie", "color": "violet"}', 'data11', 'running', 'Summary for Goal 11', NOW(), NOW(), 0),
(3, 'Goal 12', '{"type": "area", "color": "indigo"}', 'data12', 'failed', 'Summary for Goal 12', NOW(), NOW(), 0),
-- Admin1的图表
(4, 'Goal 13', '{"type": "bar", "color": "pink"}', 'data13', 'succeeded', 'Summary for Goal 13', NOW(), NOW(), 0),
(4, 'Goal 14', '{"type": "line", "color": "brown"}', 'data14', 'waiting', 'Summary for Goal 14', NOW(), NOW(), 0),
(4, 'Goal 15', '{"type": "pie", "color": "cyan"}', 'data15', 'running', 'Summary for Goal 15', NOW(), NOW(), 0),
(4, 'Goal 16', '{"type": "area", "color": "purple"}', 'data16', 'failed', 'Summary for Goal 16', NOW(), NOW(), 0),
-- Admin2的图表
(5, 'Goal 17', '{"type": "bar", "color": "violet"}', 'data17', 'succeeded', 'Summary for Goal 17', NOW(), NOW(), 0),
(5, 'Goal 18', '{"type": "line", "color": "indigo"}', 'data18', 'waiting', 'Summary for Goal 18', NOW(), NOW(), 0),
(5, 'Goal 19', '{"type": "pie", "color": "pink"}', 'data19', 'running', 'Summary for Goal 19', NOW(), NOW(), 0),
(5, 'Goal 20', '{"type": "area", "color": "brown"}', 'data20', 'failed', 'Summary for Goal 20', NOW(), NOW(), 0),
-- 继续生成图表数据，直至至少30条为止
(1, 'Goal 21', '{"type": "bar", "color": "blue"}', 'data21', 'succeeded', 'Summary for Goal 21', NOW(), NOW(), 0),
(2, 'Goal 22', '{"type": "line", "color": "green"}', 'data22', 'waiting', 'Summary for Goal 22', NOW(), NOW(), 0),
(3, 'Goal 23', '{"type": "pie", "color": "red"}', 'data23', 'running', 'Summary for Goal 23', NOW(), NOW(), 0),
(4, 'Goal 24', '{"type": "area", "color": "orange"}', 'data24', 'failed', 'Summary for Goal 24', NOW(), NOW(), 0),
(5, 'Goal 25', '{"type": "bar", "color": "purple"}', 'data25', 'succeeded', 'Summary for Goal 25', NOW(), NOW(), 0),
(1, 'Goal 26', '{"type": "line", "color": "pink"}', 'data26', 'waiting', 'Summary for Goal 26', NOW(), NOW(), 0),
(2, 'Goal 27', '{"type": "pie", "color": "yellow"}', 'data27', 'running', 'Summary for Goal 27', NOW(), NOW(), 0),
(3, 'Goal 28', '{"type": "area", "color": "brown"}', 'data28', 'failed', 'Summary for Goal 28', NOW(), NOW(), 0),
(4, 'Goal 29', '{"type": "bar", "color": "cyan"}', 'data29', 'succeeded', 'Summary for Goal 29', NOW(), NOW(), 0),
(5, '分析中国历年增长', null, 'testData.txt', 'waiting', null, NOW(), NOW(), 0);
