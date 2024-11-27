//package com.whai.springcloud.mybatis.mybatisplus.mybatis;
//
//import com.whai.springcloud.mybatis.mybatisplus.mybatis.domain.AmountBig;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.*;
//
///**
// * @version 1.0
// * @Author whai文海
// * @Date 2024/11/15 10:52
// * @注释
// */
//public class AmountTypeHandler  extends BaseTypeHandler<AmountBig> {
//
//    /**
//     * 该方法用于将 AmountBig 枚举类型的值设置到 PreparedStatement 中，
//     * @param ps
//     * @param i
//     * @param parameter
//     * @param jdbcType
//     * @throws SQLException
//     */
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, AmountBig parameter, JdbcType jdbcType) throws SQLException {
//        if (parameter.equals(AmountBig.BIG)) {
//            ps.setDouble(i, 1000000000D);
//        }else {
//            ps.setDouble(i, 0D);
//        }
//    }
//
//    @Override
//    public AmountBig getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        double aDouble = rs.getDouble(columnName);
//        if (aDouble == 1000000000D) {
//            return AmountBig.BIG;
//        }
//        return AmountBig.SMALL;
//    }
//
//    @Override
//    public AmountBig getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public AmountBig getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        return null;
//    }
//}
