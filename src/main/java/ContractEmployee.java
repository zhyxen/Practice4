/**
 * Похідний клас, що описує працівника за контрактом (успадковано від Employee).
 */
public class ContractEmployee extends Employee {
    private int contractMonths;

    /**
     * Конструктор контрактного працівника.
     *
     * @param name            ПІБ працівника
     * @param position        Посада
     * @param salary          Заробітна плата (грн)
     * @param experienceYears Стаж роботи (років)
     * @param department      Відділ (enum)
     * @param contractMonths  Тривалість контракту (місяців)
     */
    public ContractEmployee(String name, String position, double salary, int experienceYears, Department department, int contractMonths) {
        super(name, position, salary, experienceYears, department);
        setContractMonths(contractMonths);
    }

    public int getContractMonths() {
        return contractMonths;
    }

    public void setContractMonths(int contractMonths) {
        if (contractMonths <= 0) {
            throw new InvalidEmployeeDataException("Тривалість контракту повинна бути більшою за 0 місяців.");
        }
        this.contractMonths = contractMonths;
    }

    @Override
    public String toString() {
        return String.format("[КОНТРАКТНИК] %s | Контракт на: %d міс.", super.toString(), contractMonths);
    }
}