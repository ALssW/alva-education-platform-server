create database `alva_edu`;

# ------- public columns
#   create_time datetime not null comment '创建时间',
#	update_time datetime not null comment '修改时间'，
#	status tinyint not null comment '状态',
#	del_flag tinyint not null comment '删除'


drop table if exists `sys_user`;

create table `sys_user`
(
    id          bigint primary key auto_increment comment '主键',
    username    varchar(20) unique not null comment '用户名',
    password    char(32)           not null comment '密码',
    name        varchar(10)        not null comment '姓名',
    create_time datetime           not null comment '创建时间',
    update_time datetime           not null comment '修改时间',
    status      tinyint            not null comment '状态',
    del_flag    tinyint            not null comment '删除'
) comment '系统用户表'