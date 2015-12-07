import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by nuc on 2015/11/24.
 */
public class SupIndy {
    public Date theIndy(long hours, String min) {
        BiFunction<Long, Long, String> func = (first, second) -> {
            return String.valueOf(first + second + hours) + min + getCurTime() + getRandom();
        };
        printBiFunction(func);
        String str = func.apply(8L, 9L);
        return new Date();
    }


    public String getCurTime() {
        return String.valueOf(System.currentTimeMillis() + new Random().nextLong());
    }

    public String getRandom() {
        return String.valueOf(new Random().nextLong());
    }

    private void printBiFunction(Object obj) {
        System.out.println(obj.getClass() + " BiFunction isSynthetic: " + obj.getClass().isSynthetic());
        Set<Method> methods = new LinkedHashSet<>();
        // 所有public method，包括继承的来的
        methods.addAll(Arrays.asList(this.getClass().getMethods()));
        // 所有 public/protected/private/friendly method，但，不包括继承的来的
        methods.addAll(Arrays.asList(this.getClass().getDeclaredMethods()));

        for (Method method : methods) {
            System.out.print(method);
            System.out.println("-----method synthetic: " + method.isSynthetic());
        }

        Set<Field> fields = new LinkedHashSet<>();
        // 所有public fields，包括继承的来的
        fields.addAll(Arrays.asList(obj.getClass().getFields()));
        // 所有 public/protected/private/friendly fields，但，不包括继承的来的
        fields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
        System.out.println();
        for (Field field : fields) {
            System.out.print(field);
            System.out.println("-----field synthetic: " + field.isSynthetic());
        }
    }

    public void notCapture() {
        Function<String, Date> func = (str) -> new Date(Long.parseLong(str));
        Date d = func.apply((System.currentTimeMillis() + 1000L) + "");
        System.out.println(d);
    }

    private static class YesImStatic {
        public void hithere() {
            System.out.println("Hi There");
        }
    }

    private class ImInstance {
        public void hithere() {
            System.out.println("Hi There");
        }
    }

    public static void main(String[] args) {
        SupIndy aa = new SupIndy();
        aa.theIndy(12L, "marvin");
        aa.notCapture();
    }
}
