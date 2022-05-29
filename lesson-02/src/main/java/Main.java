public class Main {
    public static void main(String[] args) {
        MyArray arr = new MyArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        arr.display();
        arr.append(10);
        arr.display();
        arr.delete(3);
        arr.display();
    }
}
