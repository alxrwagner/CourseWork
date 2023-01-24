package enums;

public enum TypeRepeatTask {
    ONE_TIME("Однократная"),
    DAILY("Ежедневная"),
    WEEKLY("Еженедельная"),
    MONTHLY("Ежемесячная"),
    YEARLY("Ежегодная");

    private final String type;

    TypeRepeatTask(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
