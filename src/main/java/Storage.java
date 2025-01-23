import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    static ArrayList<Task> loadTasks(String filepath) {
        try (var fin = new FileInputStream(filepath); var ois = new ObjectInputStream(fin)) {
            return (ArrayList<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    static void saveTasks(String filepath, ArrayList<Task> tasks) throws JankBotException {
        var file = new File(filepath);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists

        try (var fout = new FileOutputStream(file); var oos = new ObjectOutputStream(fout)) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            throw new JankBotException("Fail to save task");
        }
    }
}
