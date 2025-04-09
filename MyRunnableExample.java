// Runnable 인터페이스 구현 예제

public class MyRunnableExample {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start(); // Runnable의 run() 실행
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable 구현 방식 실행 중!");
    }
}