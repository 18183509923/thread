import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateDemo {
    public static void main(String[] args) {
        System.out.println(new Date()+"\n");
        try {
            Thread.sleep(1000*3);
            System.out.println(new Date()+"\n");
        } catch (InterruptedException e) {
            System.out.println("gou an exception");
        }
    }
}
