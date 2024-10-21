SET @row_number = 0;

SELECT
    (@row_number := @row_number + 1) AS row_num, -- :=是MYSQL的赋值运算
    t.*
FROM
    test_table t
ORDER BY
    t.id;
