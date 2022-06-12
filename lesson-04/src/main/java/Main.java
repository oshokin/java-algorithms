import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //0. Реализовать класс двусвязного списка, чтобы реализовать на нем итератор:
        petDoublyLinkedList(); //потискаем мой вновь изобретенный и никому не нужный связный список
        //1. Реализовать класс итератора на двусвязном списке:
        petIterator();
        //2. Написать очередь, использующую двусвязный список:
        petPimpedQueue();
    }

    private static void petDoublyLinkedList() {
        removeDoublyLinkedListItemsOneByOne();
        removeDoublyLinkedListItemsRandomly();
        insertItemsIntoDoublyLinkedListRandomly();
    }

    private static void petIterator() {
        System.out.println("Четвертый эксперимент: ");
        var dummyArray = getRandomArray(10, 20, 100, 200);
        var list = new DoublyLinkedList<>(dummyArray);
        System.out.printf("Сначала появился список: %s\n", list);
        var idx = getRandomNumber(0, list.getSize() - 1);
        System.out.printf("Потом мы решили поитерироваться по нему с индекса %d\n", idx);
        var iter = list.getIterator(idx);
        while (iter.hasNext()) {
            System.out.printf("%d\n", iter.next());
        }
    }

    private static void petPimpedQueue() {
        System.out.println("Пятый эксперимент: ");
        var dummyArray = getRandomArray(10, 20, 100, 200);
        var queue = new PimpedQueue<>(dummyArray);
        System.out.printf("Сначала была очередь: %s\nПотом мы решили ее очистить\n", queue);
        while (!queue.isEmpty()) {
            System.out.printf("%d\n", queue.popFirst());
        }
    }

    private static void removeDoublyLinkedListItemsOneByOne() {
        System.out.println("Первый эксперимент: ");
        var dummyArray = getRandomArray(10, 20, 100, 200);
        var list = new DoublyLinkedList<>(dummyArray);
        System.out.printf("Сначала появился список: %s\n", list);
        System.out.printf("И было у него %d элементов\n", list.getSize());
        var isEvenTurn = false;
        while (!list.isEmpty()) {
            System.out.println("Потом первый элемент решил отделиться и сделать свой список с пасьянсом и куртизанками");
            if (!isEvenTurn) list.removeFirst();
            else list.removeLast();
            isEvenTurn = !isEvenTurn;
            System.out.printf("И осталось в списке %d элементов\n", list.getSize());
            System.out.println(list);
        }
    }

    private static void removeDoublyLinkedListItemsRandomly() {
        System.out.println("Второй эксперимент: ");
        var dummyArray = getRandomArray(10, 20, 100, 200);
        var list = new DoublyLinkedList<>(dummyArray);
        System.out.printf("Сначала появился список: %s\n", list);
        System.out.printf("И было у него %d элементов\n", list.getSize());
        while (!list.isEmpty()) {
            var curSize = list.getSize();
            var idx = 0;
            if (curSize > 1) idx = getRandomNumber(0, curSize - 1);
            System.out.printf("Потом элемент %d поверил в себя и решил уйти, осталось %d элементов\n", idx, curSize);
            list.remove(idx);
            System.out.printf("И осталось в списке %d элементов\n", list.getSize());
            System.out.println(list);
        }
    }

    private static void insertItemsIntoDoublyLinkedListRandomly() {
        System.out.println("Третий эксперимент: ");
        var dummyArray = getRandomArray(10, 20, 100, 200);
        var list = new DoublyLinkedList<>(dummyArray);
        System.out.printf("Сначала появился список: %s\n", list);
        System.out.printf("И было у него %d элементов\n", list.getSize());
        System.out.println("А потом мы стали добавлять всякую дичь в разные места списка");
        var iterationsCount = getRandomNumber(10, 20);
        for (int i = 0; i < iterationsCount; i++) {
            var idx = getRandomNumber(0, list.getSize() - 1);
            var number = getRandomNumber(100, 200);
            System.out.printf("Например, %d в индекс %d\n", number, idx);
            list.insert(idx, number);
            System.out.printf("И стало в списке %d элементов\n", list.getSize());
            System.out.println(list);
        }
    }

    private static Integer[] getRandomArray(int startIndex, int endIndex, int start, int end) {
        var randomSize = getRandomNumber(startIndex, endIndex);
        var baseArr = new Integer[randomSize];
        for (int i = 0; i < randomSize; i++) {
            baseArr[i] = getRandomNumber(start, end);
        }

        return baseArr;
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
