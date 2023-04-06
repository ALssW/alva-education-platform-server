create database `alva_edu`;

# ------- public columns
# create_time datetime not null comment '创建时间',
#	update_time datetime not null comment '修改时间',
#	status tinyint not null comment '状态',
#	del_flag tinyint not null comment '删除'

# 系统用户表
drop table if exists `sys_user`;
create table `sys_user`
(
    id          bigint primary key auto_increment comment '主键',
    account     varchar(20) unique not null comment '用户名',
    password    char(32)           not null comment '密码',
    name        varchar(10)        not null comment '姓名',
    create_time datetime           not null comment '创建时间',
    update_time datetime           not null comment '修改时间',
    status      tinyint            not null comment '状态',
    del_flag    tinyint            not null comment '删除'
) comment '系统用户表';

# 创建默认超级用户
insert into `sys_user`
values (1001, 'admin', 'admin', 'admin', now(), now(), 0, 0);

# 课程分类表
drop table if exists `course_type`;
create table course_type
(
    id          bigint primary key auto_increment comment '主键',
    pid         bigint comment '上级分类',
    name        varchar(20) not null unique comment '分类名称',
    num         int default 0 comment '分类数',
    type        int         not null comment '分类等级 1, 2, 3',
    chains      varchar(50) comment '当前分类与上级分类ID',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间',
    status      tinyint     not null comment '状态',
    del_flag    tinyint     not null comment '删除'
) comment '课程分类';

# 课程分类测试数据
insert into course_type
values (1, null, 'IT·互联网', 0, 1, null, now(), now(), 0, 0),
       (2, 1, '前沿技术', 0, 2, 1, now(), now(), 0, 0),
       (3, 1, 'Java开发', 0, 2, 1, now(), now(), 0, 0),
       (4, 3, 'Java零基础', 0, 3, '[1,3]', now(), now(), 0, 0),
       (5, null, '考试·考证', 0, 1, null, now(), now(), 0, 0),
       (6, 5, '考验', 0, 2, 5, now(), now(), 0, 0),
       (7, 5, '考公', 0, 2, 5, now(), now(), 0, 0);


# 教师表
drop table if exists `teacher`;
create table teacher
(
    id          bigint primary key auto_increment comment '主键',
    email       varchar(30) unique not null comment '邮箱',
    name        varchar(20) comment '姓名',
    age         int comment '年龄',
    info        text comment '简介',
    create_time datetime           not null comment '创建时间',
    update_time datetime           not null comment '修改时间',
    status      tinyint            not null comment '状态',
    del_flag    tinyint            not null comment '删除'
) comment '教师';

# 课程表
drop table if exists `course`;
create table `course`
(
    id          bigint primary key auto_increment comment '主键',
    type_id     bigint         not null comment '课程分类ID',
    teacher_id  bigint         not null comment '教师ID',
    name        varchar(100) comment '课程名称',
    image       varchar(100) comment '课程封面',
    info        text comment '课程介绍',
    price       decimal(10, 2) not null comment '课程价格',
    begin_time  datetime       not null comment '课程开始时间',
    end_time    datetime       not null comment '课程结束时间',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '修改时间',
    status      tinyint        not null comment '状态',
    del_flag    tinyint        not null comment '删除'
) comment '课程';

# 普通用户
drop table if exists `user`;
create table `user`
(
    id           bigint primary key auto_increment comment '主键',
    openid       varchar(100) unique not null comment '微信端openid',
    session_key varchar(100)        not null comment '微信端session key',
    avatar       text comment '头像',
    nickname     varchar(20) comment '用户名称',
    create_time  datetime            not null comment '创建时间',
    update_time  datetime            not null comment '修改时间',
    status       tinyint             not null comment '状态',
    del_flag     tinyint             not null comment '删除'
) comment '普通用户';