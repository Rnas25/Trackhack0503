public class Todo{
	String task_name;
	String progress;
	
	
	public Todo(String task_name, String progress) {
		this.task_name = task_name;
		this.progress = progress;
	}
	
	public String getTaskName() {
		return this.task_name;
	}
	
	public String getProgress() {
		return this.progress;
	}
}