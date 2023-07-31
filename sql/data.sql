-- 管理员 (登录账号/密码 : admin/admin)
INSERT INTO `weblog`.`t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 'admin', '$2a$10$aGGgbab8HdmfxixuTwIYnOZxPH9hzrQuq1oRfLa91mVzSaRfgmtcu', '2023-07-03 11:57:18', '2023-07-08 08:24:36', 0);
-- 游客 (登录账号/密码 : test/test)
INSERT INTO `weblog`.`t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (2, 'test', '$2a$10$L6ce4rQsyJ1k7ZCOfN6X4e5dHLyvg2X0t9JFEZBezDq0lds79Pxja', '2023-07-07 01:22:05', '2023-07-07 01:22:05', 0);

-- 博客基础设置信息（部署完成后，可自行登录管理后台更改）
INSERT INTO `weblog`.`t_blog_setting` (`blog_name`, `author`, `introduction`, `avatar`, `github_home`, `csdn_home`, `gitee_home`, `zhihu_home`) VALUES ('Weblog', '犬小哈', '平安喜乐', 'https://img.quanxiaoha.com/quanxiaoha/2a5509abf8fe42118c17d6d18fb36a13.jpg', 'https://www.quanxiaoha.com', 'https://www.quanxiaoha.com', 'https://www.quanxiaoha.com', 'https://www.quanxiaoha.com');

-- 管理员角色
INSERT INTO `weblog`.`t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (1, 'admin', 'ROLE_ADMIN', '2023-07-07 01:21:15');
-- 游客角色
INSERT INTO `weblog`.`t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (2, 'test', 'ROLE_VISITOR', '2023-07-07 01:23:33');

