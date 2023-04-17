create database `alva_edu_activity`;

# ------- public columns
#   id bigint primary key auto_increment comment '主键',
#   create_time datetime not null comment '创建时间',
#   update_time datetime not null comment '修改时间',
#   status tinyint not null comment '状态',
#   del_flag tinyint not null comment '删除'

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
    begin_time  datetime           not null comment '活动开始时间',
    end_time    datetime           not null comment '活动结束时间',
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