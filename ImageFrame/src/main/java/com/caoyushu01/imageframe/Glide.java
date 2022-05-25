package com.caoyushu01.imageframe;

import android.content.Context;

/**
 * Api接口类。仿造Glide的调用方式提供对应的加载方法.
 */
public class Glide {
    private static String TAG = "Glide";


    public static void init() {
        BitmapRequestManager.getIns().init();
    }

    public static BitmapRequest with(Context context) {
        if (context == null) {
            throw new NullPointerException(TAG + ":null context is not allowed.");
        }
        BitmapRequest bitmapRequest = new BitmapRequest(context);
        return bitmapRequest;
    }


}
