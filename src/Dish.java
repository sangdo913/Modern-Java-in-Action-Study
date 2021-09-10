public class Dish {
    enum TYPE{
        OTHER, FISH, MEAT
    };
    String name;
    boolean vegetarian;
    int calorie;
    TYPE type;

    int getCalorie() {
        return calorie;
    }

    String getName(){
        return name;
    }

    TYPE getType() {
        return type;
    }

    Dish(boolean vege, String name) {
        this.name = name;
        vegetarian = vege;
    }

    Dish(String name, boolean b, int c, TYPE t) {
        this.name = name;
        vegetarian = b;
        calorie = c;
        type =t;
    }
    void printName(){
        System.out.println(name);
    }
    boolean isVegetarian() {
        return vegetarian;
    }
}
