import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void shouldCreateEmployeeWhenValidData() {
        Employee emp = new Employee("Іван Іваненко", "Розробник", 45000.0, 3);
        assertEquals("Іван Іваненко", emp.getName());
        assertEquals("Розробник", emp.getPosition());
        assertEquals(45000.0, emp.getSalary());
        assertEquals(3, emp.getExperienceYears());
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(InvalidEmployeeDataException.class, () -> {
            new Employee("", "Розробник", 45000.0, 3);
        });

        assertThrows(InvalidEmployeeDataException.class, () -> {
            new Employee("Іван Іваненко", "Розробник", -100.0, 3);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Employee emp = new Employee("Іван Іваненко", "Розробник", 45000.0, 3);

        assertThrows(InvalidEmployeeDataException.class, () -> {
            emp.setSalary(0);
        });

        assertThrows(InvalidEmployeeDataException.class, () -> {
            emp.setExperienceYears(-5);
        });
    }
}