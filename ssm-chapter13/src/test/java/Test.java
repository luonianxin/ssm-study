import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        Date date2 = new Date(System.currentTimeMillis()+600000);
        System.out.println(date.compareTo(date2));
    }
}
