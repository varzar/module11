package task1;

public class MessengerOfThread {
    private final Object MONITOR = new Object();
    volatile int sum = 0;
    private int period1;
    private int period2;

    public MessengerOfThread() {
        this.period1 = 1;
        this.period2 = 5;
    }

    public MessengerOfThread(int period1, int period2) {
        this.period1 = period1;
        this.period2 = period2;
    }

    public void startThread() {
        Thread thread1 = createThread1(period1);
        thread1.start();
        Thread thread2 = createThread2(period2);
        thread2.start();
    }

    private Thread createThread1(int period) {
        return new Thread(() -> {
            synchronized (MONITOR) {
                while (true) {
                    MONITOR.notifyAll();
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum++;
                    System.out.println(sum + " seconds ");
                    try {
                        Thread.sleep(period * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Thread createThread2(int period) {
        return new Thread(() -> {
            synchronized (MONITOR) {
                while (true) {
                    if (sum % period == 0 && sum != 0 && period != 1) {
                        System.out.println("Passed " + period + " seconds");
                    }
                    MONITOR.notifyAll();
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
