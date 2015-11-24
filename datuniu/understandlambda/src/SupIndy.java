import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by nuc on 2015/11/24.
 */
public class SupIndy {
    public Date theIndy(long hours, long min) {
        Supplier<Date> sup = () -> new Date(System.currentTimeMillis() + (1000 * 60 * hours));
        Date d = sup.get();


        BiFunction<Long, Long, String> func = (first, second) ->
                String.valueOf(first + second + hours + min);
        String str = func.apply(8L, 9L);

        return d;
    }

    public static void main(String[] args) {
        SupIndy aa = new SupIndy();
        aa.theIndy(12L, 45L);
    }
}
