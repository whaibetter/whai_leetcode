package cn.whaifree.tech.DateDemo;

import org.junit.Test;

import java.sql.*;
import java.time.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 18:45
 * @注释
 */
public class DateDemo {


    @Test
    public void test() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(); // 获取当前时区的时间
        System.out.println(zonedDateTime);

        // 切换时区到其他地方 美国
        ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameInstant(java.time.ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime1);
    }

    /**
     *

     CREATE TABLE time_demo (
         id INT PRIMARY KEY AUTO_INCREMENT,
         datetime_column DATETIME,
         timestamp_column TIMESTAMP,
         date_column DATE,
         time_column TIME,
         year_column YEAR
     );

     * @throws SQLException
     */
    @Test
    public void testSQLDate() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_tranc", "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO time_demo (datetime_column, timestamp_column, date_column, time_column, year_column) VALUES (?, ?, ?, ?, ?)");


        // 使用当前的日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Year currentYear = Year.now();

        // 设置参数
        pstmt.setTimestamp(1, Timestamp.valueOf(currentDateTime)); // DATETIME
        pstmt.setTimestamp(2, Timestamp.valueOf(currentDateTime)); // TIMESTAMP
        pstmt.setDate(3, Date.valueOf(currentDate));              // DATE
        pstmt.setTime(4, Time.valueOf(currentTime));              // TIME
        pstmt.setInt(5, currentYear.getValue());// YEAR
        pstmt.execute();

        // 如果要要存储“公元1年1月1日11点11分”，只能使用MySQL的DATETIME类型
        // SET sql_mode = 'ALLOW_INVALID_DATES'; 在MySQL中允许存储更早的日期。
        //
        // pstmt.setTimestamp(1, Timestamp.valueOf("1911-01-01 11:11:11")); // DATETIME

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM time_demo");
        while (resultSet.next()) {
            System.out.println(resultSet.getTimestamp("datetime_column"));
            System.out.println(resultSet.getTimestamp("timestamp_column"));
            System.out.println(resultSet.getDate("date_column"));
            System.out.println(resultSet.getTime("time_column"));
            System.out.println(resultSet.getInt("year_column"));
        }

    }

}
