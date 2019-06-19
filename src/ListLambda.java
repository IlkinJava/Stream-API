import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLambda {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

//        numbers.forEach(System.out::println);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 100);
        map.put(2, 200);
        map.put(3, 300);

//        map.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        list.forEach(number -> {
            if (number > 2) {
                System.out.println(number);
            }
        });
    }
}
