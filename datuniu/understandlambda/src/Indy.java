import java.util.function.Function;

/**
 * Created by nuc on 2015/11/22.
 */
public class Indy {
    public String theIndy(int passedIn) {
        Function<Integer, String> func = (i) -> String.valueOf(i + passedIn);
        String str = func.apply(1);
        return str;
    }

    public static void main(String[] args) {
        Indy aa = new Indy();
        aa.theIndy(10);
    }
}
