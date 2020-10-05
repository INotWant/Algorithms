package other;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    public static void main(String[] args) throws InterruptedException, IOException {
//        System.out.println(RUNNING);
//        System.out.println(SHUTDOWN);
//        System.out.println(STOP);
//        System.out.println(TIDYING);
//        System.out.println(TERMINATED);

//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();
//
//        Thread thread = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                System.out.println("start");
//                lock.lock();
//                System.out.println("end");
//            }
//        });
//
//        thread.start();
//        thread.interrupt();
//        Thread.sleep(5000);
//        lock.unlock();

        StreamTokenizer streamTokenizer = new StreamTokenizer(new InputStreamReader(System.in));

        streamTokenizer.ordinaryChar('/');
        streamTokenizer.slashSlashComments(true);
//        streamTokenizer.slashSlashComments(false);

        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            System.out.println("==");
            System.out.println(streamTokenizer.toString());
        }
    }

}
