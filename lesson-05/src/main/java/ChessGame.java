public class ChessGame {
    private static final Shift[] movesShift = new Shift[]{
            new Shift(-2, 1),
            new Shift(-1, 2),
            new Shift(1, 2),
            new Shift(2, 1),
            new Shift(2, -1),
            new Shift(1, -2),
            new Shift(-1, -2),
            new Shift(-2, -1),
    };

    private int width;
    private int height;
    private int possibleMovesCount;
    private int[][] moves;
    private int[] verticalMoves, horizontalMoves;
    private int y, x, moveIndex;

    public void play() {
        width = Utils.inputNumber("Введи ширину доски", true);
        height = Utils.inputNumber("Введи высоту доски", true);
        possibleMovesCount = width * height;
        moves = new int[height][width];
        verticalMoves = new int[possibleMovesCount];
        horizontalMoves = new int[possibleMovesCount];
        moveIndex = 0;
        y = Utils.getRandomNumber(0, height - 1);
        x = Utils.getRandomNumber(0, width - 1);
        System.out.printf("Начало, y: %d, x: %d\n", y, x);
        try {
            fillKnightMoves();
            displayBoard();
        } catch (StackOverflowError e) {
            System.out.println("Ну не смогла я!");
        }
    }

    private void fillKnightMoves() {
        moves[y][x] = 1;
        verticalMoves[moveIndex] = y;
        horizontalMoves[moveIndex] = x;
        moveIndex++;

        int nextY, nextX;
        boolean isNextMoveFound = false;
        for (var shift : movesShift) {
            if (moveIndex >= possibleMovesCount) return;

            nextY = y + shift.y();
            nextX = x + shift.x();
            if (!doesMoveFitBoard(nextY, nextX)) continue;
            if (moves[nextY][nextX] == 1) continue;
            isNextMoveFound = true;
            y = nextY;
            x = nextX;
            fillKnightMoves();
        }

        if (!isNextMoveFound) return;
        moveIndex--;

        // идем назад
        var prevY = verticalMoves[moveIndex];
        var prevX = horizontalMoves[moveIndex];
        moves[prevY][prevX] = 0;
        moveIndex--;

        // идем дальше, делаем следующий ход
        y = verticalMoves[moveIndex];
        x = horizontalMoves[moveIndex];
        moveIndex++;
    }

    private boolean doesMoveFitBoard(int y, int x) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    private void displayBoard() {
        System.out.println("Ура! Твой кампухтер не сгорел от вычислений! Мамка счастлива! Флексим!");
        System.out.println("Посмотрим список ходов: ");
        for (int idx = 0; idx < possibleMovesCount; idx++) {
            y = verticalMoves[idx];
            x = horizontalMoves[idx];
            if (y == 0 && x == 0) return;
            System.out.printf("%d: (y = %d, x = %d)\n", idx + 1, y, x);
        }
    }
}