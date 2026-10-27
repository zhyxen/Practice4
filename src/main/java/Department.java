/**
 * Перерахування для визначення відділу підприємства.
 */
public enum Department {
    IT("ІТ-відділ"),
    HR("Відділ кадрів"),
    FINANCE("Фінансовий відділ"),
    MARKETING("Відділ маркетингу"),
    LOGISTICS("Відділ логістики");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}