package com.caoyushu01.imageframe.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.caoyushu01.imageframe.BitmapRequest;
import com.caoyushu01.imageframe.Const;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkHelper {
    private static final String TAG = Const.MODULE_TAG + "NetworkHelper";
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    OkHttpClient client;

    public NetworkHelper() {
        client = new OkHttpClient.Builder().build();
    }

    public void requestBitmap(BitmapRequest bitmapRequest) {
        Log.i(TAG, "request Bitmap in Thread:"+Thread.currentThread().getName());
        Request request = new Request.Builder()
                .url(bitmapRequest.getUrl())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "request Bitmap Error:" + call.request().url());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Bitmap resultBitmap = BitmapFactory.decodeStream(response.body().byteStream());
                mainHandler.post(() -> {
                    bitmapRequest.fillTargetBitmap(resultBitmap);
                });
            }
        });
    }
}
