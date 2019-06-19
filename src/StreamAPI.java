import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Stream API  - новый способ взаимодействия с данными, представляя их в виде конечного потока данных.
 */
public class StreamAPI {
    public static void main(String[] args) {

    }

    /**
     * Один из методов Stream-ов - filter принимает лямбда-выражение известное как предикат,
     * необходимое для фильтрации данных по какому-то условию.
     */
    static void getEvenNumbers() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (Integer i : numbers) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        //------------------
        List<Integer> numbers2 = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers2.stream()
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
    }

    static void countOfLengthGreaterThanFour() {
        List<String> names = asList("John", "Jan", "Tirion", "Marry", "Nikolas");

        int counter = 0;
        for (String name : names) {
            if (name.length() > 4) {
                counter++;
            }
        }
        System.out.println(counter); //output 3
        //-----------------------
        List<String> names2 = asList("John", "Jan", "Tirion", "Marry", "Nikolas");

        long count = names2.stream()
                .filter(i -> i.length() > 4)
                .count();
        System.out.println(count); //output  3
    }

    static void checkLetter() {
        List<String> list = asList("John", "Alan", "Sadiq", "", null, "Lola");

        for (String name : list) {
            if (name != null && !name.isEmpty() && name.contains("a")) {
                System.out.println(name);
            }
        }
        //----------------------------------
        List<String> list2 = asList("John", "Alan", "Sadiq", "", null, "Lola");

        list2.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty() && name.contains("a"))
                .forEach(System.out::println);

    }

    /**
     * Один из методов Stream-ов, map принимает лямбда-выражение известное как функция (Function),
     * которое преобразовывает Stream одного типа данных в Stream другого типа.
     */
    static void getTheDoubleOfNumb() {
        List<Integer> integers = asList(1, 3, 5, 7);

        for (Integer i : integers) {
            System.out.println(i * 2); //output 2 6 10 14
        }
        //-------------------
        List<Integer> integers2 = asList(1, 3, 5, 7);

        integers2.stream()
                .map(i -> i * 2)
                .forEach(System.out::println);
    }

    static void getCarNumbers() {
        List<Car> cars = asList(
                new Car("90XU878", 2010),
                new Car("10AA500", 2009),
                new Car("90CD266", 2010),
                new Car("99XX885", 2015),
                new Car("90HL990", 2017));

        for (Car car : cars) {
            System.out.println(car.getNumber());
        }
        //-----------
        List<Car> cars2 = asList(
                new Car("90XU878", 2010),
                new Car("10AA500", 2009),
                new Car("90CD266", 2010),
                new Car("99XX885", 2015),
                new Car("90HL990", 2017));

        cars2.stream()
                .map(Car::getNumber) // преобразовываем Stream машин в Stream номеров
                .forEach(System.out::println);
    }

    static void getCarNumbersYearGreaterThan() {
        List<Car> carList = asList(
                new Car("90XU878", 2007),
                new Car("90HL990", 2010),
                new Car(null, 2012),
                new Car("", 2015),
                new Car("10AA200", 2017));

        for (Car car : carList) {
            if (car.getYear() > 2010) {
                String number = car.getNumber();
                if (number != null && !number.isEmpty())
                    System.out.println(number);
            }
        }
//------------------
        List<Car> carList2 = asList(
                new Car("90XU878", 2007),
                new Car("90HL990", 2010),
                new Car(null, 2012),
                new Car("", 2015),
                new Car("10AA200", 2017));

        carList2.stream()
                .filter(c -> c.getYear() > 2010) // выбираем только машины, выпущенные после 2010 года
                .map(Car::getNumber) // преобразовываем Машины в номера, теперь у нас Stream номеров машин
                .filter(s -> s != null && !s.isEmpty()) // выбираем только не пустые номера
                .forEach(System.out::println); // выводим результат
    }

    /**
     * Один из методов Stream-ов, collect принимает лямбда-выражение известное как коллектор (Collector),
     * которое собирает данные в необходимую структуру данных.
     */

    static void getEvenNumbList() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer i : numbers) {
            if (i % 2 == 0) {
                evenNumbers.add(i);
            }
        }
        System.out.println(evenNumbers); //output [2, 4, 6, 8, 10]
        //-------------------
        List<Integer> numbers2 = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers2 = numbers2.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers2); //output [2, 4, 6, 8, 10]
    }

    static void converToUpperCase() {
        List<String> names = asList("John", "Arya", "Sansa");
        Set<String> upperCaseNames = new HashSet<>();

        for (String name : names) {
            upperCaseNames.add(name.toUpperCase());
        }
        System.out.println(upperCaseNames); //output [ARYA, JOHN, SANSA]
        //------------------
        List<String> names2 = asList("John", "Arya", "Sansa");
        Set<String> upperCaseNames2 = names2.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        System.out.println(upperCaseNames2); //output [ARYA, JOHN, SANSA]
    }

    static void putInQ() {
        List<String> names = asList("Jaime", "Daenerys", "", "Tyrion", "");
        Queue<String> queue = new LinkedList<>();
        for (String name : names) {
            if (!name.isEmpty()) {
                queue.add(name);
            }
        }
        System.out.println(queue); //output [Jaime, Daenerys, Tyrion]

        //-------
        List<String> names2 = asList("Jaime", "Daenerys", "", "Tyrion", "");
        Queue<String> queue2 = names2.stream()
                .filter(n -> !n.isEmpty())
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(queue2); //output [Jaime, Daenerys, Tyrion]
    }

    static void groupByHumanSurname() {
        List<Human> humans = asList(
                new Human("X", "Aliyev", 1),
                new Human("Y", "Aliyev", 2),
                new Human("Z", "Aliyev", 1),
                new Human("X", "Alizade", 6),
                new Human("Y", "Alizade", 4),
                new Human("X", "Mamedov", 1),
                new Human("Y", "Mamedov", 3));

        Map<String, List<Human>> map = new HashMap<>();

        for (Human human : humans) {
            String surname = human.getSurname();
            if (!map.containsKey(surname)) { // если фамилии еще нет - создаем новый список
                List<Human> humanList = new ArrayList<>();
                humanList.add(human);
                map.put(surname, humanList);
            } else {                        // если фамилия есть - добавляем представителя семьи =)
                List<Human> humanList = map.get(surname);
                humanList.add(human);
            }
        }
        System.out.println(map);
        //---------------------------
        List<Human> humans1 = asList(
                new Human("X", "Aliyev", 1),
                new Human("Y", "Aliyev", 2),
                new Human("Z", "Aliyev", 1),
                new Human("X", "Alizade", 6),
                new Human("Y", "Alizade", 4),
                new Human("X", "Mamedov", 1),
                new Human("Y", "Mamedov", 3));

        Map<String, List<Human>> map1 = humans1.stream()
                .collect(Collectors.groupingBy(Human::getSurname));
        System.out.println(map1);
    }

    static void groupByWithCount() {
        List<Human> humans = asList(
                new Human("X", "Aliyev", 1),
                new Human("Y", "Aliyev", 2),
                new Human("Z", "Aliyev", 1),
                new Human("X", "Alizade", 6),
                new Human("Y", "Alizade", 4),
                new Human("X", "Mamedov", 1),
                new Human("Y", "Mamedov", 3));

        Map<String, Long> map = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname, Collectors.counting()));

        System.out.println(map);
    }

    static void groupWithAmountOfMoney() {
        List<Human> humans = asList(
                new Human("X", "Aliyev", 1),
                new Human("Y", "Aliyev", 2),
                new Human("Z", "Aliyev", 1),
                new Human("X", "Alizade", 6),
                new Human("Y", "Alizade", 4),
                new Human("X", "Mamedov", 1),
                new Human("Y", "Mamedov", 3));
        Map<String, Integer> map = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname, Collectors.summingInt(Human::getMoney)));

        System.out.println(map);
    }

    static void groupAndConvertToSet() {
        List<Human> humans = asList(
                new Human("X", "Aliyev", 1),
                new Human("Y", "Aliyev", 2),
                new Human("Z", "Aliyev", 1),
                new Human("X", "Alizade", 6),
                new Human("Y", "Alizade", 4),
                new Human("X", "Mamedov", 1),
                new Human("Y", "Mamedov", 3));
        Map<String, Set<String>> map = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname, //группируем по фамилии
                        Collectors.mapping(Human::getName, Collectors.toSet()))); // собираем имена в Set

        System.out.println(map);
    }

    /**
     * Один из методов Stream-ов, flatMap() принимает лямбда-выражение известное как функция (Function),
     * которое "разворачивает" Stream-ы в один
     */
    static void getPetNames() {
        List<Person> persons = asList(
                new Person("Sam", asList("Buddy", "Lucy")),
                new Person("Bob", asList("Frankie", "Rosie")),
                new Person("Marta", asList("Simba", "Tilly")));

        List<String> petNames = new ArrayList<>();
        for (Person person : persons) {
            petNames.addAll(person.getPets());
        }

        System.out.println(petNames); // output [Buddy, Lucy, Frankie, Rosie, Simba, Tilly]

        //---------------------------------------
        List<Person> people = asList(
                new Person("Sam", asList("Buddy", "Lucy")),
                new Person("Bob", asList("Frankie", "Rosie")),
                new Person("Marta", asList("Simba", "Tilly")));

        List<String> pets = people.stream()
                .flatMap(human -> human.getPets().stream())
                .collect(Collectors.toList());

        System.out.println(pets); // output [Buddy, Lucy, Frankie, Rosie, Simba, Tilly]
    }

    static void convertArrayFromMultiToOne() {
//        int[][] arr = {{1, 2}, {3, 4}, {5, 6}}; // массив 3 на 2
//
//        int[] newArr = new int[arr.length * arr[0].length]; // length = 6
//
//        for (int i = 0, index = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                newArr[index++] = arr[i][j];
//            }
//        }
//        System.out.println(Arrays.toString(newArr)); //output [1, 2, 3, 4, 5, 6]
        //***********-----------------
        int[][] arr = {{1, 2}, {3, 4}, {5, 6}};

        int[] newArr = Arrays.stream(arr)
                .flatMapToInt(Arrays::stream) //преобразовываем IntStream<int[]> в IntStream
                .toArray(); // преобразовываем IntStream в int[]

        System.out.println(Arrays.toString(newArr)); //output [1, 2, 3, 4, 5, 6]
    }

    /**
     * Для обычных Stream-ов (последовательных) при нескольких запусках результат будет один и тот же.
     * Для параллельных же - всегда разный
     */
    static void parallelStreamDifference() {
        List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco", "BLOB");

        Optional<String> java = strings.stream()
                .filter(s -> s.contains("Java"))
                .findAny();

        System.out.println(java); //output Optional[Java is the best]

        List<String> strs = Arrays.asList("Java is the best", "Java 8", "Jacoco", "BLOB", "Java 9");

        Optional<String> str = strs.parallelStream()
                .filter(s -> s.contains("Java"))
                .findAny();

        System.out.println(str);
    }

    /**
     * Для поиска данных в Stream-е, необходимо использовать комбинации методов filter() и findAny()
     * или findFirst()
     */

    static void findFirstExample() {
        List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);

        Optional<Integer> first = numbers.stream()
                .filter(number -> number > 10)
                .findFirst();

        System.out.println(first); //output Optional[12]
    }

    /**
     * Для проверки данных по критерию в Stream-е можно использовать следующие методы :
     * anyMatch(), allMatch(), noneMatch()
     */
    static void isEvenNumber() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        boolean match = numbers.stream()
                .anyMatch(number -> number % 2 == 0); // есть ли в Stream-e четное число

        System.out.println(match); //output true
    }

    static void isAllNumbersEven() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        boolean match = numbers.stream()
                .allMatch(number -> number % 2 == 0); // все ли числа в Stream-e четные

        System.out.println(match); //output false
    }

    static void isAllNumbOdds() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        boolean match = numbers.stream()
                .noneMatch(number -> number % 2 == 0); // все ли числа в Stream-e НЕ четные

        System.out.println(match); //output false
    }

    /**
     * Stream-ы могут иметь различные модификаторы состояния
     * : distinct, sorted, parallel, sequential
     * Если мы хотим получить Stream уникальных элеметов, мы можем воспользоваться методом distinct().
     * Использование метода distinct() предполагает что элементы Stream-a реализовывают метод equals()
     */
    static void distinctTest() {
        List<String> strings = Arrays.asList("a", "b", "c", "d", "a", "b");

        strings.stream()
                .distinct()
                .forEach(System.out::println); //output a b c d
    }

    /**
     * Если же класс не реализовывает метод equals(), метод distinct() не будет иметь смысла
     */
    static void noneEqualsClassMethod() {
        List<Human> humans = Arrays.asList(new Human("Jo"),
                new Human("Jo"),
                new Human("Lui"));

        humans.stream()
                .distinct()
                .forEach(System.out::println); //output {name='Jo'} {name='Jo'} {name='Lui'}
    }

    /**
     * Если мы хотим получить сортированный Stream, мы можем использовать метод sorted().
     * Stream имеет два метода sorted(), один без параметров (подразумевает что класс реализует Comparable)
     * второй принимает Comparator.
     */
    static void sortedStreamExample() {
        List<Human> humans = Arrays.asList(new Human("Jo"),
                new Human("Zai"),
                new Human("Lui"));

        humans.stream()
                .sorted()
                .forEach(System.out::println); //java.lang.ClassCastException
    }

    /**
     * Если элементы Stream-a не реализуют Comparable, мы получим ClassCastException при вызове sorted.
     * После реализации интерфейса все работает как нужно
     */
    static void sortedWithComparator() {
        List<Human> humans = Arrays.asList(new Human("Jo"),
                new Human("Zai"),
                new Human("Lui"));

        humans.stream()
                .sorted(Comparator.comparing(Human::getName).reversed()) //сортировка по имени в обратном порядке
                .forEach(System.out::println); //output {name='Zai'} {name='Lui'} {name='Jo'}
    }

    /**
     * Stream API поддерживает параллельное исполнение задач. Чтобы сделать Stream параллельным,
     * нужно лишь вызвать метод parallel() или parallelStream() у коллекции
     */
    static void parallelStreamExample() {
        List<String> people = Arrays.asList("Jo", "Zai", "Lui", "Andy");

        people.parallelStream()
                .forEach(System.out::println); //output Lui Zai Jo Andy
    }
    /**
     * На самом деле вывод будет разный каждый раз, Stream то параллельный, значит обрабатывать данные
     * он будет в несколько потоков.
     */

    /**
     * Sequential stream это самый обычный (последовательный) Stream, метод sequential() был введен для того,
     * чтобы из parallel можно было обратно получить sequential Stream.
     */
    static void parallelToSequential() {
        List<String> people = Arrays.asList("Jo", "Zai", "Lui", "Andy");

        people.parallelStream()
                .sequential()
                .forEach(System.out::println); //output Jo Zai Lui Andy
    }

    /**
     * Метод limit() оставляет только первые n элементов из Stream-a, где n - заданный параметр.
     * Т.е. если у нас есть Stream из 10 элементов и мы вызвали limit(5), то в Stream останутся первые 5 элементов
     * Если в limit() указать значение больше чем размер Stream-a,то Stream останется без изменений
     * Если указать отрицательное значение в limit() то мы получим IllegalArgumentException
     */
    static void limitExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .limit(5)
                .forEach(System.out::println); //output 1 2 3 4 5
    }

    /**
     * Метод skip() пропускает первые n элементов Stream-a, где n - заданный параметр .
     * Т.е. если у нас есть Stream из 10 элементов и мы вызвали skip(5), то в Stream останутся вторые 5 элементов
     * Если в skip() указать значение больше, чем размер Stream-a, то мы получим пустой Stream
     * Если указать отрицательное значение в skip(), то мы получим IllegalArgumentException
     */
    static void skipExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .skip(5)
                .forEach(System.out::println); //output 6 7 8 9 10
    }
}