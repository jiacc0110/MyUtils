package jiacc.myutilslib;

import android.util.Log;

/**
 * Created by jiacc on 2018/1/29.
 */

public class LogUtils {

    private static boolean closeLog = false;

    public static void logI(String tag,String msg){
        if(closeLog) return;
        Log.i(tag,msg);
    }

    public static void logD(String tag,String msg){
        if(closeLog) return;
        Log.d(tag,msg);
    }

    public static void logW(String tag,String msg){
        if(closeLog) return;
        Log.w(tag,msg);
    }

    public static void logE(String tag,String msg){
        if(closeLog) return;
        Log.e(tag,msg);
    }

    public static void logV(String tag,String msg){
        if(closeLog) return;
        Log.v(tag,msg);
    }

}
