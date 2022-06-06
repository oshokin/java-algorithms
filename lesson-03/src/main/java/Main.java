import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Джун: Да я свою программу могу на разных языках написать!");
        System.out.println("Сеньор: Что за программа?");
        System.out.println("Джун: Hello, world!");
        System.out.println("Сцена драки");

        //1. Создать класс для реализации дека.
        petDeque(); //потискать очередь

        //2. Создать класс для реализации приоритетной очереди (выбрать только один из вариантов)
        petPriorityQueue(); //потискать приоритетную очередь

        //3. Создать решение для задачи перемещения
        hackBrackets();
    }

    private static void petDeque() {
        //Java, когда же ты добавишь оператор краткого присваивания как в Golang, ты же взрослая и сама можешь вывести тип!
        var deque = new Deque(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.printf("Сначала был дек: %s\nДавайте же его потыкаем палочкой\n", deque);
        for (int i = 0; i < 5; i++) {
            deque.popLast();
            System.out.printf("Потом убрали кусочек справа: %s\n", deque);
            deque.popFirst();
            System.out.printf("Потом убрали кусочек слева: %s\n", deque);
        }

        System.out.println("А если попробовать впихнуть невпихуемое? Нужен эксперимент :)");
        for (int i = 1; i <= 10; i++) {
            deque.pushFirst(-i);
            deque.pushLast(10 + i);
        }
        System.out.printf("Вскрываем сейф: %s\n", deque);
        System.out.println("Kotlin-разработчик: Ручка отдает 404!");
        System.out.println("Golang-разработчик: Скинь пжл curl");
        System.out.println("Kotlin-разработчик: Я же тебе уже скидывал в личке!");
        System.out.println("Golang-разработчик: Я же тебя уже просил научиться пользоваться curl'ом. Где-то грустит 1 ProxyMan :pekaface:");
    }

    private static void petPriorityQueue() {
        var pq = new PriorityQueue(100);
        new Random().ints(100, 100, 500).forEach(pq::pushLast);
        while (!pq.isEmpty()) {
            var item = pq.popFirst();
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private static void hackBrackets() {
        var isCorrectLine = false;
        var line = "";
        while (!isCorrectLine) {
            try (Scanner in = new Scanner(System.in)) {
                System.out.print("Enter the line: ");
                line = in.nextLine();
                isCorrectLine = !line.isBlank();
            } catch (RuntimeException e) {
                System.out.println("Ты что, псина! Не ломай кампухтер! МАМКА ЗАРУГАЕТ!");
            }
        }

        var areBracketsFine = checkIfBracketsAreCorrect(line);
        if (areBracketsFine) System.out.println("Моё почтение! ERES UN PUTO GENIO!");
        else System.out.println("Иди под парту, учись! Чтоб через 5 минут умный был! НАЧАЛЬНИК! ЭТОТ ДЖУН ОШИБСЯ!");
    }

    private static boolean checkIfBracketsAreCorrect(String line) {
        var counter = new Stack(line.length());
        var cp = line.codePoints().toArray();
        for (int i : cp) {
            var c = (char) i;
            //Java, нормальный свитч тебе прикрутили в 14-й версии. Почему???!!!
            //Кровавый Enterprise не заслуживает нормального свитча?!
            switch (c) {
                case '(':
                case '[':
                case '{':
                    counter.push(i);
                    break;
                case ')':
                case ']':
                case '}':
                    var bracket = (char) counter.pop();
                    if (c != getClosingBracket(bracket)) return false;
                    break;
            }
        }

        return counter.isEmpty();
    }

    private static char getClosingBracket(char bracket) {
        switch (bracket) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                return 0;
        }
    }
}