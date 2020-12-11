-- --------------------------------------------------------
-- 主机:                           192.168.161.3
-- 服务器版本:                        5.7.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 devicedb.sys_api 结构
CREATE TABLE IF NOT EXISTS `sys_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_pid` int(11) NOT NULL COMMENT '接口父ID(即接口分组)',
  `api_pids` varchar(64) NOT NULL COMMENT '当前接口的所有上级id(即所有上级分组)',
  `is_leaf` tinyint(1) NOT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `api_name` varchar(64) NOT NULL COMMENT '接口名称',
  `url` varchar(64) DEFAULT NULL COMMENT '跳转URL',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) NOT NULL COMMENT '层级，1：接口分组，2：接口',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='系统Http接口表，配合sys_role_api控制接口访问权限';

-- 正在导出表  devicedb.sys_api 的数据：~44 rows (大约)
/*!40000 ALTER TABLE `sys_api` DISABLE KEYS */;
INSERT INTO `sys_api` (`id`, `api_pid`, `api_pids`, `is_leaf`, `api_name`, `url`, `sort`, `level`, `status`) VALUES
	(1, 0, '[0]', 0, '系统数据接口', NULL, 1, 1, 0),
	(2, 1, '[0],[1]', 0, '系统管理模块', NULL, 1, 2, 0),
	(3, 2, '[0],[1],[2]', 1, '用户信息接口', '/sysuser/info', 1, 3, 0),
	(4, 2, '[0],[1],[2]', 1, '组织管理-树形数据接口', '/sysorg/tree', 2, 3, 0),
	(5, 2, '[0],[1],[2]', 1, '组织管理-新增组织接口', '/sysorg/add', 3, 3, 0),
	(6, 2, '[0],[1],[2]', 1, '组织管理-修改组织接口', '/sysorg/update', 4, 3, 0),
	(7, 2, '[0],[1],[2]', 1, '组织管理-删除组织接口', '/sysorg/delete', 5, 3, 0),
	(8, 2, '[0],[1],[2]', 1, '菜单树形数据加载接口', '/sysmenu/tree', 6, 3, 0),
	(9, 2, '[0],[1],[2]', 1, '菜单管理-新增菜单项接口', '/sysmenu/add', 7, 3, 0),
	(10, 2, '[0],[1],[2]', 1, '菜单管理-修改菜单项接口', '/sysmenu/update', 8, 3, 0),
	(11, 2, '[0],[1],[2]', 1, '菜单管理-删除菜单项接口', '/sysmenu/delete', 9, 3, 0),
	(12, 2, '[0],[1],[2]', 1, '查询某角色已具备菜单权限接口', '/sysmenu/checkedtree', 10, 3, 0),
	(13, 2, '[0],[1],[2]', 1, '保存某角色分配勾选的菜单权限', '/sysmenu/savekeys', 11, 3, 0),
	(14, 2, '[0],[1],[2]', 1, '接口分类树形结构数据加载', '/sysapi/tree', 12, 3, 0),
	(15, 2, '[0],[1],[2]', 1, '接口管理-新增接口', '/sysapi/add', 13, 3, 0),
	(16, 2, '[0],[1],[2]', 1, '接口管理-更新接口数据', '/sysapi/update', 14, 3, 0),
	(17, 2, '[0],[1],[2]', 1, '接口管理-删除接口', '/sysapi/delete', 15, 3, 0),
	(18, 2, '[0],[1],[2]', 1, '查询某角色已具备的接口访问权限', '/sysapi/checkedtree', 16, 3, 0),
	(19, 2, '[0],[1],[2]', 1, '保存某角色勾选分配的接口访问权限', '/sysapi/savekeys', 17, 3, 0),
	(20, 2, '[0],[1],[2]', 1, '角色管理-列表查询', '/sysrole/query', 18, 3, 0),
	(21, 2, '[0],[1],[2]', 1, '角色管理-新增角色', '/sysrole/add', 19, 3, 0),
	(22, 2, '[0],[1],[2]', 1, '角色管理-更新角色数据', '/sysrole/update', 20, 3, 0),
	(23, 2, '[0],[1],[2]', 1, '角色管理-删除角色', '/sysrole/delete', 21, 3, 0),
	(24, 2, '[0],[1],[2]', 1, '查询某用户具备的角色id列表', '/sysrole/checkedroles', 22, 3, 0),
	(25, 2, '[0],[1],[2]', 1, '保存为某用户分配的角色', '/sysrole/savekeys', 23, 3, 0),
	(26, 2, '[0],[1],[2]', 1, '用户管理-用户列表查询', '/sysuser/query', 24, 3, 0),
	(27, 2, '[0],[1],[2]', 1, '用户管理-新增用户', '/sysuser/add', 25, 3, 0),
	(28, 2, '[0],[1],[2]', 1, '用户管理-修改用户信息', '/sysuser/update', 26, 3, 0),
	(29, 2, '[0],[1],[2]', 1, '用户管理-删除用户', '/sysuser/delete', 27, 3, 0),
	(30, 2, '[0],[1],[2]', 1, '为用户重置密码', '/sysuser/pwd/reset', 28, 3, 0),
	(31, 2, '[0],[1],[2]', 1, '判断用户是否使用默认密码', '/sysuser/pwd/isdefault', 29, 3, 0),
	(32, 2, '[0],[1],[2]', 1, '修改用户密码', '/sysuser/pwd/change', 30, 3, 0),
	(33, 2, '[0],[1],[2]', 1, '菜单栏数据接口(根据登录用户)', '/sysmenu/tree/user', 6, 3, 0),
	(34, 2, '[0],[1],[2]', 1, '获取系统全局配置参数', '/sysconfig/all', 31, 3, 0),
	(35, 2, '[0],[1],[2]', 1, '条件查询全局配置参数接口', '/sysconfig/query', 32, 3, 0),
	(36, 2, '[0],[1],[2]', 1, '新增配置参数接口', '/sysconfig/add', 33, 3, 0),
	(37, 2, '[0],[1],[2]', 1, '修改配置参数接口', '/sysconfig/update', 34, 3, 0),
	(38, 2, '[0],[1],[2]', 1, '删除配置参数接口', '/sysconfig/delete', 35, 3, 0),
	(39, 2, '[0],[1],[2]', 1, '配置参数从数据库刷新到内存', '/sysconfig/refresh', 36, 3, 0),
	(40, 2, '[0],[1],[2]', 1, '数据字典数据加载接口', '/sysdict/all', 37, 3, 0),
	(41, 2, '[0],[1],[2]', 1, '数据字典条件查询接口', '/sysdict/query', 38, 3, 0),
	(42, 2, '[0],[1],[2]', 1, '数据字典数据新增接口', '/sysdict/add', 39, 3, 0),
	(43, 2, '[0],[1],[2]', 1, '数据字典数据修改接口', '/sysdict/update', 40, 3, 0),
	(44, 2, '[0],[1],[2]', 1, '数据字典数据删除接口', '/sysdict/delete', 41, 3, 0);
