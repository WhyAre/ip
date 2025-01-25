package jank;

import java.util.Scanner;
import java.util.stream.Stream;


/**
 * Main Bot
 */
public class JankBot {
    private static final String TASK_FILE = "./data/jank.txt";

    private static final String name = "jank.JankBot";
    private static final TaskList tasks = Storage.loadTasks(TASK_FILE);

    /**
     * Prints greet message
     */
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

    /**
     * Prints bye message
     */
    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints delete successful message
     *
     * @param t task that was deleted
     */
    static void printDelSuccessMsg(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Prints the add successful message
     *
     * @param t task that was added
     */
    static void printAddSuccessMsg(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }


    /**
     * Performs the action that's supplied into the function
     *
     * @param line command as a String[]
     * @throws JankBotException
     */
    static void processCommand(String[] line) throws JankBotException {
        String cmd = line[0];

        switch (cmd) {
        case "list" -> tasks.print();
        case "find" -> {
            var c = FindCommand.parse(line);
            var matchingTasks = tasks.find(c.query());

            if (matchingTasks.isEmpty()) {
                System.out.println("No matching tasks found.");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                matchingTasks.forEach(System.out::println);
            }
        }
        case "delete" -> {
            var c = DeleteCommand.parse(line);
            printDelSuccessMsg(tasks.remove(c.index()));
            Storage.saveTasks(TASK_FILE, tasks);
        }
        case "mark", "unmark" -> {
            var c = MarkCommand.parse(line);

            if (c.isMarked()) {
                System.out.printf("Nice! I've marked this task as done:\n%s\n", tasks.mark(c.index()));
            } else {
                System.out.printf("Nice! I've marked this task as not done yet:\n%s\n", tasks.unmark(c.index()));
            }

            Storage.saveTasks(TASK_FILE, tasks);
        }
        case "todo" -> {
            var c = TodoCommand.parse(line);
            printAddSuccessMsg(tasks.add(new TodoTask(c.desc())));
            Storage.saveTasks(TASK_FILE, tasks);
        }
        case "deadline" -> {
            var c = DeadlineCommand.parse(line);
            printAddSuccessMsg(tasks.add(new DeadlineTask(c.desc(), c.by())));
            Storage.saveTasks(TASK_FILE, tasks);
        }
        case "event" -> {
            var c = EventCommand.parse(line);
            printAddSuccessMsg(tasks.add(new EventTask(c.desc(), c.from(), c.to())));
            Storage.saveTasks(TASK_FILE, tasks);
        }
        default -> throw new JankBotException("I don't know what that means");
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

