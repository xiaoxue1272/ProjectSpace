package com.utils;

import java.sql.*;

/**
 * author:xiaoxue1272
 * date:2019/11/8-15:34
 */
public class MysqlConnection {


    //获取驱动连接数据库
    public static void CreatEntity(MysqlBean bean){
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(bean.getJdbcUrl(), bean.getUser(), bean.getPassowrd());
            PreparedStatement ps = con.prepareStatement("SHOW COLUMNS FROM " + bean.getTableName());
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}