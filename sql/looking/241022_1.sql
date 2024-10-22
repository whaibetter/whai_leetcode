-- stu，列 name, score, course，查询每一科分数大于 60 的学生姓名


-- 创建表
create table stu(
    id int primary key auto_increment,
    name varchar(20),
    score int,
    course varchar(20)
);
-- 生成一些数据
insert into stu(name, score, course)
values ('张三', 70, '语文'),
       ('李四', 80, '语文'),
       ('王五', 60, '语文'),
       ('赵六', 90, '语文');

SELECT DISTINCT stu.name FROM stu where stu.name
    not in( SELECT stu.name FROM stu WHERE stu.score<60);

SELECT name
FROM stu
GROUP BY name
HAVING MIN(score) > 60;
