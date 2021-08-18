import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.GREEN;
enum Color { RED, GREEN }

class Apple {
    int number;
    int weight;
    Color color;
    Color getColor() {
        return color;
    }

    int getNumber() {
        return number;
    }

    int getWeight() {
        return weight;
    }

    Apple(int number, Color color, int weight) {
        this.color = color;
        this.number = number;
        this.weight = weight;
    }

    void print() {
        System.out.println("(" + number + ", " + color + ", " + weight +  ") ");
    }
}

class PrintApple {
    PrintApple(String msg, List<Apple> apples) {
        System.out.println("=======");
        System.out.println(msg);
        for(Apple apple: apples) {
            apple.print();
        }
    }
}


public class main {
    public static void main(String[] args) {
        ArrayList<Apple> list = new ArrayList();
        int cnt = 0;
        list.add(new Apple(cnt++, Color.RED, 150));
        list.add(new Apple(cnt++, Color.GREEN, 180));
        list.add(new Apple(cnt++, Color.RED,78));
        list.add(new Apple(cnt++, Color.RED, 234));
        list.add(new Apple(cnt++, Color.GREEN, 1235));

        List<Apple> res = filterGreenApples(list);

        System.out.println("print apple list");
        for (Apple apple: res) {
            System.out.println("(" + apple.getNumber() + ", " + apple.getColor() +  ") ");
        }

        System.out.println("===\n");
        System.out.println("print filtered apples list by Color");
        res =  filterApplesByColor(list, Color.RED);
        for (Apple apple: res) {
            System.out.println("(" + apple.getNumber() + ", " + apple.getColor() +  ") ");
        }

        System.out.println("===");
        System.out.println("print filtered apples list by Weight");
        res =  filterApplesByWeight(list, 150);
        for (Apple apple: res) {
            System.out.println("(" + apple.getNumber() + ", " + apple.getColor() + ", " + apple.getWeight() +  ") ");
        }
        //==========위에까지가 1
        var c = new ModernJavaInAction2_2(list);
        c.func2_4_1(list);
        c.func2_4_2();

        //===========Quiz 2-1
        new QUIZ2(list);

        System.out.println("Chapter3 test");
        var chapter3 = new Chapter3();
        chapter3.all_execute();
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple: inventory) {
            if (Color.GREEN.equals(apple.getColor())) { // 녹색 사과만 선택
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple: inventory) {
            if (apple.getColor().equals(color)) { // 같은 사과 색 선택
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple: inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple: inventory) {
            if ((flag && apple.getColor().equals(color)) || // 색이나 무개를 선택하는 방법이 마음에 들지 않는다.
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }
}

class ModernJavaInAction2_2 {
    ModernJavaInAction2_2(List<Apple> apples){
        func(apples);
    }

    public void func(List<Apple> apples) {
        List<Apple> greenApples = filterApples(apples, new AppleGreenColorPredicate());
        new PrintApple("filter apple by 전략패턴(Color Green)", greenApples);

        List<Apple> heavyApples = filterApples(apples, new AppleHeavyWeightPredicate());
        new PrintApple("filter apple by 전략패턴(Weight 150 초과)", heavyApples);

        List<Apple> redAndHeavyApples = filterApples(apples, new AppleRedAndHeavyPredicate());
        new PrintApple("filter apple by 전략패턴(color red && weight 150 초과)", redAndHeavyApples);

        List<Apple> redApples = filterApples(apples, new ApplePredicate() {
            public boolean test(Apple apple){
                return Color.RED.equals(apple.getColor());
            }
        });
        new PrintApple("2.3.2 filter apple by anonymous class (color red)", redApples);

        redApples = filterApples(apples, (Apple apple) -> apple.getColor().equals(Color.RED));
        new PrintApple("2.3.3 filter apple by lambda (color red)", redApples);

        ArrayList<Integer> arr = new ArrayList();
        arr.add(2);
        arr.add(1235);
        arr.add(5213);
        arr.add(223);
        arr.add(3234);
        arr.add(1230);
        func2_3_4(arr, apples);
    }

    void func2_3_4(List<Integer> list, List<Apple> apples) {
        List<Integer> res = filter(list, (Integer v) -> v%2 == 0);
        System.out.println("2_3_4 리스트 형식으로 추상화(Integer ArrayList)");
        for(Integer v: res) {
            System.out.print(v + " ");
        }
        System.out.println();
        List<Apple> greenApples = filter(apples, (Apple apple) -> apple.getColor().equals(Color.GREEN));
        new PrintApple("2_3_4 리스트 형식으로 추상화(Apple ArrayList)", greenApples);
        System.out.println();
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e: list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList();

        for(Apple apple: inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    void func2_4_1(List<Apple> apples) {
        apples.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        new PrintApple("2_4_1 Comparator로 정렬하기", apples);
    }

    void func2_4_2() {
        Thread t = new Thread(new Runnable() {
            public void run(){
                System.out.println("Hello Java(익명 클래스)");
            }
        });

        Thread t2 = new Thread(()->System.out.println("Hello Java(람다)"));
        t.run();
        t2.run();
    }
}

class QUIZ2 {
    QUIZ2(List<Apple> apples) {
        System.out.println("\n====Quiz 2=====");
        quiz2_1(apples);
    }
    void quiz2_1(List<Apple> apples) {
        System.out.println("===fancy formatter");
        prettyPrintApple(apples, new FancyFormatter());

        System.out.println("===simple formatter");
        prettyPrintApple(apples, new AppleSimpleFormatter());
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter)  {
        for(Apple apple: inventory) {
            System.out.println(formatter.accept(apple));
        }
    }
}