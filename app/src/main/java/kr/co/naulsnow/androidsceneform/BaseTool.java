package kr.co.naulsnow.androidsceneform;

import android.util.Log;

public class BaseTool {

    public static void log(String tag, String message){
        tag = "arcore_"+tag;
        Log.w(tag, message);
    }

    public static void error(String tag, String message){
        tag = "arcore_"+tag;
        Log.e(tag, message);
    }
}
