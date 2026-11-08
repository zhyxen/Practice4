/**
 * Похідний клас, що описує топ-менеджера / керівника (успадковано від Employee).
 */
public class ExecutiveEmployee extends Employee {
    private int stockOptionsCount;

    /**
     * Конструктор керівника.
     *
     * @param name              ПІБ керівника
     * @param position          Посада
     * @param salary            Заробітна плата (грн)
     * @param experienceYears   Стаж роботи (років)
     * @param department         Відділ (enum)
     * @param stockOptionsCount Кількість опціонів/акцій компанії
     */
    public ExecutiveEmployee(String name, String position, double salary, int experienceYears, Department department, int stockOptionsCount) {
        super(name, position, salary, experienceYears, department);
        setStockOptionsCount(stockOptionsCount);
    }

    public int getStockOptionsCount() {
        return stockOptionsCount;
    }

    public void setStockOptionsCount(int stockOptionsCount) {
        if (stockOptionsCount < 0) {
            throw new InvalidEmployeeDataException("Кількість опціонів не може бути від'ємною.");
        }
        this.stockOptionsCount = stockOptionsCount;
    }

    @Override
    public String toString() {
        return String.format("[КЕРІВНИК] %s | Акцій/Опціонів: %d шт.", super.toString(), stockOptionsCount);
    }
}