import java.util.Date;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by nuc on 2015/11/24.
 */
public class SupIndy {
    public Date theIndy(long hours, String min) {
//        Supplier<Date> sup = () -> new Date(System.currentTimeMillis() + (1000 * 60 * hours));
//        Date d = sup.get();


        BiFunction<Long, Long, String> func = (first, second) ->
                String.valueOf(first + second + hours) + min + getCurTime() + getRandom();
        String str = func.apply(8L, 9L);

        return new Date();
    }

    public String getCurTime() {
        return String.valueOf(System.currentTimeMillis() + new Random().nextLong());
    }

    public String getRandom() {
        return String.valueOf(new Random().nextLong());
    }

    public static void main(String[] args) {
        SupIndy aa = new SupIndy();
        aa.theIndy(12L, "marvin");
    }
}
