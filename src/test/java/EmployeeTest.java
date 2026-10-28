import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Клас автотестів для перевірки розширеної 5-класової ієрархії.
 */
class EmployeeTest {

    @Test
    void shouldCreateContractEmployeeAndOverriddenToString() {
        ContractEmployee contractEmp = new ContractEmployee("Олексій Сидоренко", "Розробник", 50000.0, 2, Department.IT, 12);

        assertEquals("Олексій Сидоренко", contractEmp.getName());
        assertEquals(12, contractEmp.getContractMonths());
        assertTrue(contractEmp.toString().contains("[КОНТРАКТНИК]"));
    }

    @Test
    void shouldCreateFullTimeEmployeeAndOverriddenToString() {
        FullTimeEmployee fullTimeEmp = new FullTimeEmployee("Марія Коваленко", "HR Менеджер", 40000.0, 5, Department.HR, 15000.0);

        assertEquals("Марія Коваленко", fullTimeEmp.getName());
        assertEquals(15000.0, fullTimeEmp.getAnnualBonus());
        assertTrue(fullTimeEmp.toString().contains("[ШТАТНИЙ]"));
    }

    @Test
    void shouldCreateInternEmployeeAndVerifyMentor() {
        InternEmployee intern = new InternEmployee("Іван Бойко", "Молодший тестувальник", 15000.0, 0, Department.IT, "Володимир Шевченко");

        assertEquals("Іван Бойко", intern.getName());
        assertEquals("Володимир Шевченко", intern.getMentorName());
        assertTrue(intern.toString().contains("[СТАЖЕР]"));
    }

    @Test
    void shouldCreateExecutiveEmployeeAndVerifyStockOptions() {
        ExecutiveEmployee exec = new ExecutiveEmployee("Олена Коваль", "Технічний директор", 120000.0, 12, Department.IT, 500);

        assertEquals("Олена Коваль", exec.getName());
        assertEquals(500, exec.getStockOptionsCount());
        assertTrue(exec.toString().contains("[КЕРІВНИК]"));
    }
}