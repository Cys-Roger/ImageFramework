package com.caoyushu01.imageframe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.caoyushu01.imageframe.utils.Md5Util;

import java.lang.ref.WeakReference;

/**
 * 单次加载一个图片的请求实体
 */
public class BitmapRequest {
    /**
     * context
     */
    Context context;

    WeakReference<ImageView> viewHolder;

    /**
     * the image network url
     */
    String url;
    /**
     * default image place holder
     */
    int placeHolder;
    /**
     * error image place holder
     */
    int errorHolder;

    /**
     * request result Listener
     */
    BitmapRequestListener listener;

    /**
     * assign the request priority, locale is [0,5], higher is bigger.
     */
    int priority = 5;

    /**
     * unique each BitmapRequest.
     */
    String key;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    public BitmapRequest load(String url) {
        this.url = url;
        genKey(url);
        return this;
    }

    /**
     * @return
     */
    public BitmapRequest placeHolder(int placeHolder) {
        this.placeHolder = placeHolder;
        return this;
    }

    public BitmapRequest errorHolder(int errorHolder) {
        this.errorHolder = errorHolder;
        return this;
    }

    public BitmapRequest listener(BitmapRequestListener listener) {
        this.listener = listener;
        return this;
    }

    public BitmapRequest priority(int priority) {
        if (priority < 0) {
            this.priority = 0;
        } else if (priority <= 5) {
            this.priority = priority;
        }
        return this;
    }

    public void into(ImageView imageView) {
        if (viewHolder != null) {
            viewHolder.clear();
        }
        imageView.setTag(key);
        viewHolder = new WeakReference<>(imageView);
        fillPlaceBitmap();
        // put this bitmap request into request queues.
        enqueue();
    }

    /**
     * 将图片填充至目标图片
     *
     * @param bitmap
     */
    public void fillTargetBitmap(Bitmap bitmap) {
        if (viewHolder.get() != null) {
            viewHolder.get().setImageBitmap(bitmap);
        }
    }

    /**
     * 将图片填充至占位图片
     */
    private void fillPlaceBitmap() {
        // the user does not want to show an error image
        if (placeHolder == 0) {
            return;
        }
        if (viewHolder.get() != null) {
            viewHolder.get().setImageResource(placeHolder);
        }
    }

    /**
     * 将图片填充至出错图片
     */
    public void fillErrorBitmap() {
        // the user does not want to show an error image
        if (errorHolder == 0) {
            return;
        }
        if (viewHolder.get() != null) {
            viewHolder.get().setImageResource(errorHolder);
        }
    }

    public String getUrl() {
        return url;
    }

    private void enqueue() {
        BitmapRequestManager.getIns().enqueue(this);
    }

    private void genKey(String oriKey) {
        this.key = Md5Util.stringToMD5(oriKey);
    }

    public interface BitmapRequestListener {
        void onSuccess();

        void onError(int errCode);
    }

}
