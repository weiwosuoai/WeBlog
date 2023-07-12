-- 管理员
INSERT INTO `t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (1, 'admin', 'ROLE_ADMIN', '2023-07-07 01:21:15');
-- 游客
INSERT INTO `t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (2, 'test', 'ROLE_VISITOR', '2023-07-07 01:23:33');
-- 博客基础设置信息（部署完成后，可自行登录管理后台更改）
INSERT INTO `t_blog_setting` (`blog_name`, `author`, `introduction`, `avatar`, `github_home`, `csdn_home`, `gitee_home`, `zhihu_home`) VALUES ('Weblog', '犬小哈', '平安喜乐', 'https://img.quanxiaoha.com/quanxiaoha/2a5509abf8fe42118c17d6d18fb36a13.jpg', 'https://www.quanxiaoha.com', 'https://www.quanxiaoha.com', 'https://www.quanxiaoha.com', 'https://www.quanxiaoha.com');

-- 管理员角色
INSERT INTO `t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (1, 'admin', 'ROLE_ADMIN', '2023-07-07 01:21:15');
-- 游客角色
INSERT INTO `t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (2, 'test', 'ROLE_VISITOR', '2023-07-07 01:23:33');

