// Runnable 인터페이스를 람다식으로 구현한 예제

public class LambdaRunnableExample {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("람다식 Runnable 실행 중!");
        });
        t.start();
    }
}