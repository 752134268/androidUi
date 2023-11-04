package com.wuang.baselibrary;

import android.app.Application;

import com.wuang.baselibrary.utils.ToastUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseLibraryApplication extends Application {
    private static ExecutorService executorService;
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            int threadNumber = counter++;
            return new Thread(runnable, "MyThread-" + threadNumber);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.getInstance().init(this);

    }
    public void shutDown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public static ExecutorService getCachedThreadPoll() {

        if (executorService == null) {
            executorService = new ThreadPoolExecutor(
                    5,
                    10,
                    60,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    THREAD_FACTORY
            );
        }
        return executorService;
    }
}
