/**
 * Власний виняток для позначення некоректних даних працівника.
 */
public class InvalidEmployeeDataException extends IllegalArgumentException {
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}