/**
 * Похідний клас, що описує стажера (успадковано від Employee).
 */
public class InternEmployee extends Employee {
    private String mentorName;

    /**
     * Конструктор стажера.
     *
     * @param name            ПІБ стажера
     * @param position        Посада
     * @param salary          Заробітна плата (стипендія, грн)
     * @param experienceYears Стаж роботи (років)
     * @param department      Відділ (enum)
     * @param mentorName      ПІБ наставника (ментора)
     */
    public InternEmployee(String name, String position, double salary, int experienceYears, Department department, String mentorName) {
        super(name, position, salary, experienceYears, department);
        setMentorName(mentorName);
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        if (mentorName == null || mentorName.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("ПІБ ментора не може бути порожнім.");
        }
        this.mentorName = mentorName.trim();
    }

    @Override
    public String toString() {
        return String.format("[СТАЖЕР] %s | Ментор: %s", super.toString(), mentorName);
    }
}