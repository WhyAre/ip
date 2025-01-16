import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JankyBot {
    private static final String name = "JankyBot";
    private static final ArrayList<String> memory = new ArrayList<>();

    static void greet() {
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", name);
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void addToList(String line) {
        memory.add(line);
    }

    static void printList() {
        String out = IntStream.iterate(1, x -> x + 1)
                .limit(memory.size())
                .mapToObj(c -> "%d. %s".formatted(c, memory.get(c - 1)))
                .reduce("%s\n%s"::formatted)
                .orElse("");
        System.out.println(out);
    }

    public static void main(String[] args) {
        greet();

        var sc = new Scanner(System.in);

        Stream.generate(sc::nextLine)
                .takeWhile(input -> !input.equalsIgnoreCase("bye"))
                .forEach(input -> {
                    switch (input) {
                        case "list" -> printList();
                        default -> {
                            addToList(input);
                            System.out.printf("added: %s\n", input);
                        }
                    }
                });

        sc.close();

        bye();
    }
}
