import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试AtomicInteger
 */
public class UseAtomicInt {

    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get());
    }
}
