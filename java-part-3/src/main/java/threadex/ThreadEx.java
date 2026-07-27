package threadex;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

class PrintDash extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("-");
        }
    }
}

class PrintBar extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("|");
        }
    }
}

class SleepThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println(i);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("SleepThread 종료");
    }
}

class CountThread extends Thread {
    @Override
    public void run() {
        int i = 10;
        while(i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for (long x =0; x < 2_500_000_000L; x++);
        }
        System.out.println("카운트 종료");
    }
}

class CountSleepThread extends Thread {
    @Override
    public void run() {
        int i = 10;
        while(i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("자다가 깨어남 (InterruptedException)");
                break;
            }
        }
        System.out.println("카운트 종료");
    }
}

class YieldThread extends Thread {
    private String name;
    public YieldThread(String name) { this.name = name; }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " 실행 중. 반복: " + i);
            Thread.yield();
            try { Thread.sleep(500); } catch (InterruptedException e) { break; }
        }
    }
}

class ManyPrintThread extends Thread {
    private char name;

    public ManyPrintThread(char name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println(name);
        }
    }
}

public class ThreadEx {

    public static void main(String[] args) {
        /*PrintDash pd = new PrintDash();
        PrintBar pb = new PrintBar();

        pd.start();
        pb.start();*/

        /*SleepThread sleepThread = new SleepThread();
        sleepThread.start();
        try {
            sleepThread.sleep(2000);
            // main 스레드를 재움
            // Thread.sleep(2000) -> 실제 컴파일러가 실행하는 코드
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*CountThread countThread = new CountThread();
        countThread.start();
        new Scanner(System.in).nextLine();
        countThread.interrupt();*/

        /*CountSleepThread countSleepThread = new CountSleepThread();
        countSleepThread.start();
        new Scanner(System.in).nextLine();
        countSleepThread.interrupt();*/

        /*new YieldThread("thread1").start();
        new YieldThread("thread2").start();*/

        ManyPrintThread t1 = new ManyPrintThread('-');
        ManyPrintThread t2 = new ManyPrintThread('|');
        t1.start();
        t2.start();

        long start = System.currentTimeMillis();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("소요시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
