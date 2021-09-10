import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.binarySearch;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Chapter5 {
    public void go() {
        List<Dish> specialMenu = asList(
                new Dish("seasonal fruit", true, 120, Dish.TYPE.OTHER),
                new Dish("prawns", false, 300, Dish.TYPE.FISH),
                new Dish("rice", true, 350, Dish.TYPE.OTHER),
                new Dish("chicken", false, 400, Dish.TYPE.MEAT),
                new Dish("french fries", true, 530, Dish.TYPE.OTHER)
        );

        Chapter511 c511 = new Chapter511();
        System.out.println("===example 512====");
        c511.e512();
        c511.e521();
        c511.quiz(specialMenu);

        c511.e531(specialMenu);
        c511.e532();
        c511.quiz2();
        c511.e541(specialMenu);
        c511.e543(specialMenu);
        c511.e544();
        c511.e551();
        c511.quiz3(specialMenu);

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        c511.e562(transactions);
        c511.e571(specialMenu);
        c511.e581();
        c511.quiz4();
    }

    class Chapter511 {
        Chapter511() {
            ArrayList<Dish> menu = new ArrayList();
            menu.add(new Dish(false, "Beef"));
            menu.add(new Dish(true, "salard"));
            menu.add(new Dish(false, "Chicken"));
            List<Dish> vegetarianMenu = menu.stream()
                    .filter(Dish::isVegetarian)
                    .collect(toList());
            vegetarianMenu.forEach(Dish::printName);
        }

        void e512() {
            List<Integer> numbers = asList(1, 2, 1, 3, 3, 2, 4);
            numbers.stream()
                    .filter(i -> {
                        System.out.println("filter " + i);
                        return i % 2 == 0;
                    })
                    .distinct()
                    .map(i -> {
                        System.out.println("test " + i);
                        return i + 1;
                    })
                    .forEach(System.out::println);
        }

        void e521() {
            List<Dish> specialMenu = asList(
                    new Dish("seasonal fruit", true, 120, Dish.TYPE.OTHER),
                    new Dish("prawns", false, 300, Dish.TYPE.FISH),
                    new Dish("rice", true, 350, Dish.TYPE.OTHER),
                    new Dish("chicken", false, 400, Dish.TYPE.MEAT),
                    new Dish("french fries", true, 530, Dish.TYPE.OTHER)
            );

            System.out.println("example 521");
            List<Dish> filterMenu = specialMenu.stream()
                    .filter(dish -> {
                        System.out.println("filter");
                        return dish.getCalorie() < 320;
                    })
                    .collect(toList());
            filterMenu.forEach(Dish::printName);


            System.out.println("take while");
            List<Dish> filterMenu2 = specialMenu.stream()
                    .takeWhile(dish -> {
                        System.out.println("takewhile");
                        return dish.getCalorie() < 320;
                    })
                    .collect(toList());

            System.out.println("test drop while");
            List<Dish> filterMenu3 = specialMenu.stream()
                    .dropWhile(dish -> {
                        System.out.println("drop while");
                        return dish.getCalorie() < 320;
                    })
                    .collect(toList());
            filterMenu3.forEach(Dish::printName);


            System.out.println("Limit");
            List<Dish> filterMenu4 = specialMenu.stream()
                    .filter(dish -> dish.getCalorie() > 300)
                    .limit(2)
                    .collect(toList());
            filterMenu4.forEach(Dish::printName);

            System.out.println("==Skip");
            filterMenu4 = specialMenu.stream()
                    .filter(dish -> dish.getCalorie() > 300)
                    .skip(2)
                    .collect(toList());
            filterMenu4.forEach(Dish::printName);
        }

        void quiz(List<Dish> menu) {
            System.out.println("==quiz meat");
            List<Dish> dishes = menu.stream()
                    .filter(d -> d.getType() == Dish.TYPE.MEAT)
                    .limit(2)
                    .collect(toList());
            dishes.forEach(Dish::printName);
        }

        void e531(List<Dish> menu) {
            List<String> dishNames = menu.stream()
                    .map(Dish::getName)
                    .collect(toList());
            dishNames.forEach(System.out::println);

            List<Integer> dishNameLengths = menu.stream()
                    .map(Dish::getName)
                    .map(String::length)
                    .collect(toList());
            System.out.println("haha");
            dishNameLengths.forEach(System.out::println);

        }

        void e532() {
            List<String> words = asList("Hello", "Hello");
            List<String[]> a = words.stream()
                    .map(word -> word.split(""))
                    .distinct()
                    .collect(toList());
            a.forEach(i -> {
                for (int j = 0; j < i.length; j++) {
                    System.out.println(i[j]);
                }
            });


            System.out.println("==array string to stream");
            String[] arrayOfWords = {"GoodBye", "World"};
            Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
            streamOfWords.forEach(System.out::println);

            System.out.println("unique character");
            List<String> uniqueCharacters =
                    words.stream()
                            .map(word -> word.split(""))
                            .flatMap(Arrays::stream)
                            .distinct()
                            .collect(toList());
            uniqueCharacters.forEach(System.out::println);
        }

        void quiz2() {
            List<Integer> nums = asList(1, 2, 3, 4, 5);
            nums.stream()
                    .map(i -> i * i)
                    .collect(toList());

            System.out.println("==1");
            nums.forEach(System.out::println);

            List<Integer> nums1 = asList(1, 2, 3);
            List<Integer> nums2 = asList(3, 4);

            System.out.println("==2");
            List<Integer[]> a = nums1.stream()
                    .flatMap(i -> nums2.stream().map(j -> new Integer[]{i, j}))
                    .collect(toList());
            a.forEach(v -> System.out.println(v[0].toString() + v[1].toString()));

            System.out.println("==3");
            a = nums1.stream()
                    .flatMap(i -> nums2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new Integer[]{i, j}))
                    .collect(toList());
            a.forEach(v -> System.out.println(v[0].toString() + v[1].toString()));
            a.forEach(v -> System.out.println(v[0].toString() + " " + v[1].toString()));

        }

        void e541(List<Dish> menu) {
            System.out.println("==any match test");
            if (menu.stream().anyMatch(Dish::isVegetarian)) {
                System.out.println("menu is somewhat vegetarian friendly!");
            }

            System.out.println("==all match test");
            if (menu.stream().allMatch(dish -> dish.getCalorie() < 1000)) {
                System.out.println("all menu is healthy food");
            }

            System.out.println("==none match test");
            if (menu.stream().noneMatch(dish -> dish.getCalorie() >= 500)) {
                System.out.println("all menu is healthy food");
            }
        }

        void e543(List<Dish> menu) {
            System.out.println("chapter 5 4 3");
            menu.stream()
                    .filter(Dish::isVegetarian)
                    .map(Dish::getName)
                    .findAny()
                    .ifPresent(System.out::println);
        }

        void e544() {
            System.out.println("==chapter 5 4 4");
            List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
            someNumbers.stream()
                    .map(n -> n * n)
                    .filter(n -> n % 3 == 0)
                    .findFirst()
                    .ifPresent(i -> System.out.println(i));
        }

        void e551() {
            System.out.println("==chapter 5 5 1");
            int sum = 0;
            List<Integer> numbers = asList(1, 2, 3, 4, 5);
            for (int x : numbers) {
                sum += x;
            }

            int sum2 = numbers.stream().reduce(0, (i, j) -> i + j);
            System.out.println(sum);
            System.out.println(sum2);

            int product = numbers.stream().reduce(1, (i, j) -> i * j);
            System.out.println(product);

            int sum3 = numbers.stream().reduce(0, Integer::sum);
            System.out.println(sum3);

            System.out.println("no init value ");
            numbers.stream().reduce(Integer::sum).ifPresent(System.out::println);

            Optional<Integer> max = numbers.stream().reduce(Integer::max);
            Optional<Integer> min = numbers.stream().reduce(Integer::min);
            System.out.println("min and max value");
            System.out.println(max.toString() + " " + min.toString());
        }

        void quiz3(List<Dish> menu) {
            System.out.println("quiz 3");
            Integer res = menu.stream().map(i -> 1).reduce(Integer::sum).get();
            System.out.println(res);
        }

        void e562(List<Transaction> transactions) {
            System.out.println("==chapter 5 6 2");
            System.out.println("==1");
            transactions.stream().filter(t -> t.getYear() == 2011).map(t -> t.getValue()).sorted().collect(toList());
            System.out.println("==2");
            transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
            System.out.println("==3");
            transactions.stream()
                    .map(Transaction::getTrader)
                    .filter(trader -> trader.getCity().equals("Cambridge"))
                    .distinct()
                    .sorted(comparing(Trader::getName))
                    .collect(toList());
            System.out.println("==4");
            Optional<Transaction> res = transactions.stream().min(comparing(Transaction::getValue));
            System.out.println(res.get().getValue());
        }

        void e571(List<Dish> menu) {
            int calories = menu.stream()
                    .mapToInt(Dish::getCalorie)
                    .sum();
            System.out.println("restore boxed");
            IntStream intStream = menu.stream().mapToInt(Dish::getCalorie);
            Stream<Integer> stream = intStream.boxed();

            System.out.println("use optional int");
            OptionalInt maxCalories = menu.stream()
                    .mapToInt(Dish::getCalorie)
                    .max();

            int max = maxCalories.orElse(1);

            System.out.println("range and rangeClosed compare");
            IntStream.range(1,10).forEach(System.out::println);
            IntStream.rangeClosed(1,10).forEach(System.out::println);

            Stream<double[]> pythagoreanTriples =
                    IntStream.rangeClosed(1,100).boxed()
                            .flatMap(a -> IntStream.rangeClosed(a, 100)
                                    .mapToObj(b-> new double[]{a,b,Math.sqrt(a*a + b*b)})
                                    .filter(b -> b[2] %1 == 0));

            pythagoreanTriples =
                    IntStream.rangeClosed(1,100).boxed()
                            .flatMap(a -> IntStream.rangeClosed(a, 100).boxed()
                                    .map(b-> new double[]{a,b,Math.sqrt(a*a + b*b)})
                                    .filter(b -> b[2] %1 == 0));
            pythagoreanTriples.forEach(v-> System.out.println(v[0] + " "  + v[1] + " " + v[2]));
        }

        void e581() {
            System.out.println("==chapter 5.8.1 test");
            Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action");
            stream.map(String::toUpperCase).forEach(System.out::println);
            //Empty stream
            Stream<String> emptyStream = Stream.empty();

            System.out.println("==chapter 5.8.2 test");
            String homeValue = System.getProperty("home");
            Stream<String> homeValueStream = homeValue == null? Stream.empty() : Stream.of(homeValue);

            System.out.println("==null stream check");
            homeValueStream = Stream.ofNullable(System.getProperty("home"));
            homeValueStream.forEach(System.out::println);

            System.out.println(System.getProperty("user.name"));

            System.out.println("==nullable stream and flatmap");
            homeValueStream = Stream.of("config","home", "user.name");
            homeValueStream.flatMap(key -> Stream.ofNullable(System.getProperty(key))).forEach(System.out::println);

            System.out.println("==int stream");
            int[] numbers = {2,3,5,7,11,13};
            IntStream intStream = Arrays.stream(numbers);
            Stream<Integer> integerStream = Arrays.stream(numbers).boxed();
            Stream<String> stringStream = Arrays.stream(new String[]{"a","b"});

            System.out.println("== file stream");
            long uniqueWords = 0;
            try(Stream<String> lines = Files.lines(Paths.get("C:\\Users\\SD\\Desktop\\Git\\modern_in_java\\Modern-Java-in-Action-Study\\src\\data.txt"), Charset.defaultCharset())) {
                uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                        .distinct()
                        .count();
            } catch(IOException e) {
                System.out.println("Exception occured");
            }
            System.out.println(uniqueWords);

            System.out.println("iterate를 이용한 생성");
            Stream.iterate(0, n->n+2)
                    .limit(10)
                    .forEach(System.out::println);

            System.out.println("iterate predicate");
            IntStream.iterate(0,n->n<100, n->n+4)
                    .forEach(System.out::println);

            //무한
//            IntStream.iterate(0, n->n+4)
//                    .filter(n -> n < 100)
//                    .forEach(System.out::println);
            System.out.println("iterate take while");
            IntStream.iterate(0, n->n+4)
                    .takeWhile(n -> n < 100)
                    .forEach(System.out::println);
            System.out.println("==generate test");
            Stream.generate(Math::random)
                    .limit(5)
                    .forEach(System.out::println);
            Stream<Integer> twos = Stream.generate(()->1);

            IntStream.generate(new IntSupplier() {
                private int previous = 0;
                private int current = 1;
                @Override
                public int getAsInt() {
                    int oldPrevious = previous;
                    previous = current;
                    current += oldPrevious;
                    return oldPrevious;
                }
            })
            .limit(10)
            .forEach(System.out::println);
        }
        void quiz4() {
            System.out.println("피보나치 만들기");
            Stream.iterate(new int[]{0,1}, v -> new int[]{v[1], v[0] + v[1]})
                    .limit(20)
                    .map(v->v[0])
                    .forEach(System.out::println);
        }
    }
}
