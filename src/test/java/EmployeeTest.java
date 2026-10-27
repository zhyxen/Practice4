import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Клас автотестів для перевірки успадкування, поліморфізму та нових похідних класів.
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
    void shouldDemonstratePolymorphismWithBaseReference() {
        Employee emp = new ContractEmployee("Тарас Григорович", "Директор", 80000.0, 10, Department.MANAGEMENT, 24);

        assertTrue(emp.toString().contains("[КОНТРАКТНИК]"));
    }
}