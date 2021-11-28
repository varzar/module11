package task2;

import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(31);
        List <String> result =  fizzBuzz.startThread();

        try {
            fizzBuzz.getA().join();
            fizzBuzz.getB().join();
            fizzBuzz.getC().join();
            fizzBuzz.getD().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String s = result.stream()
                .collect(Collectors.joining(", ", "", "."));
        System.out.println(s);
    }
}
