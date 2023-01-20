package enums;

public enum TypeRepeatTask {
    SINGLE("Одноразовая"),
    DAILY("Ежедневная"),
    WEEKLY("Еженедельная"),
    MONTHLY("Ежемесячная"),
    YEARLY("Ежегодная");

    private final String type;

    TypeRepeatTask(String title) {
        this.type = title;
    }
}
