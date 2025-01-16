import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JankyBot {
    private static final String name = "JankyBot";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    static void greet() {
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", name);
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

//    static void addToList(String line) {
//        memory.add(new Task(line));
//    }

    static Task markListItem(int index, boolean isMarked) {
        var task = tasks.get(index);
        task.setMark(isMarked);
        return task;
    }

    static void printList() {
        String out = IntStream.iterate(1, x -> x + 1)
                .limit(tasks.size())
                .mapToObj(c -> "%d. %s".formatted(c, tasks.get(c - 1)))
                .reduce("%s\n%s"::formatted)
                .orElse("");
        System.out.println(out);
    }

    static void printAddSuccessMsg(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    static void checkHasArgs(String[] cmd, String msg) throws JankyBotException {
        if (cmd.length <= 1) {
            throw new JankyBotException(msg);
        }
    }

    static void processCommand(String[] line) throws JankyBotException {
        String cmd = line[0];

        switch (cmd) {
            case "list" -> printList();
            case "mark" -> {
                checkHasArgs(line, "Which task do you want to mark?");
                int index = Integer.parseInt(line[1]) - 1;
                var task = markListItem(index, true);
                System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
            }
            case "unmark" -> {
                checkHasArgs(line, "Which task do you want to unmark?");
                int index = Integer.parseInt(line[1]) - 1;
                var task = markListItem(index, false);
                System.out.printf("Nice! I've marked this task as not done yet:\n%s\n",
                        task);
            }
            case "todo" -> {
                checkHasArgs(line, "Todo description cannot be empty");
                var task = TodoTask.parse(Arrays.copyOfRange(line, 1, line.length));
                tasks.add(task);
                printAddSuccessMsg(task);
            }
            case "deadline" -> {
                checkHasArgs(line, "Deadline description cannot be empty");
                var task = DeadlineTask.parse(Arrays.copyOfRange(line, 1, line.length));
                tasks.add(task);
                printAddSuccessMsg(task);
            }
            case "event" -> {
                checkHasArgs(line, "Event description cannot be empty");
                var task = EventTask.parse(Arrays.copyOfRange(line, 1, line.length));
                tasks.add(task);
                printAddSuccessMsg(task);
            }
            default -> {
                throw new JankyBotException("I don't know what that means");
            }
        }
    }

    public static void main(String[] args) {
        greet();

        var sc = new Scanner(System.in);

        Stream.generate(sc::nextLine)
                .map(String::strip)
                .takeWhile(input -> !input.equalsIgnoreCase("bye"))
                .map(line -> line.split(" "))
                .forEach(cmd -> {
                    try {
                        JankyBot.processCommand(cmd);
                    } catch (JankyBotException e) {
                        System.out.println(e.getMessage());
                    }
                });

        sc.close();

        bye();
    }

}