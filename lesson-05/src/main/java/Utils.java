import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Utils {
    public static int inputNumber(String inputPrompt) {
        return inputNumber(inputPrompt, false);
    }

    public static int inputNumber(String inputPrompt, boolean shouldBePositive) {
        int number = 0;
        var sc = new Scanner(System.in);
        while (true) {
            System.out.print(inputPrompt + ": ");
            if (sc.hasNextInt()) {
                try {
                    number = sc.nextInt();
                } catch (NoSuchElementException e) {
                    System.out.println("Ахаха! Кто у мамки хакер? Введи целое число!");
                    sc.next();
                } catch (IllegalStateException e) {
                    System.out.println("Ты что творишь со сканером, пёс!");
                    break;
                }
                if (shouldBePositive && number < 0) {
                    System.out.println("А теперь еще разочек, но теперь неотрицательное");
                    System.out.print(inputPrompt + ": ");
                    sc.next();
                } else break;
            } else {
                System.out.println("Ахаха! Кто у мамки хакер? Введи целое число, пёс!");
                //Java, зачем делать такой сложный консольный вывод????!!!!!
                //простой ввод числа ты превращаешь в адъ!
                sc.next();
            }
        }

        return number;
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
