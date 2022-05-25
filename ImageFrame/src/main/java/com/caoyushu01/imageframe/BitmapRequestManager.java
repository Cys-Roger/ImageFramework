package com.caoyushu01.imageframe;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class BitmapRequestManager {

    private static final String TAG = Const.MODULE_TAG+"ReqManager";

    private boolean isInit = false;

    /**
     * request queue
     */
    private static final int QUEUE_CAPACITY = 5;
    private PriorityBlockingQueue<BitmapRequest> mQueue;

    private BitmapRequestDispatcher mReqDispatcher;

    private static class InstanceHolder {
        private static final BitmapRequestManager singleton = new BitmapRequestManager();
    }

    public static BitmapRequestManager getIns() {
        return InstanceHolder.singleton;
    }

    public void init() {
        mQueue = new PriorityBlockingQueue<BitmapRequest>(QUEUE_CAPACITY, new Comparator<BitmapRequest>() {
            @Override
            public int compare(BitmapRequest o1, BitmapRequest o2) {
                return o1.priority - o2.priority;
            }
        });
        mReqDispatcher = new BitmapRequestDispatcher().work(mQueue);
        isInit = true;
    }

    public void enqueue(BitmapRequest request) {
        checkInit();
        if(!mQueue.contains(request)) {
            mQueue.add(request);
        }
    }

    public void dequeue(BitmapRequest request) {
        checkInit();
        if(mQueue.contains(request)) {
            mQueue.remove(request);
        }
    }

    public PriorityBlockingQueue getQueue() {
        checkInit();
        return mQueue;
    }

    private BitmapRequestManager() {

    }

    private void checkInit() throws IllegalStateException {
        if(!isInit) {
            throw new IllegalStateException(TAG+"you should init glide first");
        }
    }


}
