package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * author:xiaoxue1272
 * date:2019/11/8-15:34
 */
public class MysqlConnection {


    public static void main(String[] args) {
        CreatEntity(new MysqlBean("jdbc:mysql://localhost:3306/week1?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai","root","127201","movie"));
    }

    //获取驱动连接数据库
    public static void CreatEntity(MysqlBean bean){
        Connection con = null;
        List<String> list = new ArrayList<>();
        try {
            //"SHOW COLUMNS FROM " + bean.getTableName()
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(bean.getJdbcUrl(), bean.getUser(), bean.getPassowrd());
            PreparedStatement ps = con.prepareStatement("SHOW COLUMNS FROM " + bean.getTableName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CreateNewBean("com/bean",bean.getTableName(),list);
        }
    }

    //io流创建文件（实体Bean）方法
    public static void CreateNewBean(String url,String TableName,List<String> columns){
        TableName = TableName.replace(TableName.substring(0,1),TableName.substring(0,1).toUpperCase());
        File BeanPackages = new File("src/main/java/"+url);
        if (!BeanPackages.exists()){
            BeanPackages.mkdirs();
        }
        File BeanPath = new File("src/main/java/" + url + "/" + TableName+".java");
        if (!BeanPath.exists()){
            try {
                BeanPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("src/main/java/" + url + "/" + TableName+".java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        url = url.replaceAll("\\\\",".").replaceAll("\\/",".");
        System.out.println(url);
        pw.write("package "+url+";" +
                "\n\n/**\n这是自动生成的实体bean，如有需要请手动改动\n*/\n" +
                "public class "+TableName+" {\n\n");
        for (String column : columns) {
            pw.write("  private String "+column+";\n");
        }
        pw.write("\n\n");
        for (String column : columns) {
            String Upcolumn = column.replace(column.substring(0,1),column.substring(0,1).toUpperCase());
            pw.write("  public String get"+Upcolumn+"() {\n");
            pw.write("      return "+column+";\n");
            pw.write("  }\n\n");

            pw.write("  public String set"+Upcolumn+"(String "+column+")"+" {\n");
            pw.write("      return this."+column+" = "+column+";\n");
            pw.write("  }\n\n");
        }
        pw.write(
                "   @Override\n" +
                "   public String toString() {\n" +
                "       return \""+TableName+"[\"+\n");
        for (int i = 0; i < columns.size(); i ++ ) {
            if (i>0){
                pw.write("          \","+columns.get(i)+"=\"+"+columns.get(i)+"+\n");
            }else {
                pw.write("          \""+columns.get(i)+"=\"+"+columns.get(i)+"+\n");
            }
        }
        pw.write("      \"]\";\n");
        pw.printf(" }");
        pw.printf("}");
        pw.flush();
        pw.close();
    }

}