/*!40000 ALTER TABLE `sys_api` ENABLE KEYS */;


-- 导出  表 devicedb.sys_config 结构
CREATE TABLE IF NOT EXISTS `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(32) NOT NULL COMMENT '参数名称(中文)',
  `param_key` varchar(64) NOT NULL COMMENT '参数编码唯一标识(英文及数字)',
  `param_value` varchar(64) NOT NULL COMMENT '参数值',
  `param_desc` varchar(64) DEFAULT NULL COMMENT '参数描述备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统全局配置参数';

-- 正在导出表  devicedb.sys_config 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` (`id`, `param_name`, `param_key`, `param_value`, `param_desc`, `create_time`) VALUES
	(1, '用户初始密码', 'user.init.password', 'abcd1234', '系统新增用户初始化密码（登录后会提示用户自行修改）', '2020-02-29 13:26:58');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;


-- 导出  表 devicedb.sys_dict 结构
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) NOT NULL COMMENT '分组名称',
  `group_code` varchar(64) NOT NULL COMMENT '分组编码',
  `item_name` varchar(16) NOT NULL COMMENT '字典项名称',
  `item_value` varchar(16) NOT NULL COMMENT '字典项Value',
  `item_desc` varchar(64) DEFAULT NULL COMMENT '字典项描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '字典项创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- 正在导出表  devicedb.sys_dict 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` (`id`, `group_name`, `group_code`, `item_name`, `item_value`, `item_desc`, `create_time`) VALUES
	(1, '是否禁用', 'common.status', '未禁用', 'false', '通用数据记录的禁用状态', '2020-02-29 17:00:16'),
	(2, '是否禁用', 'common.status', '已禁用', 'true', '通用数据记录的禁用状态', '2020-02-29 17:00:26'),
	(3, '用户状态', 'sysuser.enabled', '已激活', 'true', '用户状态', '2020-02-29 18:42:08'),
	(4, '用户状态', 'sysuser.enabled', '已禁用', 'false', '用户状态', '2020-02-29 23:23:35');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;


-- 导出  表 devicedb.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_pid` int(11) NOT NULL COMMENT '父菜单ID',
  `menu_pids` varchar(64) NOT NULL COMMENT '当前菜单所有父菜单',
  `is_leaf` tinyint(1) NOT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `menu_name` varchar(16) NOT NULL COMMENT '菜单名称',
  `url` varchar(64) DEFAULT NULL COMMENT '跳转URL',
  `icon` varchar(45) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) NOT NULL COMMENT '菜单层级',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- 正在导出表  devicedb.sys_menu 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `menu_pid`, `menu_pids`, `is_leaf`, `menu_name`, `url`, `icon`, `sort`, `level`, `status`) VALUES
	(1, 0, '[0]', 0, '系统根目录', '/', '', 1, 1, 0),
	(2, 1, '[0],[1]', 0, '系统管理', '/system', 'el-icon-fa fa-cogs', 1, 2, 0),
	(3, 2, '[0],[1],[2]', 1, '用户管理', '/home/sysuser', 'el-icon-fa fa-user', 1, 3, 0),
	(4, 2, '[0],[1],[2]', 1, '角色管理', '/home/sysrole', 'el-icon-fa fa-users', 2, 3, 0),
	(5, 2, '[0],[1],[2]', 1, '组织管理', '/home/sysorg', 'el-icon-fa fa-sitemap', 3, 3, 0),
	(6, 2, '[0],[1],[2]', 1, '菜单管理', '/home/sysmenu', 'el-icon-fa fa-list-ul', 4, 3, 0),
	(7, 2, '[0],[1],[2]', 1, '接口管理', '/home/sysapi', 'el-icon-fa fa-plug', 5, 3, 1),
	(10, 1, '[0],[1]', 0, '测试用菜单', '/order', 'el-icon-eleme', 2, 2, 0),
	(11, 10, '[0],[1],[10]', 1, '子菜单(首页)', '/home/firstpage', 'el-icon-lock', 1, 3, 0),
	(12, 2, '[0],[1],[2]', 1, '参数配置', '/home/sysconfig', 'el-icon-fa fa-cog', 6, 3, 0),
	(13, 2, '[0],[1],[2]', 1, '数据字典', '/home/sysdict', 'el-icon-fa fa-list-ol', 7, 3, 0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 devicedb.sys_org 结构
CREATE TABLE IF NOT EXISTS `sys_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_pid` int(11) NOT NULL COMMENT '上级组织编码',
  `org_pids` varchar(64) NOT NULL COMMENT '所有的父节点id',
  `is_leaf` tinyint(1) NOT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `org_name` varchar(32) NOT NULL COMMENT '组织名',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `phone` varchar(13) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮件',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) NOT NULL COMMENT '组织层级',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统组织结构表';

-- 正在导出表  devicedb.sys_org 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `sys_org` DISABLE KEYS */;
INSERT INTO `sys_org` (`id`, `org_pid`, `org_pids`, `is_leaf`, `org_name`, `address`, `phone`, `email`, `sort`, `level`, `status`) VALUES
	(1, 0, '[0]', 0, 'DongTech', NULL, NULL, NULL, 1, 1, 0),
	(2, 1, '[0],[1]', 0, '北京分公司', '北京海淀区中关村', '13218765432', 'beijing21212@email.com', 1, 2, 0),
	(3, 2, '[0],[1],[2]', 1, '研发部', '北京海淀区中关村', '13218765422', 'hanxi@email.com', 1, 3, 0),
	(4, 2, '[0],[1],[2]', 1, '测试部', NULL, '', 'cesh@gmail.com', 2, 3, 0),
	(5, 1, '[0],[1]', 0, '上海分公司', '上海黄浦区', '13753287651', 'shanghaixx@email.com', 2, 2, 0),
	(6, 5, '[0],[1],[5]', 1, '运维部', '上海黄浦区', '13753287656', NULL, 1, 3, 0),
	(7, 5, '[0],[1],[5]', 1, '运维部二', '上海黄浦区', '13753287656', NULL, 2, 3, 0),
	(8, 5, '[0],[1],[5]', 1, '运维部三', '上海黄浦区', '13753287656', NULL, 3, 3, 0),
	(9, 5, '[0],[1],[5]', 1, '运维部四', '上海黄浦区', '13753287656', NULL, 4, 3, 0);
/*!40000 ALTER TABLE `sys_org` ENABLE KEYS */;


-- 导出  表 devicedb.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL DEFAULT '0' COMMENT '角色名称(汉字)',
  `role_desc` varchar(128) NOT NULL DEFAULT '0' COMMENT '角色描述',
  `role_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '角色的英文code.如：ADMIN',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '角色顺序',
  `status` tinyint(1) DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- 正在导出表  devicedb.sys_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `role_name`, `role_desc`, `role_code`, `sort`, `status`) VALUES
	(1, '管理员', '系统管理员', 'admin', 1, 0),
	(2, '普通用户', '普通用户', 'common', 2, 0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 devicedb.sys_role_api 结构
CREATE TABLE IF NOT EXISTS `sys_role_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `api_id` int(11) NOT NULL COMMENT '接口id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=359 DEFAULT CHARSET=utf8 COMMENT='角色接口权限关系表';

-- 正在导出表  devicedb.sys_role_api 的数据：~46 rows (大约)
/*!40000 ALTER TABLE `sys_role_api` DISABLE KEYS */;
INSERT INTO `sys_role_api` (`id`, `role_id`, `api_id`) VALUES
	(6, 2, 4),
	(7, 2, 5),
	(315, 1, 1),
	(316, 1, 2),
	(317, 1, 3),
	(318, 1, 4),
	(319, 1, 5),
	(320, 1, 6),
	(321, 1, 7),
	(322, 1, 33),
	(323, 1, 8),
	(324, 1, 9),
	(325, 1, 10),
	(326, 1, 11),
	(327, 1, 12),
	(328, 1, 13),
	(329, 1, 14),
	(330, 1, 15),
	(331, 1, 16),
	(332, 1, 17),
	(333, 1, 18),
	(334, 1, 19),
	(335, 1, 20),
	(336, 1, 21),
	(337, 1, 22),
	(338, 1, 23),
	(339, 1, 24),
	(340, 1, 25),
	(341, 1, 26),
	(342, 1, 27),
	(343, 1, 28),
	(344, 1, 29),
	(345, 1, 30),
	(346, 1, 31),
	(347, 1, 32),
	(348, 1, 34),
	(349, 1, 35),
	(350, 1, 36),
	(351, 1, 37),
	(352, 1, 38),
	(353, 1, 39),
	(354, 1, 40),
	(355, 1, 41),
	(356, 1, 42),
	(357, 1, 43),
	(358, 1, 44);
/*!40000 ALTER TABLE `sys_role_api` ENABLE KEYS */;


-- 导出  表 devicedb.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 COMMENT='角色菜单权限关系表';

-- 正在导出表  devicedb.sys_role_menu 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES
	(11, 2, 3),
	(12, 2, 4),
	(13, 2, 5),
	(163, 1, 1),
	(164, 1, 2),
	(165, 1, 3),
	(166, 1, 4),
	(167, 1, 5),
	(168, 1, 6),
	(169, 1, 7),
	(170, 1, 12),
	(171, 1, 13),
	(172, 1, 10),
	(173, 1, 11);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;


-- 导出  表 devicedb.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '0' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '0' COMMENT '密码',
  `org_id` int(11) NOT NULL COMMENT '组织id',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0无效用户，1是有效用户',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT 'email',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- 正在导出表  devicedb.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `username`, `password`, `org_id`, `enabled`, `phone`, `email`, `create_time`) VALUES
	(1, 'yanfa1', '$2a$10$dpdjlMhNK70nHcuF3SHf2elXTPTY1CJFgnVvrVc6gfeqsce.PAARK', 5, 1, NULL, '111@qq.com', '2020-02-28 09:16:30'),
	(2, 'admin', '$2a$10$eWxhrcPVnMMrnyrxWa./1.d19QuBMS33amEaVVjIETsxk4kes2HKG', 1, 1, '13756823456', 'Xx@163.com', '2020-02-27 18:37:47');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 devicedb.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色自增id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户自增id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- 正在导出表  devicedb.sys_user_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `role_id`, `user_id`) VALUES
	(2, 1, 2),
	(6, 2, 1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
