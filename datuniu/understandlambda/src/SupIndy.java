import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Created by nuc on 2015/11/24.
 */
public class SupIndy {
    public Date theIndy(long hours, String min) {
        BiFunction<Long, Long, String> func = (first, second) -> {
            Method m = new Object() {}.getClass().getEnclosingMethod();
            System.out.println(m.getName() + " isSynthetic: " + m.isSynthetic());

            return String.valueOf(first + second + hours) + min + getCurTime() + getRandom();
        };
        String str = func.apply(8L, 9L);
        return new Date();
    }


    public String getCurTime() {
        return String.valueOf(System.currentTimeMillis() + new Random().nextLong());
    }

    public String getRandom() {
        return String.valueOf(new Random().nextLong());
    }

    private void printBiFunction(BiFunction<Long, Long, String> func) {
        Set<Method> methods = new LinkedHashSet<>();
        // 所有public method，包括继承的来的
        methods.addAll(Arrays.asList(this.getClass().getMethods()));
        // 所有 public/protected/private/friendly method，但，不包括继承的来的
        methods.addAll(Arrays.asList(this.getClass().getDeclaredMethods()));

        for (Method method : methods) {
            System.out.println("method: " + method.getName());
            Class[] paraTypes = method.getParameterTypes();
            for (Class paraType : paraTypes) {
                System.out.println("----" + paraType.getName());
            }
            System.out.println("-----" + method.getReturnType());
            System.out.println("-----synthetic: " + method.isSynthetic());
        }

        Set<Field> fields = new LinkedHashSet<>();
        // 所有public fields，包括继承的来的
        fields.addAll(Arrays.asList(func.getClass().getFields()));
        // 所有 public/protected/private/friendly fields，但，不包括继承的来的
        fields.addAll(Arrays.asList(func.getClass().getDeclaredFields()));
        for (Field field : fields) {
            System.out.println("field: " + field.getName());
            System.out.println("----" + field.getType());
        }
    }

    public static void main(String[] args) {
        SupIndy aa = new SupIndy();
        aa.theIndy(12L, "marvin");
    }
}
