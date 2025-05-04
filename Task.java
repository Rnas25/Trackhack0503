public class Task {
    private String taskName; 
    private String progress; // 型はそのまま
    private String date;

    // コンストラクタを修正
    public Task(String taskName, String progress, String date) {
        this.taskName = taskName;
        this.progress = progress;
        this.date = date;
    }

    // getter
    public String getTaskName() {
        return taskName;
    }
    public String getProgress() {
        return progress;
    }
    public String getDate() {
        return date;
    }
    // setter
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
