package springboot.backend.utils;

public class CustomMethods {
    public static String firstLetterUpper(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
