create database `alva_edu`;

# ------- public columns
#   id bigint primary key auto_increment comment '主键',
#   create_time datetime not null comment '创建时间',
#   update_time datetime not null comment '修改时间',
#   status tinyint not null comment '状态',
#   del_flag tinyint not null comment '删除'

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
) comment '系统用户';

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

# 普通用户表
drop table if exists `user`;
create table `user`
(
    id          bigint primary key auto_increment comment '主键',
    openid      varchar(100) unique not null comment '微信端openid',
    session_key varchar(100)        not null comment '微信端session key',
    avatar      text comment '头像',
    nickname    varchar(20) comment '用户名称',
    create_time datetime            not null comment '创建时间',
    update_time datetime            not null comment '修改时间',
    status      tinyint             not null comment '状态',
    del_flag    tinyint             not null comment '删除'
) comment '普通用户';

# 活动表
drop table if exists `activity`;
create table `activity`
(
    id          bigint primary key auto_increment comment '主键',
    title       varchar(20) unique not null comment '活动标题',
    cover       varchar(200)       not null comment '活动封面',
    url         varchar(100)       not null comment '活动页面',
    info        text comment '活动介绍',
    type        tinyint            not null comment '活动类型(0-红包雨 1-...)',
    create_time datetime           not null comment '创建时间',
    update_time datetime           not null comment '修改时间',
    status      tinyint            not null comment '状态',
    del_flag    tinyint            not null comment '删除'
) comment '活动';

# 活动红包表
drop table if exists `activity_red_packet`;
create table `activity_red_packet`
(
    id          bigint primary key auto_increment comment '主键',
    activity_id bigint   not null comment '活动ID',
    type        tinyint  not null comment '红包类型(0-现金红包 1-优惠价 2-)',
    probability tinyint  not null comment '概率',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    status      tinyint  not null comment '状态',
    del_flag    tinyint  not null comment '删除'
) comment '活动红包';

# 活动现金红包表
drop table if exists `activity_red_packet_cash`;
create table `activity_red_packet_cash`
(
    id            bigint primary key auto_increment comment '主键',
    red_packet_id bigint         not null comment '红包ID',
    activity_id   bigint         not null comment '活动ID',
    cash          decimal(10, 2) not null comment '金额',
    create_time   datetime       not null comment '创建时间',
    update_time   datetime       not null comment '修改时间',
    status        tinyint        not null comment '状态',
    del_flag      tinyint        not null comment '删除'
) comment '活动现金红包';

# 活动优惠券红包表
drop table if exists `activity_red_packet_coupon`;
create table `activity_red_packet_coupon`
(
    id            bigint primary key auto_increment comment '主键',
    red_packet_id bigint   not null comment '红包ID',
    activity_id   bigint   not null comment '活动ID',
    coupon_id     bigint   not null comment '优惠券ID',
    nums          int      not null comment '优惠券数量(-1-无限制)',
    create_time   datetime not null comment '创建时间',
    update_time   datetime not null comment '修改时间',
    status        tinyint  not null comment '状态',
    del_flag      tinyint  not null comment '删除'
) comment '活动优惠券红包';

# 优惠券表
drop table if exists `coupon`;
create table `coupon`
(
    id          bigint primary key auto_increment comment '主键',
    subject     varchar(100) not null comment '标题',
    nums        int          not null comment '数量(-1-无限制)',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '修改时间',
    status      tinyint      not null comment '状态',
    del_flag    tinyint      not null comment '删除'
) comment '优惠券';

# 活动红包领取记录表
drop table if exists `activity_red_packet_record`;
create table `activity_red_packet_acquire`
(
    id          bigint primary key auto_increment comment '主键',
    activity_id bigint       not null comment '活动ID',
    user_id     bigint       not null comment '用户ID',
    type        tinyint      not null comment '红包类型(0-现金 1-优惠券)',
    info        varchar(100) not null comment '记录信息(现金-金额，优惠券-优惠券ID)',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '修改时间',
    status      tinyint      not null comment '状态',
    del_flag    tinyint      not null comment '删除'
) comment '活动红包领取记';

# 优惠券用户关系表
drop table if exists `coupon_user_relation`;
create table `coupon_user_relation`
(
    id          bigint primary key auto_increment comment '主键',
    user_id     bigint   not null comment '用户ID',
    coupon_id   bigint   not null comment '优惠券ID',
    nums        int      not null comment '优惠券数量',
    source      tinyint  not null comment '优惠券来源(0-活动 1-系统 2-兑换)',
    business_id bigint comment '业务ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    status      tinyint  not null comment '状态',
    del_flag    tinyint  not null comment '删除'
) comment '优惠券用户关系';

# 用户流水表
drop table if exists `user_money_detail`;
create table `user_money_detail`
(
    id          bigint primary key auto_increment comment '主键',
    user_id     bigint         not null comment '用户ID',
    action      tinyint        not null comment '操作类型(0-+ 1--)',
    detail      decimal(10, 2) not null comment '金额',
    source      tinyint        not null comment '来源(0-活动红包)',
    business_id bigint comment '业务ID',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '修改时间',
    status      tinyint        not null comment '状态',
    del_flag    tinyint        not null comment '删除'
) comment '用户流水';