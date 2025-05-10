// Thread 클래스를 상속한 예제

public class MyThreadExample {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start(); // 새로운 스레드에서 run() 실행
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread 상속 방식 실행 중...");
    }
}