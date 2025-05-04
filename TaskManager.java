import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskManager {
    private static final String CSV_FILE = "tasks.csv";

    // Todo.javaからToDoを取得
    public static List<Task> getTasks(List<Todo> todos) {
        // 現在の日付を取得
        LocalDate today = LocalDate.now();
        // 日付のフォーマットを指定
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 日付をフォーマット
        String date = today.format(formatter);
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("", 0, date); // タスクの初期化
        for (Todo todo : todos)  {
            task.setTaskName(todo.getTaskName());
            task.setProgress(todo.getProgress());
            task.setDate(date);
        }
        return tasks;
    }



    // 複数のタスクをCSVファイルに保存
    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
            for (Task task : tasks) {
                writer.write(task.getTaskName() + "," + task.getProgress() + "," + task.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 指定した日付のタスクを取得 (修正中)
    public static List<Task> getTasksByDate(String date) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[2].equals(date)) {
                    tasks.add(new Task(parts[0], Integer.parseInt(parts[1]), parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
