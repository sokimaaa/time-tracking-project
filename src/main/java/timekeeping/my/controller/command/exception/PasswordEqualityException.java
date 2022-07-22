package timekeeping.my.controller.command.exception;

public class PasswordEqualityException extends Exception {
    public PasswordEqualityException(String message) {
        super(message);
    }
}
