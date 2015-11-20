import java.util.*;

/**
 * Created by nuc on 2015/11/20.
 */
public class CompileOK {
    public static void shouldBeOK() {
        List<String> ls =
                Collections.checkedList(new ArrayList<>(), String.class);

        boolean flag = new Random().nextInt() % 2 == 0;
        Set<Integer> si = flag ? Collections.singleton(23) : Collections.emptySet();
    }
}
