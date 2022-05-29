import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Equalizer {
    private final Scanner sc;

    Equalizer() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println("Кто писал на Java, в цирке не смеется :)");
        var equalizer = new Equalizer();
        equalizer.solveFirstProblem();
        System.out.println("Идем дальше! Я художник, я так вижу, но думаю, что \n" +
                "надо перемножать число само на себя до тех пор, пока показатель степени не уйдет в закат, \n" +
                "если показатель степени четный");
        equalizer.solveSecondProblem();
        equalizer.solveThirdProblem();
        equalizer.stop();
    }

    private void solveFirstProblem() {
        var base = inputNumber("Введи основание");
        var power = inputNumber("Введи степень");
        var poweredBase = simplePow(base, power);
        System.out.printf("Я все посчитал! %d ^^ %d = %f\n", base, power, poweredBase);
        System.out.println("Как тебе такое, Илон Маск?");
    }

    private void solveSecondProblem() {
        var base = inputNumber("Введи основание");
        int power;
        do {
            power = inputNumber("Введи четную степень");
            if (power % 2 != 0) {
                System.out.println("Пёс, ты с кем играешь?!");
            } else break;
        } while (true);
        var poweredBase = evenPow(base, power);
        System.out.printf("Я все посчитал! %d ^^ %d = %f\n", base, power, poweredBase);
        System.out.println("Как там на батуте, Илон?");
    }

    private void solveThirdProblem() {
        int start = 0;
        int end = 100;
        int result = 0;
        for (int i = start; i <= end; i++) {
            result += i;
        }
        System.out.printf("Я все посчитал! Сумма числового ряда от %d до %d = %d\n", start, end, result);
        System.out.println("Рогозин, что с лицом?");
    }

    private double simplePow(int base, int power) {
        switch (power) {
            case 0:
                return 1;
            case 1:
                return base;
        }
        if (base == 0) {
            return base;
        }

        var result = BigInteger.valueOf(base);
        var bigDecimalBase = BigInteger.valueOf(base);
        var absPower = Math.abs(power);
        for (int i = 1; i < absPower; i++) {
            result = result.multiply(bigDecimalBase);
        }
        if (power < 0) return BigDecimal.valueOf(1).divide(new BigDecimal(result), RoundingMode.UP).doubleValue();

        return result.doubleValue();
    }

    private double evenPow(int base, int power) {
        if (power == 0) {
            return 1;
        }
        if (base == 0) {
            return base;
        }
        var result = BigInteger.valueOf(base);
        var bigDecimalBase = BigInteger.valueOf(base);
        var stepsLeft = Math.abs(power);
        var isUnevenPowerFound = false;
        do {
            if (!isUnevenPowerFound && stepsLeft % 2 == 0) {
                result = result.multiply(result);
                bigDecimalBase = result;
                stepsLeft >>= 1;
            } else {
                isUnevenPowerFound = true;
                result = result.multiply(bigDecimalBase);
                stepsLeft--;
            }
        } while (stepsLeft != 1);
        if (power < 0) return BigDecimal.valueOf(1).divide(new BigDecimal(result), RoundingMode.UP).doubleValue();

        return result.doubleValue();
    }

    private void stop() {
        sc.close();
    }

    private int inputNumber(String inputPrompt) {
        return inputNumber(inputPrompt, false);
    }

    private int inputNumber(String inputPrompt, boolean shouldBePositive) {
        int number = 0;
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
}
