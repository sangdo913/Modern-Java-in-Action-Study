public interface AppleFormatter {
    String accept(Apple a);
}

class FancyFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}

class AppleSimpleFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        return "An apple(" + apple.getNumber() + ") of " + apple.getWeight() + "g";
    }
}