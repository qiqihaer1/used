package com.keduox.test;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 * 要求，将手机号的前七位改为*，只输出后四位原数据
 */
public class MyUdf extends UDF{

    public String evaluate(Text text){
        if(text.toString().length()!=11||text==null){
            return "输入手机号长度错误！";
        }
        StringBuffer sb=new StringBuffer("*******");
        sb.append(text.toString().substring(7,11));
        return sb.toString();
    }


    public static void main(String[] args) {
        MyUdf mu=new MyUdf();
        System.out.println(mu.evaluate(new Text("13568029815")));
    }
}
