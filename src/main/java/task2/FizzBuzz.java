package task2;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    private List <String> list = new ArrayList<>();
    volatile private int cursor = 1;
    private  final int number;
    private final Object MONITOR = new Object();
    private Thread a;
    private Thread b;
    private Thread c;
    private Thread d;

    public Thread getA() {
        return a;
    }

    public Thread getB() {
        return b;
    }

    public Thread getC() {
        return c;
    }

    public Thread getD() {
        return d;
    }

    public FizzBuzz(int number) {
        this.number = number;
    }

    private boolean fizz(){
        return cursor % 3 == 0 && cursor % 15 != 0;
    }

    private boolean buzz(){
        return cursor % 5 == 0 && cursor % 15 != 0;
    }

    private boolean fizzbuzz(){
        return cursor % 15 == 0;
    }

    private boolean number(){
        return cursor % 3 != 0 && cursor % 5 != 0;
    }

    public List<String> startThread(){

        a = createAThread();
        a.start();

        b = createBThread();
        b.start();

        c = createCThread();
        c.start();

        d = createDThread();
        d.start();
        return list;
    }

    public Thread createAThread(){
        return new Thread(() -> {
            synchronized(MONITOR){
                int step = number / 3 - number / 15;
                int i = 0;
                while (i < step){
                    if(fizz()){
                        list.add("fizz");
                        //System.out.println("fizz");
                        cursor++;
                        i++;
                        MONITOR.notifyAll();
                    }else {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public Thread createBThread(){
        return new Thread(() -> {
            synchronized(MONITOR){
                int step = number / 5 - number / 15;
                int i = 0;
                while (i < step){
                    if(buzz()){
                        list.add("buzz");
                        //System.out.println("buzz");
                        cursor++;
                        i++;
                        MONITOR.notifyAll();
                    }else {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public Thread createCThread(){
        return new Thread(() -> {
            synchronized(MONITOR){
                int step = number / 15;
                int i = 0;
                while (i < step){
                    if(fizzbuzz()){
                        list.add("fizzbuzz");
                        //System.out.println("fizzbuzz" + ".");
                        cursor++;
                        i++;
                        MONITOR.notifyAll();
                    }else {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public Thread createDThread(){
        return new Thread(() -> {
            synchronized(MONITOR){
                int step = number - number / 3 - number / 5 + number / 15;
                int i = 0;
                while (i < step){
                    if(number()){
                        list.add(String.valueOf(cursor));
                        //System.out.print(cursor + ", ");
                        cursor++;
                        i++;
                        MONITOR.notifyAll();
                    }else {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
