public class Todo{
	String task_name;
	int progress;
	
	
	public Todo(String task_name, int progress) {
		this.task_name = task_name;
		this.progress = progress;
	}
	
	public String getTaskName() {
		return this.task_name;
	}
	
	public int getProgress() {
		return this.progress;
	}
}