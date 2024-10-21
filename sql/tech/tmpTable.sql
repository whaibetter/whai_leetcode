-- 使用临时表

-- 计算平均分
WITH
avg_score AS (
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


WITH avg_score as
    (SELECT * FROM test_table)
SELECT * FROM avg_score;
