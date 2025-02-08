package jank;

import java.util.stream.Collectors;

/**
 * Main Bot
 */
public class JankBot {
    private static final String TASK_FILE = "./data/jank.txt";
    private static final String NAME = "JankBot";

    private static final TaskList tasks = Storage.loadTasks(TASK_FILE);

    /**
     * Prints greet message
     */
    static String greet() {
        return """
                     _   _    _   _ _  __
                    | | / \\  | \\ | | |/ /
                 _  | |/ _ \\ |  \\| | ' /
                | |_| / ___ \\| |\\  | . \\
                 \\___/_/   \\_\\_| \\_|_|\\_\\
                Hello! I'm %s
                What can I do for you?
                """.formatted(NAME);
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
    static String getDelSuccessMsg(Task t) {
        return """
                Noted. I've removed this task:
                %s
                Now you have %d tasks in the list.""".formatted(t, tasks.size());
    }

    /**
     * Prints the add successful message
     *
     * @param t task that was added
     */
    static String getAddSuccessMsg(Task t) {
        return """
                Got it. I've added this task:
                %s
                Now you have %d tasks in the list.""".formatted(t, tasks.size());
//        System.out.println("Got it. I've added this task:");
//        System.out.println(t);
//        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }


    /**
     * Performs the action that's supplied into the function
     *
     * @param line command as a String[]
     * @throws JankBotException
     */
    static String executeCommand(String[] line) throws JankBotException {
        String cmd = line[0];

        return switch (cmd) {
            case "list" -> tasks.getAllTasks();
            case "find" -> {
                var c = FindCommand.parse(line);
                var matchingTasks = tasks.find(c.query());

                if (matchingTasks.isEmpty()) {
                    yield "No matching tasks found.";
                } else {
                    var tasks = matchingTasks.stream()
                                             .map(Task::toString)
                                             .collect(Collectors.joining("\n"));
                    yield "Here are the matching tasks in your list:\n%s".formatted(tasks);
                }
            }
            case "delete" -> {
                var c = DeleteCommand.parse(line);
                var deletedTask = tasks.remove(c.index());
                Storage.saveTasks(TASK_FILE, tasks);
                yield getDelSuccessMsg(deletedTask);
            }
            case "mark", "unmark" -> {
                var c = MarkCommand.parse(line);

                var output = (c.isMarked())
                        ? "Nice! I've marked this task as done:\n%s\n".formatted(tasks.mark(c.index()))
                        : "Nice! I've marked this task as not done yet:\n%s\n".formatted(tasks.unmark(c.index()));

                Storage.saveTasks(TASK_FILE, tasks);

                yield output;
            }
            case "todo" -> {
                var c = TodoCommand.parse(line);
                var newTask = tasks.add(new TodoTask(c.desc()));
                Storage.saveTasks(TASK_FILE, tasks);
                yield getAddSuccessMsg(newTask);
            }
            case "deadline" -> {
                var c = DeadlineCommand.parse(line);
                var newTask = tasks.add(new DeadlineTask(c.desc(), c.by()));
                Storage.saveTasks(TASK_FILE, tasks);
                yield getAddSuccessMsg(newTask);
            }
            case "event" -> {
                var c = EventCommand.parse(line);
                var newTask = tasks.add(new EventTask(c.desc(), c.from(), c.to()));
                Storage.saveTasks(TASK_FILE, tasks);
                yield getAddSuccessMsg(newTask);
            }
            default -> throw new JankBotException("I don't know what that means");
        };
    }
}

