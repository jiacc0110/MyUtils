package jiacc.myutilslib;

import java.io.UnsupportedEncodingException;

/**
 * Created by jiacc on 2017/1/29.
 */

public class StringUtils {

    public static boolean isNullOrEmpty(String str){
        return str == null||str.equals("");
    }

    public static String convertString(String str,String encoding){
        if(isNullOrEmpty(str))return "";
        String res = "";
        try {
            res = new String(str.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
        }
        return res;
    }


}
