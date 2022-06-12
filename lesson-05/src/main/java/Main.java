import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Main {
    //ограничим глубину рекурсии для самых маленьких хакеров
    private static final int maxStackDepth = 5000;

    public static void main(String[] args) {
        //1. Написать программу по возведению числа в степень с помощью рекурсии:
        petRecursiveExponentiation();
        //2. Написать программу обхода шахматной доски конем:
        playUselessChessGame();
    }

    private static void petRecursiveExponentiation() {
        var base = Utils.inputNumber("Введи основание");
        var power = Utils.inputNumber("Введи степень");
        var poweredBase = pow(base, power);
        System.out.printf("Я все посчитал! %d ^^ %d = %f\n", base, power, poweredBase);
        System.out.println("Как тебе такое, Илон Маск?");
    }

    private static void playUselessChessGame() {
        var game = new ChessGame();
        game.play();
    }


    public static BigDecimal pow(int base, int power) {
        if (power > maxStackDepth)
            throw new IllegalArgumentException("Ты выбрал не ~~тот стул~~ ту степень! Шах и мат, мамкин хакер!");
        if (power == 0) return BigDecimal.valueOf(1);
        if (power == 1 || base == 1) return BigDecimal.valueOf(base);
        var absPower = Math.abs(power);
        var bigIntegerBase = BigInteger.valueOf(base);
        var result = new BigDecimal(recursivePow(bigIntegerBase, absPower));
        if (power < 0) return BigDecimal.valueOf(1).divide(result);
        return result;
    }

    private static BigInteger recursivePow(BigInteger base, int power) {
        if (power == 1 || base.intValue() == 1) return base;
        if (power == 0) return BigInteger.valueOf(1);
        return base.multiply(recursivePow(base, power - 1));
    }
}
