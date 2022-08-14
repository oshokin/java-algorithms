public class Main {
    public static void main(String[] args) {
        var journal = getSchoolJournal();
        System.out.println("My school journal in Vologda high school at mid of 2014 year!");
        System.out.println(journal);

        System.out.println("Not let's fix something!");
        journal.delete("Physical education");
        System.out.println(journal);

        System.out.println("No hay malas notas mas! Soy un puto genio!!!");
        journal.put("Physical education", 5);
        System.out.printf("And my physical education note is %d\n", journal.get("Physical education"));
        System.out.println(journal);

        System.out.println("You see, I believe I can fly!");
    }

    private static MyHashTable<String, Integer> getSchoolJournal() {
        var journal = new MyHashTable<String, Integer>();
        journal.put("Math", 5);
        journal.put("Russian", 3);
        journal.put("English", 5);
        journal.put("Science", 5);
        journal.put("Computer science", 5);
        journal.put("Spanish", 5);
        journal.put("Physical education", 3);

        return journal;
    }
}
