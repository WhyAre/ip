import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;


public class JankBot {
    private static final String TASK_FILE = "./data/jank.txt";

    private static final String name = "JankBot";
    private static final TaskList tasks = Storage.loadTasks(TASK_FILE);


    static void greet() {
        System.out.println("""
                     _   _    _   _ _  __
                    | | / \\  | \\ | | |/ /
                 _  | |/ _ \\ |  \\| | ' /
                | |_| / ___ \\| |\\  | . \\
                 \\___/_/   \\_\\_| \\_|_|\\_\\
                """);
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", name);
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void printDelSuccessMsg(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    static void printAddSuccessMsg(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    static void checkHasArgs(String[] cmd, String msg) throws JankBotException {
        if (cmd.length <= 1) {
            throw new JankBotException(msg);
        }
    }

    static void processCommand(String[] line) throws JankBotException {
        String cmd = line[0];

        switch (cmd) {
            case "list" -> tasks.print();
            case "delete" -> {
                checkHasArgs(line, "Which task do you want to delete?");
                int index = Integer.parseInt(line[1]) - 1;
                printDelSuccessMsg(tasks.remove(index));
                Storage.saveTasks(TASK_FILE, tasks);
            }
            case "mark" -> {
                checkHasArgs(line, "Which task do you want to mark?");
                int index = Integer.parseInt(line[1]) - 1;
                System.out.printf("Nice! I've marked this task as done:\n%s\n", tasks.mark(index));
                Storage.saveTasks(TASK_FILE, tasks);
            }
            case "unmark" -> {
                checkHasArgs(line, "Which task do you want to unmark?");
                int index = Integer.parseInt(line[1]) - 1;
                System.out.printf("Nice! I've marked this task as not done yet:\n%s\n",
                        tasks.unmark(index));
                Storage.saveTasks(TASK_FILE, tasks);
            }
            case "todo" -> {
                checkHasArgs(line, "Todo description cannot be empty");
                var task = TodoTask.parse(Arrays.copyOfRange(line, 1, line.length));
                printAddSuccessMsg(tasks.add(task));
                Storage.saveTasks(TASK_FILE, tasks);
            }
            case "deadline" -> {
                checkHasArgs(line, "Deadline description cannot be empty");
                var task = DeadlineTask.parse(Arrays.copyOfRange(line, 1, line.length));
                printAddSuccessMsg(tasks.add(task));
                Storage.saveTasks(TASK_FILE, tasks);
            }
            case "event" -> {
                checkHasArgs(line, "Event description cannot be empty");
                var task = EventTask.parse(Arrays.copyOfRange(line, 1, line.length));
                printAddSuccessMsg(tasks.add(task));
                Storage.saveTasks(TASK_FILE, tasks);
            }
            default -> {
                throw new JankBotException("I don't know what that means");
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
                        JankBot.processCommand(cmd);
                    } catch (JankBotException e) {
                        System.out.println(e.getMessage());
                    }
                });

        sc.close();

        bye();
    }

}

