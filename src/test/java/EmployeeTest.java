import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void shouldCreateEmployeeAndTrackStaticCount() {
        int initialCount = Employee.getCount();
        Employee emp = new Employee("Іван Іваненко", "Розробник", 45000.0, 3, Department.IT);

        assertEquals("Іван Іваненко", emp.getName());
        assertEquals(Department.IT, emp.getDepartment());
        assertEquals(initialCount + 1, Employee.getCount());
    }

    @Test
    void shouldCopyEmployeeCorrectly() {
        Employee original = new Employee("Петро Петренко", "Менеджер", 35000.0, 5, Department.HR);
        Employee copy = new Employee(original);

        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getDepartment(), copy.getDepartment());
        assertEquals(original.getSalary(), copy.getSalary());
    }

    @Test
    void shouldThrowExceptionWhenNullDepartment() {
        assertThrows(InvalidEmployeeDataException.class, () -> {
            new Employee("Тарас Шевченко", "Директор", 60000.0, 10, null);
        });
    }
}