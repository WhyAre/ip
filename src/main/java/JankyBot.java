public class JankyBot {
    private static final String name = "JankyBot";

    static void greet() {
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", name);
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();

        bye();
    }
}
