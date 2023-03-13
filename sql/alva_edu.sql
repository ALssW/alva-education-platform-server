create database alva_edu;

use alva_edu;

drop table if exists `course`;
create table `course`
(
    id         int PRIMARY KEY auto_increment comment '课程ID',
    name       varchar(20) not null comment '课程名称',
    start_time datetime    not null comment '开课时间'
);

ALTER TABLE `alva_edu`.`course`
    MODIFY COLUMN `name` varchar(100);

