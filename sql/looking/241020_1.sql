CREATE TABLE students_241020 (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      age INT NOT NULL,
      class VARCHAR(50) NOT NULL,
      score1 DECIMAL(5, 2) NOT NULL,  -- 数学成绩
      score2 DECIMAL(5, 2) NOT NULL,  -- 英语成绩
      score3 DECIMAL(5, 2) NOT NULL   -- 语文成绩
);

INSERT INTO students_241020 (name, age, class, score1, score2, score3) VALUES
('张三', 18, '高三1班', 90.50, 85.00, 92.00),
('李四', 17, '高三2班', 88.00, 90.50, 87.00),
('王五', 18, '高三1班', 92.00, 88.00, 89.00),
('赵六', 17, '高三2班', 85.00, 92.00, 90.50),
('孙七', 18, '高三1班', 87.00, 89.00, 91.00),
('周八', 17, '高三2班', 90.00, 87.00, 88.00),
('吴九', 18, '高三1班', 89.00, 91.00, 86.00),
('郑十', 17, '高三2班', 91.00, 86.00, 87.00);


# 获取每个班级的三科总分高于平均分（所有同学）的同学数量，并按照数量大小获取排名前三的班级，写出SQL语句

SELECT
    tmp1.class,  -- 选择班级字段
    count( tmp1.id ) AS `count`  -- 统计每个班级的学生数
FROM
    (
        -- 子查询，计算每个学生的总分
        SELECT s1.id, s1.class, s1.score1 + s1.score2 + s1.score3 AS `sum`
        FROM students_241020 s1
    ) tmp1
WHERE
    -- 条件判断，筛选出总分高于平均分的记录
    sum > (
        SELECT AVG( s.score1 + s.score2 + s.score3 )
        FROM students_241020 s
    )
GROUP BY
    tmp1.class  -- 按班级分组
ORDER BY
    count DESC  -- 按学生数降序排列
LIMIT 3;  -- 限制结果返回前三条记录




SELECT
    tmp1.class,  -- 选择班级字段
    count( tmp1.id ) AS `count`  -- 统计每个班级的学生数
FROM
    students_241020 tmp1
WHERE
    -- 条件判断，筛选出总分高于平均分的记录
    tmp1.score1 + tmp1.score2 + tmp1.score3 > (
        SELECT AVG( s.score1 + s.score2 + s.score3 )
        FROM students_241020 s
    )
GROUP BY
    tmp1.class  -- 按班级分组
ORDER BY
    count DESC  -- 按学生数降序排列
LIMIT 3;  -- 限制结果返回前三条记录



    -- 使用临时表

-- 计算平均分
WITH avg_score AS (
    SELECT AVG(score1 + score2 + score3) AS avg_sum
    FROM students_241020
),
-- 子查询，计算每个学生的总分
 student_scores AS (
     SELECT id, class, score1 + score2 + score3 AS `sum`
     FROM students_241020
 )


-- 查询总分高于平均分的班级及其学生数
SELECT
    class,  -- 选择班级字段
    COUNT(id) AS `count`  -- 统计每个班级的学生数
FROM
    student_scores
WHERE
    `sum` > (SELECT avg_sum FROM avg_score)
GROUP BY
    class  -- 按班级分组
ORDER BY
    `count` DESC  -- 按学生数降序排列
LIMIT 3;  -- 限制结果返回前三条记录
