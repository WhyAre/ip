package jank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jank.task.TaskList;

/**
 * Handles the loading and storing of the tasks to secondary storage
 */
public class Storage {
    /**
     * Stores the tasks into a file
     *
     * @param filepath relative path to the source file
     * @return List of tasks as a TaskList object
     */
    static TaskList loadTasks(String filepath) {
        try (var fin = new FileInputStream(filepath); var ois = new ObjectInputStream(fin)) {
            if (ois.readObject() instanceof TaskList tasklist) {
                return tasklist;
            }
            return new TaskList();
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (Exception e) {
            System.err.println("Error loading tasks");
            return new TaskList();
        }
    }

    /**
     * Saves tasks into a file
     *
     * @param filepath path to the destination file
     * @param tasks    List of tasks (as a TaskList object) to save
     * @throws JankBotException
     */
    static void saveTasks(String filepath, TaskList tasks) throws JankBotException {
        var file = new File(filepath);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists

        try (var fout = new FileOutputStream(file); var oos = new ObjectOutputStream(fout)) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            throw new JankBotException("Fail to save task");
        }
    }
}
