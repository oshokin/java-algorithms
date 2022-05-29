import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //1. boolean deleteAll(int value);
        solveFirstProblem();

        //2. boolean deleteAll()
        solveSecondProblem();

        //3. void insert(int idx, int value); // shift the tail
        solveThirdProblem();

        //4. 0. подсчитать количество действий для каждой сортировки и сравнить со сложностью О-большое
        measureEfficiencyOfSorts();

        //4. улучшить пузырьковую сортировку
        solveFourthProblem();

        //5.** реализовать сортировку подсчётом
        solveFifthProblem();
    }

    private static void solveFirstProblem() {
        System.out.println("Реализация функции удаления всех значений из массива:");

        var baseArr = new int[]{1, 2, 3, 4, 88, 22, 4, 11, 23, 4, 10, 30, 50};
        showArrayBeforeAfterDeletion("Начало списка", baseArr, 1);
        showArrayBeforeAfterDeletion("Середина списка", baseArr, 4);
        showArrayBeforeAfterDeletion("Конец списка", baseArr, 50);
    }

    private static void solveSecondProblem() {
        System.out.println("Реализация функции очистки массива:");
        System.out.println("<---------------------BEGIN--------------------->");
        var arr = new MyArray(getRandomArray());
        System.out.println("Массив до очистки (легкий способ)");
        arr.display();
        System.out.println("Массив после очистки (легкий способ)");
        arr.clear(false);
        arr.display();
        System.out.println("<--------------------THE END-------------------->");

        System.out.println("<---------------------BEGIN--------------------->");
        arr = new MyArray(getRandomArray());
        System.out.println("Массив до очистки (забивка нулями)");
        arr.display();
        System.out.println("Массив после очистки (забивка нулями)");
        arr.clear(true);
        arr.display();
        System.out.println("<--------------------THE END-------------------->");
    }

    private static void solveThirdProblem() {
        System.out.println("Реализация функции для вставки значения в определенный индекс массива:");

        var baseArr = getRandomArray();
        showArrayBeforeAfterInsertion("Начало списка", baseArr, getRandomNumber(1, 1000), 0);
        showArrayBeforeAfterInsertion("Середина списка", baseArr, getRandomNumber(1, 1000), baseArr.length >> 1);
        showArrayBeforeAfterInsertion("Конец списка", baseArr, getRandomNumber(1, 1000), baseArr.length);
    }

    private static void solveFourthProblem() {
        System.out.println("Улучшение пузырьковой сортировки:");
        measureEfficiencyOfBubbleSorts(10, 20, 1, 1000, true);
        measureEfficiencyOfBubbleSorts(300, 1000, 1, 10000, true);
        measureEfficiencyOfBubbleSorts(6000, 10000, 1, 1000000, true);
    }

    private static void solveFifthProblem() {
        System.out.println("Различные реализации сортировки подсчетом:");
        measureEfficiencyOfCountingSort(10, 20, 1, 1000, true);
        measureEfficiencyOfCountingSort(300, 1000, 1, 10000, true);
        measureEfficiencyOfCountingSort(6000, 10000, 1, 1000000, true);
    }

    private static void measureEfficiencyOfSorts() {
        System.out.println("Подсчет количества шагов для каждой сортировки и сравнить со сложностью О-большое:");
        measureEfficiencyOfSorts(10, 100, 1, 100, true);
        measureEfficiencyOfSorts(100, 1000, 1, 10000, false);
        measureEfficiencyOfSorts(1000, 10000, 1, 1000000, false);
    }

    private static void measureEfficiencyOfSorts(int startIndex, int endIndex, int start, int end, boolean isArrayOutputRequired) {
        var baseArr = getRandomArray(startIndex, endIndex, start, end);
        int[] copy = new int[baseArr.length];
        System.arraycopy(baseArr, 0, copy, 0, baseArr.length);

        System.out.println("<---------------------BEGIN--------------------->");
        System.out.printf("Измерение сортировок с размером массива от %d элементов:\n", baseArr.length);
        if (isArrayOutputRequired) System.out.println(Arrays.toString(baseArr));

        var arr = new MyArray(copy);
        System.out.printf("Обычная пузырьковая сортировка, количество шагов = %d\n", arr.bubbleSort());
        if (isArrayOutputRequired) arr.display();

        arr = new MyArray(copy);
        System.out.printf("Оптимизированная пузырьковая сортировка, количество шагов = %d\n", arr.optimizedBubbleSort());
        if (isArrayOutputRequired) arr.display();

        arr = new MyArray(copy);
        System.out.printf("Сортировка выбором, количество шагов = %d\n", arr.selectionSort());
        if (isArrayOutputRequired) arr.display();

        arr = new MyArray(copy);
        System.out.printf("Сортировка вставками, количество шагов = %d\n", arr.insertionSort());
        if (isArrayOutputRequired) arr.display();
    }

    private static void measureEfficiencyOfBubbleSorts(int startIndex, int endIndex, int start, int end, boolean isArrayOutputRequired) {
        var baseArr = getRandomArray(startIndex, endIndex, start, end);
        int[] copy = new int[baseArr.length];
        System.arraycopy(baseArr, 0, copy, 0, baseArr.length);

        System.out.println("<---------------------BEGIN--------------------->");
        System.out.printf("Измерение сортировок с размером массива от %d элементов:\n", baseArr.length);
        if (isArrayOutputRequired) System.out.println(Arrays.toString(baseArr));

        var arr = new MyArray(copy);
        System.out.printf("Обычная пузырьковая сортировка, количество шагов = %d\n", arr.bubbleSort());
        if (isArrayOutputRequired) arr.display();

        arr = new MyArray(copy);
        System.out.printf("Оптимизированная пузырьковая сортировка, количество шагов = %d\n", arr.optimizedBubbleSort());
        if (isArrayOutputRequired) arr.display();
    }

    private static void measureEfficiencyOfCountingSort(int startIndex, int endIndex, int start, int end, boolean isArrayOutputRequired) {
        var baseArr = getRandomArray(startIndex, endIndex, start, end);
        int[] copy = new int[baseArr.length];
        System.arraycopy(baseArr, 0, copy, 0, baseArr.length);

        System.out.println("<---------------------BEGIN--------------------->");
        System.out.printf("Измерение сортировки с размером массива от %d элементов:\n", baseArr.length);
        if (isArrayOutputRequired) System.out.println(Arrays.toString(baseArr));

        var arr = new MyArray(copy);
        System.out.printf("Сортировка подсчетом с использованием TreeMap, количество шагов = %d\n", arr.treeMapCountingSort());
        if (isArrayOutputRequired) arr.display();

        arr = new MyArray(copy);
        System.out.printf("Сортировка подсчетом с использованием массива-счетчика, количество шагов = %d\n", arr.arrayCountingSort());
        if (isArrayOutputRequired) arr.display();

        arr = new MyArray(copy);
        System.out.printf("Сортировка подсчетом из Wikipedia, количество шагов = %d\n", arr.wikiCountingSort());
        if (isArrayOutputRequired) arr.display();
    }

    private static void showArrayBeforeAfterDeletion(String prompt, int[] baseArr, int numberToRemove) {
        System.out.println("<---------------------BEGIN--------------------->");
        if (!prompt.isBlank()) System.out.println(prompt);
        int[] copy = new int[baseArr.length];
        System.arraycopy(baseArr, 0, copy, 0, baseArr.length);

        var arr = new MyArray(copy);
        System.out.printf("Массив до удаления числа %d из него\n", numberToRemove);
        arr.display();

        var isNumberFound = arr.deleteAll(numberToRemove);
        System.out.printf("Найдено ли удаляемое число в массиве: %s\n", isNumberFound ? "да" : "нет");

        System.out.println("Массив после удаления");
        arr.display();

        System.out.println("<--------------------THE END-------------------->");
    }

    private static void showArrayBeforeAfterInsertion(String prompt, int[] baseArr, int numberToInsert, int pos) {
        System.out.println("<---------------------BEGIN--------------------->");
        if (!prompt.isBlank()) System.out.println(prompt);
        int[] copy = new int[baseArr.length];
        System.arraycopy(baseArr, 0, copy, 0, baseArr.length);

        var arr = new MyArray(copy);
        System.out.printf("Массив до вставки числа %d в позицию %d\n", numberToInsert, pos);
        arr.display();

        arr.insert(numberToInsert, pos);

        System.out.println("Массив после вставки");
        arr.display();

        System.out.println("<--------------------THE END-------------------->");
    }

    private static int[] getRandomArray() {
        return getRandomArray(10, 50);
    }

    private static int[] getRandomArray(int startIndex, int endIndex) {
        return getRandomArray(startIndex, endIndex, 1, 100);
    }

    private static int[] getRandomArray(int startIndex, int endIndex, int start, int end) {
        var randomSize = getRandomNumber(startIndex, endIndex);
        var baseArr = new int[randomSize];
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
