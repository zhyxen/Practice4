import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Клас автотестів для перевірки роботи файлового менеджера.
 */
class FileManagerTest {

    @Test
    void shouldSaveAndLoadEmployeesCorrectly(@TempDir Path tempDir) {
        File tempFile = tempDir.resolve("test_input.txt").toFile();
        String filePath = tempFile.getAbsolutePath();

        List<Employee> originalList = new ArrayList<>();
        originalList.add(new Employee("Іван Петренко", "Інженер", 35000.0, 3, Department.IT));
        originalList.add(new ContractEmployee("Олексій Сидоренко", "Розробник", 50000.0, 2, Department.IT, 12));
        originalList.add(new FullTimeEmployee("Марія Коваленко", "HR Менеджер", 40000.0, 5, Department.HR, 15000.0));
        originalList.add(new InternEmployee("Іван Бойко", "Молодший тестувальник", 15000.0, 0, Department.IT, "Володимир Шевченко"));
        originalList.add(new ExecutiveEmployee("Олена Коваль", "Технічний директор", 120000.0, 10, Department.IT, 500));

        // Зберігаємо у тимчасовий файл
        FileManager.saveToFile(originalList, filePath);
        assertTrue(tempFile.exists());

        // Зчитуємо з тимчасового файлу
        List<Employee> loadedList = FileManager.loadFromFile(filePath);

        assertEquals(5, loadedList.size());
        assertEquals("Іван Петренко", loadedList.get(0).getName());
        assertTrue(loadedList.get(1) instanceof ContractEmployee);
        assertTrue(loadedList.get(2) instanceof FullTimeEmployee);
        assertTrue(loadedList.get(3) instanceof InternEmployee);
        assertTrue(loadedList.get(4) instanceof ExecutiveEmployee);
    }
}