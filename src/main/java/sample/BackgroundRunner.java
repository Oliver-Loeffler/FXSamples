package sample;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class BackgroundRunner {

    private AtomicLong count = new AtomicLong(0);

    private Executor executor = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    });

}
