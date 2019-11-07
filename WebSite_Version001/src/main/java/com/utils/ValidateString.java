package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author:xiaoxue1272
 * date:2019/11/7-10:08
 */
//自定义校验类
public class ValidateString {

    //自定义正则表达式验证字符串
    public static Boolean RegexString(String reg, String message){

        //如果可以验证，则开始验证
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(message);
        return m.matches();
    }

    //自定义字符串替换
    public static ValidateBean ReplactStrBean(String reg, String message){
        String regmesg = message;
        Boolean regbol=RegexString(reg,message);
        if (regbol){
            regmesg = message.replaceAll(reg,"");
        }else {
            regmesg = message;
        }
        return new ValidateBean(regbol,regmesg);
    }

    //自定义字符串替换
    public static String ReplactString(String reg, String message){
        String regmesg = message;
        Boolean regbol=RegexString(reg,message);
        if (regbol){
            regmesg = message.replaceAll(reg,"");
        }else {
            regmesg = message;
        }
        return regmesg;
    }
}