interface ApplePredicate {
    boolean test (Apple apple);
}

// 무거운 사과만 선택
class AppleHeavyWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

//녹색 사과만 선택
class AppleGreenColorPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}

//빨간 사과 + 무게 150 초과
class AppleRedAndHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }
}

interface Predicate<T> {
    boolean test(T t);
}