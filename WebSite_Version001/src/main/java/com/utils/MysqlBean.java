package com.utils;

/**
 * author:xiaoxue1272
 * date:2019/11/8-15:35
 */
public class MysqlBean {
    
    //基本连接数据源
    private String jdbcUrl;
    private String user;
    private String passowrd;
    private String TableName;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public MysqlBean(String jdbcUrl, String user, String passowrd, String tableName) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.passowrd = passowrd;
        TableName = tableName;
    }
}