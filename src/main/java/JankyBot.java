import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JankyBot {
    private static final String name = "JankyBot";
    private static final ArrayList<Task> memory = new ArrayList<>();

    static void greet() {
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", name);
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void addToList(String line) {
        memory.add(new Task(line));
    }

    static Task markListItem(int index, boolean isMarked) {
        var newTask = memory.get(index).setMark(isMarked);
        memory.set(index, newTask);
        return newTask;
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
                            if (input.startsWith("mark")) {
                                int index = Integer.parseInt(input.substring("mark ".length())) - 1;
                                var task = markListItem(index, true);
                                System.out.printf("Nice! I've marked this task as done:\n%s\n",
                                        task);
                            } else if (input.startsWith("unmark")) {
                                int index =
                                        Integer.parseInt(input.substring("unmark ".length())) - 1;
                                var task = markListItem(index, false);
                                System.out.printf(
                                        "Nice! I've marked this task as not done yet:\n%s\n",
                                        task);
                            } else {
                                addToList(input);
                                System.out.printf("added: %s\n", input);
                            }
                        }
                    }
                });

        sc.close();

        bye();
    }

    record Task(String title, boolean isMarked) {
        Task(String title) {
            this(title, false);
        }

        Task setMark(boolean isMarked) {
            return new Task(this.title, isMarked);
        }

        private char getStatusIcon() {
            return (this.isMarked ? 'X' : ' ');
        }

        @Override
        public String toString() {
            return "[%s] %s".formatted(this.getStatusIcon(), this.title);
        }
    }
}
