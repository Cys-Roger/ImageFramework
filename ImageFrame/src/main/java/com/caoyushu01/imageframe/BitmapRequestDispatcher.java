package com.caoyushu01.imageframe;

import com.caoyushu01.imageframe.network.NetworkHelper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * holding the whole thread pool to consume the request queue
 */
public class BitmapRequestDispatcher {

    private static String TAG = Const.MODULE_TAG + "ReqDispatcher";
    /**
     * thread poll executor
     */
    private final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final int MAX_POOL_SIZE = 100;
    private final long KEEP_ALIVE_TIME = 50;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(null, r,
                    TAG + "-Worker-" + threadNumber.getAndIncrement());
            if (t.isDaemon())
                t.setDaemon(false);
            return t;
        }
    };
    ExecutorService mExecutor;
    NetworkHelper networkHelper;

    public BitmapRequestDispatcher() {
        mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                workQueue,
                threadFactory);
        networkHelper = new NetworkHelper();
    }

    public BitmapRequestDispatcher work(BlockingQueue<BitmapRequest> requestBlockingQueue) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        BitmapRequest request = requestBlockingQueue.take();
                        mExecutor.submit(new Runnable() {
                            @Override
                            public void run() {
                                networkHelper.requestBitmap(request);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, TAG + "-Dispatcher");
        thread.start();
        return this;
    }


}
