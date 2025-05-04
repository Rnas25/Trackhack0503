import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String CSV_FILE = "tasks.csv";
    // 日付のフォーマットを指定
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    // CSVファイルが存在しない場合に新規作成するメソッド
    public static void createCsvFileIfNotExists() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("CSVファイル '" + CSV_FILE + "' を作成しました。");
                } else {
                    System.err.println("CSVファイル '" + CSV_FILE + "' の作成に失敗しました。");
                }
            } catch (IOException e) {
                System.err.println("CSVファイル '" + CSV_FILE + "' の作成中にエラーが発生しました: " + e.getMessage());
            }
        }
    }
    

    

    // Todo.javaからToDoを取得
    public static List<Task> getTasks(List<Todo> todos) {
        // 現在の日付を取得
        LocalDate today = LocalDate.now();
        // 日付をフォーマット
        String date = today.format(formatter);
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("", "0", date);
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

    // CSVファイルから全てのタスクを取得
    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    tasks.add(new Task(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Alltaskメソッドから最新のタスクを取得
    public static List<Task> getLatestTasks() {
        List<Task> allTasks = getAllTasks();
        if (allTasks.isEmpty()) {
            return new ArrayList<>(); // タスクがない場合は空のリストを返す
        }

        LocalDate latestDate = LocalDate.parse(allTasks.get(0).getDate(), formatter);
        List<Task> latestTasks = new ArrayList<>();
        latestTasks.add(allTasks.get(0)); // 最初のタスクを初期値とする

        for (int i = 1; i < allTasks.size(); i++) {
            LocalDate currentDate = LocalDate.parse(allTasks.get(i).getDate(), formatter);
            if (currentDate.isAfter(latestDate)) {
                latestDate = currentDate;
                latestTasks.clear(); 
                latestTasks.add(allTasks.get(i));
            } else if (currentDate.isEqual(latestDate)) {
                latestTasks.add(allTasks.get(i)); // 同じ最新日付のタスクを追加
            }
        }
        return latestTasks;
    }

    // 指定した日付のタスクを取得
    public static List<Task> getTasksByDate(String date) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[2].equals(date)) {
                    tasks.add(new Task(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
