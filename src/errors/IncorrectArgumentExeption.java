package errors;

public class IncorrectArgumentExeption extends Exception {
    private String argument;

    public IncorrectArgumentExeption(String message) {
        super(message);
    }

    public IncorrectArgumentExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "IncorrectArgumentExeption{" +
                "argument='" + argument + '\'' +
                '}';
    }
}
