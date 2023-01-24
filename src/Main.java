import dailyPlanner.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService dailyPlanner = new TaskService();

        dailyPlanner.startWork();
//        dailyPlanner.addTask();
//        System.out.println(dailyPlanner.getTasks().peek());
    }
}