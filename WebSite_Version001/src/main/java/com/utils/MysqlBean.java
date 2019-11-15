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
    private String beanPackagesName;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl , String DataBaseName) {
        this.jdbcUrl = "jdbc:mysql://"+jdbcUrl+"/"+DataBaseName+"?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
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

    public String getBeanPackagesName() {
        return beanPackagesName;
    }

    public void setBeanPackagesName(String beanPackagesName) {
        this.beanPackagesName = beanPackagesName;
    }

    public MysqlBean(String jdbcUrl, String DataBaseName, String user, String passowrd, String tableName, String beanPackagesName) {
        this.jdbcUrl = "jdbc:mysql://"+jdbcUrl+"/"+DataBaseName+"?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        this.user = user;
        this.passowrd = passowrd;
        this.TableName = tableName;
        this.beanPackagesName = beanPackagesName;
    }

    @Override
    public String toString() {
        return "MysqlBean{" +
                "jdbcUrl='" + jdbcUrl + '\'' +
                ", user='" + user + '\'' +
                ", passowrd='" + passowrd + '\'' +
                ", TableName='" + TableName + '\'' +
                ", beanPackagesName='" + beanPackagesName + '\'' +
                '}';
    }
}