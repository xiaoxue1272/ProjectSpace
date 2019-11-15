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
        CreatEntity(new MysqlBean("localhost:3306","week1","root","127201","movie","com/bean"));
    }


    //获取驱动连接数据库
    public static void CreatEntity(MysqlBean bean){
        Connection con = null;
        List<String> columns = new ArrayList<>();
        List<String> columnsType = new ArrayList<>();
        try {
            //"SHOW COLUMNS FROM " + bean.getTableName()
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(bean.getJdbcUrl(), bean.getUser(), bean.getPassowrd());
            PreparedStatement ps = con.prepareStatement("SHOW COLUMNS FROM " + bean.getTableName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                columns.add(rs.getString(1));
                System.out.println(rs.getString(2));
                String type = "";
                if (rs.getString(2).contains("int") || rs.getString(2).contains("tinyint") || rs.getString(2).contains("bigint")) {
                    type = "Integer";
                } else if (rs.getString(2).contains("float") || rs.getString(2).contains("double")) {
                    type = "Double";
                } else if (rs.getString(2).contains("date")||rs.getString(2).contains("datetime")){
                    type = "Date";
                } else {
                    type = "String";
                }
                columnsType.add(type);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CreateNewBean(bean.getBeanPackagesName(),bean.getTableName(),columns,columnsType);
        }
    }

    //io流创建文件（实体Bean）方法
    public static void CreateNewBean(String packageName,String TableName,List<String> columns,List<String> columnsType){
        TableName = TableName.replace(TableName.substring(0,1),TableName.substring(0,1).toUpperCase());
        File file = new File("src");
        File BeanPackages = new File(file.getAbsolutePath()+"/main/java/"+packageName);
        if (!BeanPackages.exists()){
            BeanPackages.mkdirs();
        }
        File BeanPath = new File(file.getAbsolutePath()+"/main/java/" + packageName + "/" + TableName+".java");
        if (!BeanPath.exists()){
            try {
                BeanPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file.getAbsolutePath()+"/main/java/" + packageName + "/" + TableName+".java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        packageName = packageName.replaceAll("\\\\",".").replaceAll("\\/",".");
        System.out.println(packageName);
        pw.write("package "+packageName+";" +
                "\n\n/**\n这是自动生成的实体bean，如有需要请手动改动\n*/\n");
        if (columnsType.contains("Date")){
            pw.write("import java.util.Date;\n");
        }
        pw.write("public class "+TableName+" {\n\n");
        for (int i = 0 ; i < columns.size() ; i ++) {
            pw.write("  private "+columnsType.get(i)+" "+columns.get(i)+";\n");
        }
        pw.write("\n\n");
        for (int i = 0 ; i < columns.size() ; i ++) {
            String Upcolumn = columns.get(i).replace(columns.get(i).substring(0,1),columns.get(i).substring(0,1).toUpperCase());
            pw.write("  public "+columnsType.get(i)+" get"+Upcolumn+"() {\n");
            pw.write("      return "+columns.get(i)+";\n");
            pw.write("  }\n\n");

            pw.write("  public void set"+Upcolumn+"("+columnsType.get(i)+" "+columns.get(i)+")"+" {\n");
            pw.write("      this."+columns.get(i)+" = "+columns.get(i)+";\n");
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