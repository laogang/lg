import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/28 21:42
 * @description:
 **/
public class TestTime {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime.toLocalDate());

    }
}
