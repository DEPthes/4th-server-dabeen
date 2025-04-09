// 람다식 Runnable vs 기존 익명 클래스 Runnable

public class RunnableComparisonExample {
    public static void main(String[] args) {
        // 1. 람다식
        Thread t1 = new Thread(() -> {
            System.out.println("람다로 실행 중!");
        });
        t1.start();

        // 2. 기존 방식 (익명 클래스)
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("익명 클래스 실행 중!");
            }
        };
        Thread t2 = new Thread(r);
        t2.start();
    }
}
