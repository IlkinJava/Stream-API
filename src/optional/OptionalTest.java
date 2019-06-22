package optional;

import java.util.Optional;

/**
 * Optional - новый класс в пакете java.util, является контейнером (оберткой) для значений которая также может
 * безопасно содержать null.
 * Благодаря опциональным типам можно забыть про проверки на null и NullPointerException.
 */

public class OptionalTest {
    public static void main(String[] args) {

    }

    /**
     * В метод Optional.of нельзя передавать null, если конечно мы не хотим получить NullPointerException
     */
    static void test() {
        Optional<String> name = Optional.of("John");
        System.out.println(name); //output Optional[John]
    }

    static void test1() {
        Optional<String> name = Optional.of(null); // java.lang.NullPointerException
        System.out.println(name);
    }

    /**
     * А вот в метод Optional.ofNullable передавать null можно безопасно
     */
    static void test2() {
        Optional<String> name = Optional.ofNullable("John");
        System.out.println(name); //output Optional[John]
    }

    static void test3() {
        String john = null;
        Optional<String> name = Optional.ofNullable(john);
        System.out.println(name); //output Optional.empty
    }

    static void test4() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional); //output Optional.empty
    }

    /**
     * Для получения значения из Optional используется метод Optional.get, но он является небезопасным и может
     * бросить NoSuchElementException
     */
    static void test5() {
        Optional<String> name = Optional.of("John");
        System.out.println(name.get()); //output John

        Optional<Object> nullOptional = Optional.ofNullable(null);
        System.out.println(nullOptional.get()); // java.util.NoSuchElementException: No value present
    }

    /**
     * Метод Optional.isPresent возвращает true, если значение в нем присутствует, иначе возвращает false
     * Метод Optional.get лучше использовать в паре с Optional.isPresent чтобы предотвратить исключения
     */
    static void test6() {
        Optional<String> name = Optional.of("John");
        if (name.isPresent()) {             //условие выполнится и мы увидим имя
            System.out.println(name.get()); //output John
        }

        Optional<Object> empty = Optional.empty();
        if (empty.isPresent()) {            //условие не выполнится, значит исключения не будет
            System.out.println(empty.get());
        }
    }

    /**
     * Метод Optional.ifPresent выполняет переданное действие, если значение в Optional присутствует, иначе игнорирует его.
     * Метод принимает лямбда-выражение известное как потребитель (Consumer).
     */
    static void test7() {
        Optional<String> name = Optional.of("John");
        name.ifPresent(val -> System.out.println(val)); //условие выполнится и мы увидим John

        Optional<Object> empty = Optional.empty();
        empty.ifPresent(System.out::println); //условие не выполнится, действие игнорируется
    }

    /**
     * Метод Optional.orElse возвращает переданное значение, если Optional пустой
     */
    static void test8() {
        Optional<String> name = Optional.of("John");
        System.out.println(name.orElse("blank")); //output John

        Optional<Object> empty = Optional.empty();
        System.out.println(empty.orElse("blank")); //output blank
    }

    /**
     * Метод Optional.orElseGet возвращает переданное значение из лямда-выражение , если Optional пустой
     */

    static void test9() {
        Optional<String> name = Optional.of("John");
        System.out.println(name.orElseGet(() -> "blank")); //output John

        Optional<Object> empty = Optional.empty();
        System.out.println(empty.orElseGet(() -> "blank")); //output blank
    }

    /**
     * Метод Optional.orElseThrow бросает переданное исключение , если Optional пустой
     */
    static void test10() {
        Optional<String> name = Optional.of("John");
        String nameValue = name.orElseThrow(() -> new RuntimeException());
        System.out.println(nameValue);                                      //output John

        Optional<Object> empty = Optional.empty();
        Object emptyValue = empty.orElseThrow(RuntimeException::new); //java.lang.RuntimeException
    }

    /**
     * Метод Optional.map служит для преобразования значения внутри Optional.
     * Если Optional пустой преобразование не будет происходить
     */

    static void test11() {
        Optional<String> name = Optional.of("JOHN");
        System.out.println(name.map(String::toLowerCase));  //output Optional[john]

        Optional<String> empty = Optional.empty();
        System.out.println(empty.map(String::toLowerCase)); //output Optional.empty
    }
}