package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

// Solution below adapted by https://github.com/ruishanteo/ip
/**
 * Storage class that handles reading and writing to the data text file
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for a new Storage object
     * @param filePath String that represents the file path that the Storage object should read and write to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data stored in the data text file and returns the data as an ArrayList of String
     * where each element of the ArrayList is each respective line in the text file
     * @return ArrayList of string that represents each line of the data in the text file
     * @throws RichieException When there is an IOException when reading and writing to the text file
     */
    public ArrayList<String> load() throws RichieException {
        ArrayList<String> resultStringArray = new ArrayList<>();
        try {
            File textFile = new File(this.filePath);
            File parentDirectory = textFile.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }
            if (!textFile.exists()) {
                textFile.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));
            String taskString;
            while ((taskString = bufferedReader.readLine()) != null) {
                resultStringArray.add(taskString);
            }
        } catch (IOException e) {
            throw new RichieException("Error loading the file");
        }
        return resultStringArray;
    }

    /**
     * Saves the tasks that are currently in the TaskList object of the Richie application into the data text file
     * by writing to it
     * @param taskList ArrayList of Task objects denoting the tasks that are currently on the tasklist
     */
    public void saveCurrentTasks(ArrayList<Task> taskList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task : taskList) {
                bufferedWriter.append(task.toDataString());
                bufferedWriter.append("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
