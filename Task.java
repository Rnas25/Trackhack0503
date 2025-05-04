public class Task {
    private String taskName; // フィールド名を修正
    private int progress;
    private String date;

    // コンストラクタを追加
    public Task(String taskName, int progress, String date) {
        this.taskName = taskName;
        this.progress = progress;
        this.date = date;
    }

    // getter
    public String getTaskName() { // メソッド名を修正
        return taskName;
    }
    public int getProgress() {
        return progress;
    }
    public String getDate() {
        return date;
    }
    // setter
    public void setTaskName(String taskName) { // メソッド名を修正
        this.taskName = taskName;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
