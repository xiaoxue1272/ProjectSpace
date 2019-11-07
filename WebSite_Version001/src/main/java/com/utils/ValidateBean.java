package com.utils;

/**
 * author:xiaoxue1272
 * date:2019/11/7-10:13
 */
public class ValidateBean {

    //结果信息
    private Boolean regMessage;

    private String replaceStr;


    public Boolean getRegMessage() {
        return regMessage;
    }

    public void setRegMessage(Boolean regMessage) {
        this.regMessage = regMessage;
    }

    public String getReplaceStr() {
        return replaceStr;
    }

    public void setReplaceStr(String replaceStr) {
        this.replaceStr = replaceStr;
    }

    @Override
    public String toString() {
        return "ValidateBean{" +
                "regMessage=" + regMessage +
                ", replaceStr='" + replaceStr + '\'' +
                '}';
    }

    public ValidateBean(Boolean regMessage, String replaceStr) {
        this.regMessage = regMessage;
        this.replaceStr = replaceStr;
    }
}