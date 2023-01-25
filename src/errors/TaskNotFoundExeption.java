package errors;

public class TaskNotFoundExeption extends Exception{
    public TaskNotFoundExeption(String message) {
        super(message);
    }

    public TaskNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
