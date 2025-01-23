import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    static TaskList loadTasks(String filepath) {
        try (var fin = new FileInputStream(filepath); var ois = new ObjectInputStream(fin)) {
            if (ois.readObject() instanceof TaskList tasklist) {
                return tasklist;
            }
            return new TaskList();
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

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
